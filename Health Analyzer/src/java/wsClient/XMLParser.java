/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsClient;

/**
 *
 * @author Rudolf
 */
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XMLParser {

    public static void main(String[] args) {

        XMLParser main = new XMLParser();
        try {
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new URL("https://rxnav.nlm.nih.gov/REST/interaction/list.xml?rxcuis=207106+152923+656659").openStream());
            doc.getDocumentElement().normalize();
            System.out.println("Root element :"
                    + doc.getDocumentElement().getNodeName());

            ArrayList<MedObj> medObjs = new ArrayList<MedObj>();
            int amountOfNames = main.amountofNames(doc);
            for (int i = 0; i < amountOfNames; i++) {
                medObjs.add(new MedObj());
            }

            main.parseNames(doc, medObjs);
            main.parseDesc(doc, medObjs);
            for (MedObj obj : medObjs) {
                System.out.println(obj.getMed1_name() + " rxui1: "+ obj.getMed1_rxcui()+ " and drug 2: " + obj.getMed2_name() +  " rxui2:" + obj.getMed2_rxcui() );
                System.out.println("this objects severity: " + obj.getSeverity() + "\n and its description: " + obj.getDescription());
            }
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void parseNames(Document doc, ArrayList<MedObj> medObjs) {
        try {
            NodeList nList = doc.getElementsByTagName("minConcept");
            System.out.println("----------------------------");
            for (int temp = 0, j = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    if (temp % 2 == 0) {
                        medObjs.get(j).setMed1_name(eElement.getElementsByTagName("name").item(0).getTextContent());
                        medObjs.get(j).setMed1_rxcui(eElement.getElementsByTagName("rxcui").item(0).getTextContent());
                    } else {
                        medObjs.get(j).setMed2_name(eElement.getElementsByTagName("name").item(0).getTextContent());
                        medObjs.get(j++).setMed2_rxcui(eElement.getElementsByTagName("rxcui").item(0).getTextContent());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int amountofNames(Document doc) {
        int count = 0;
        try {
            NodeList nList = doc.getElementsByTagName("minConcept");
            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    if (temp % 2 == 0) {
                        //    System.out.println("Drug1 : " + eElement.getElementsByTagName("name").item(0).getTextContent());
                    } else {
                        count++;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return count;
    }

    private void parseDesc(Document doc, ArrayList<MedObj> medObjs) {
        try {
            NodeList nList = doc.getElementsByTagName("interactionPair");
            System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    medObjs.get(temp).setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
                    medObjs.get(temp).setSeverity(eElement.getElementsByTagName("severity").item(0).getTextContent());
                    }
                }
          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//Fabian
//NotOliver