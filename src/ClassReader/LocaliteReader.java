package ClassReader;
import Class.Localite;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocaliteReader {
    public static List<Localite> readLocalitesFromFile(String fileName) {
        List<Localite> localites = new ArrayList<> ();

        try {
            File file = new File ( fileName );
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance ();
            DocumentBuilder db = dbf.newDocumentBuilder ();
            Document doc = db.parse ( file );

            doc.getDocumentElement ().normalize ();
            NodeList nodeList = doc.getElementsByTagName ( "ObjectLocalite" );

            for (int i = 0; i < nodeList.getLength (); i++) {
                Node node = nodeList.item ( i );

                if (node.getNodeType () == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Localite localite = new Localite ();

                    localite.setCodePostale ( element.getElementsByTagName ( "codePostal" ).item ( 0 ).getTextContent () );
                    localite.setVille ( element.getElementsByTagName ( "ville" ).item ( 0 ).getTextContent () );
                    localite.setZone ( Integer.parseInt ( element.getElementsByTagName ( "zone" ).item ( 0 ).getTextContent () ) );

                    localites.add ( localite );
                }
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }

        return localites;
    }

    // Méthode pour rechercher la zone en fonction de la ville du destinataire
    public static int findZoneForDestination(String destinationCity, List<Localite> localites) {
        for (Localite localite : localites) {
            if (localite.getVille ().equalsIgnoreCase ( destinationCity )) {
                return localite.getZone ();
            }
        }
        // Si la ville du destinataire n'est pas trouvée, on retourne une valeur par défaut, par exemple 0.
        return 0;

    }
}
