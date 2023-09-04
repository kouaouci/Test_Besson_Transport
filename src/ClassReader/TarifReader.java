package ClassReader;


import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import Class.Tarif;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class TarifReader {
    public static List<Tarif> readTarifsFromFile(String fileName) {
        List<Tarif> tarifs = new ArrayList<> ();

        try {
            File file = new File ( fileName );
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance ();
            DocumentBuilder db = dbf.newDocumentBuilder ();
            Document doc = db.parse ( file );

            doc.getDocumentElement ().normalize ();
            NodeList nodeList = doc.getElementsByTagName ( "ObjectTarif" );

            for (int i = 0; i < nodeList.getLength (); i++) {
                Node node = nodeList.item ( i );

                if (node.getNodeType () == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Tarif tarif = new Tarif ();

                    tarif.setIdClient ( Integer.parseInt ( element.getElementsByTagName ( "idClient" ).item ( 0 ).getTextContent () ) );
                    tarif.setIdClientHeritage ( Integer.parseInt ( element.getElementsByTagName ( "idClientHeritage" ).item ( 0 ).getTextContent () ) );
                    tarif.setCodeDepartement ( Integer.valueOf ( element.getElementsByTagName ( "codeDepartement" ).item ( 0 ).getTextContent () ) );
                    tarif.setZone ( String.valueOf ( Integer.parseInt ( element.getElementsByTagName ( "zone" ).item ( 0 ).getTextContent () ) ) );
                    tarif.setMontant ( new BigDecimal ( element.getElementsByTagName ( "montant" ).item ( 0 ).getTextContent () ) );

                    tarifs.add ( tarif );
                }
            }
        } catch (Exception e) {


            e.printStackTrace ();
        }


        return tarifs;
}
}
