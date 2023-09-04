package ClassReader;
import Class.Client;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ClientReader {

    public static List<Client> readClientsFromFile(String fileName) {
        List<Client> clients = new ArrayList<> ();

        try {
            File file = new File ( fileName );
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance ();
            DocumentBuilder db = dbf.newDocumentBuilder ();
            Document doc = db.parse ( file );

            doc.getDocumentElement ().normalize ();
            NodeList nodeList = doc.getElementsByTagName ( "ObjectClient" );

            for (int i = 0; i < nodeList.getLength (); i++) {
                Node node = nodeList.item ( i );

                if (node.getNodeType () == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Client client = new Client ();

                    client.setIdClient ( Integer.parseInt ( element.getElementsByTagName ( "idClient" ).item ( 0 ).getTextContent () ) );
                    client.setCodePostale ( Integer.valueOf ( element.getElementsByTagName ( "codePostale" ).item ( 0 ).getTextContent () ) );
                    client.getRaisonSocial ( element.getElementsByTagName ( "raisonSocial" ).item ( 0 ).getTextContent () );
                    client.setVille ( element.getElementsByTagName ( "ville" ).item ( 0 ).getTextContent () );

                    clients.add ( client );
                }
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }

        return clients;
    }
}