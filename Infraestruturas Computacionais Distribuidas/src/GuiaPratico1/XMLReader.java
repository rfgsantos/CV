package GuiaPratico1;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {

	public XMLReader() {
		File file = new File("C:\\Users\\denga\\Desktop\\items.xml");
		readFileXML(file);
	}

	public void readFileXML(File inputfile) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputfile);
			
			Element root = doc.getDocumentElement();
			
			System.out.println("root element: "+root.getNodeName());
			System.out.println("-------------------------");
			
			NodeList nl = doc.getElementsByTagName("Entradas");
			System.out.println("comprimento da lista: " + nl.getLength());
			
			for(int i=0; i<nl.getLength();i++){
				Node currentNode = nl.item(i);
				System.out.println("\nCurrent element: ");
				System.out.println(currentNode.getNodeName());
				if(currentNode.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) currentNode;
					System.out.println("Atributo: "+eElement.getAttribute("tipo"));
					NodeList nl2 = eElement.getChildNodes();
					
					for(int j=0;j<nl2.getLength();j++){
						Node currentNode2 = nl2.item(j);
						if(currentNode2.getNodeType() == Node.ELEMENT_NODE){
							Element entrada = (Element) currentNode2;
							System.out.println("Nome: "+entrada.getTextContent());
							System.out.println("Atributo: "+entrada.getAttribute("tipo"));
						}
					}
				}
				
			}
			
			
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}	
	}
	public static void main(String [] args){
		new XMLReader();
	}

}
