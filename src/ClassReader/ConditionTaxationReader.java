package ClassReader;
import Class.ConditionTaxation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ConditionTaxationReader {

    public static List<ConditionTaxation> readConditionTaxationsFromFile(String fileName) {
        List<ConditionTaxation> conditionTaxations = new ArrayList<> ();

        try {
            File file = new File ( fileName );
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance ();
            DocumentBuilder db = dbf.newDocumentBuilder ();
            Document doc = db.parse ( file );

            doc.getDocumentElement ().normalize ();
            NodeList nodeList = doc.getElementsByTagName ( "ObjectConditionTaxation" );

            for (int i = 0; i < nodeList.getLength (); i++) {
                Node node = nodeList.item ( i );

                if (node.getNodeType () == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    ConditionTaxation conditionTaxation = new ConditionTaxation ();

                    conditionTaxation.setIdClient ( Integer.parseInt ( element.getElementsByTagName ( "idClient" ).item ( 0 ).getTextContent () ) );
                    conditionTaxation.setUseTaxePortDuGenerale ( Boolean.parseBoolean ( element.getElementsByTagName ( "useTaxePortDuGenerale" ).item ( 0 ).getTextContent () ) );
                    conditionTaxation.setTaxePortDu ( new BigDecimal ( element.getElementsByTagName ( "taxePortDu" ).item ( 0 ).getTextContent () ) );
                    conditionTaxation.setUseTaxePortPayeGenerale ( Boolean.parseBoolean ( element.getElementsByTagName ( "useTaxePortPayeGenerale" ).item ( 0 ).getTextContent () ) );
                    conditionTaxation.setTaxePortPaye ( new BigDecimal ( element.getElementsByTagName ( "taxePortPaye" ).item ( 0 ).getTextContent () ) );

                    conditionTaxations.add ( conditionTaxation );
                }
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }

        return conditionTaxations;
    }
     // Méthode pour rechercher les conditions de taxation en fonction de l'expéditeur ou du destinataire qui paie le transport
    public static ConditionTaxation findConditionTaxationForClient(int clientId, List<ConditionTaxation> conditionTaxations) {
        for (ConditionTaxation conditionTaxation : conditionTaxations) {
            if (conditionTaxation.getIdClient() == clientId) {
                return conditionTaxation;
            }
        }
        // Si les conditions de taxation pour le client spécifié ne sont pas trouvées, on retourne les conditions générales.
        for (ConditionTaxation conditionTaxation : conditionTaxations) {
            if (conditionTaxation.getIdClient() == 0) {
                return conditionTaxation;
            }
        }
        // Si les conditions de taxation générales ne sont pas trouvées non plus, on retourne null.
        return null;
    }



}











