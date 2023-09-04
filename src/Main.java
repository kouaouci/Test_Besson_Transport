
import java.util.List;
import Class.Client;

import java.util.Scanner;
import ClassReader.ClientReader;
import ClassReader.ConditionTaxationReader;
import ClassReader.LocaliteReader;
import ClassReader.TarifReader;
import Class.ConditionTaxation;
import Class.Localite;
import Class.Tarif;

public class Main {
    public static void main(String[] args) {
        // Lire les données à partir des fichiers XML et stocker les objets dans des listes
        List<Client> clients = ClientReader.readClientsFromFile ( "clients.xml" );
        List<Tarif> tarifs = TarifReader.readTarifsFromFile ( "tarifs.xml" );
        List<Localite> localites = LocaliteReader.readLocalitesFromFile ( "localite.xml" );
        List<ConditionTaxation> conditionTaxations = ConditionTaxationReader.readConditionTaxationsFromFile ( "conditiontaxation.xml" );

        // Supposons que nous sélectionnons un client expéditeur avec l'ID 1 et un client destinataire avec l'ID 2
        int expediteurId = 1;
        int destinataireId = 2;

        // Supposons que nous avons 3 colis et un poids total de 100 kg
        int nombreColis = 3;
        double poidsTotal = 100.0;

        // Supposons que l'expéditeur règle le transport (port payé) et le destinataire se trouve à "MACON"
        String destinationVille = "MACON";
        boolean portPaye = true;

        // Rechercher les zones en fonction de la ville du destinataire
        int zone = LocaliteReader.findZoneForDestination ( destinationVille, localites );

        // Rechercher les conditions de taxation pour l'expéditeur et le destinataire
        ConditionTaxation expediteurConditionTaxation = ConditionTaxationReader.findConditionTaxationForClient ( expediteurId, conditionTaxations );
        ConditionTaxation destinataireConditionTaxation = ConditionTaxationReader.findConditionTaxationForClient ( destinataireId, conditionTaxations );

        // Calculer le montant HT du transport en appliquant les règles de calcul
        double montantHT = calculateMontantHT ( tarifs, expediteurConditionTaxation, destinataireConditionTaxation, zone, nombreColis, poidsTotal, portPaye );

        // Afficher le résultat
        System.out.println ( "Montant HT du transport : " + montantHT );
    }

    public static double calculateMontantHT(List<Tarif> tarifs, ConditionTaxation expediteurConditionTaxation,
                                            ConditionTaxation destinataireConditionTaxation, int zone, int nombreColis,
                                            double poidsTotal, boolean portPaye) {

        // Chercher le tarif correspondant à l'expéditeur
        Tarif expediteurTarif = findTarifForClient ( tarifs, expediteurConditionTaxation, zone );

        // Chercher le tarif correspondant au destinataire
        Tarif destinataireTarif = findTarifForClient ( tarifs, destinataireConditionTaxation, zone );

        // Calculer le montant HT en fonction des tarifs, de la taxation et des autres informations
        double montantHT = 0.0;
        if (portPaye) {
            montantHT = nombreColis * expediteurTarif.getMontant ().doubleValue () * poidsTotal;
        } else {
            montantHT = nombreColis * destinataireTarif.getMontant ().doubleValue () * poidsTotal;
        }

        // Appliquer la taxe en fonction des conditions de taxation
        if (portPaye && expediteurConditionTaxation != null) {
            if (expediteurConditionTaxation.isUseTaxePortPayeGenerale ()) {
                montantHT += montantHT * expediteurConditionTaxation.getTaxePortPaye ().doubleValue ();
            }
        } else if (!portPaye && destinataireConditionTaxation != null) {
            if (destinataireConditionTaxation.isUseTaxePortDuGenerale ()) {
                montantHT += montantHT * destinataireConditionTaxation.getTaxePortDu ().doubleValue ();
            }
        }

        return montantHT;
    }


    }


}








