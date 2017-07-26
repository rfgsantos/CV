package DOM;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLRead {

	public XMLRead(File inputfile){
		percorreXML(inputfile);
	}
	
	public void percorreXML(File inputfile){
		try{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(inputfile);
			
			Node root = document.getDocumentElement();
			System.out.println("DEBUG-------------- numero de nos filhos: " + root.getChildNodes().getLength());
			
			transverse(root);

		}catch(Exception e){
			System.err.println(e);
		}
	} 
	
	public void transverse(Node node){
		System.out.println("node: " + node.getNodeName());
		System.out.println("node text: " + node.getNodeValue());
		if(node.hasChildNodes()){
			NodeList children = node.getChildNodes();
			for(int i=0;i<children.getLength(); i++){
				if(children.item(i).getNodeType() == Node.ELEMENT_NODE){
					Node child = children.item(i);
					System.out.println("Current node: " + child.getNodeName());
					System.out.println("Has atributes? : " + child.hasAttributes());
					if(child.getNodeName() == "restaurante"){
						if(child.hasAttributes()){
							System.out.println("entrou no if");
						}
					}
					
					if(child.hasAttributes()){
						Element elemento = (Element) child;
						if(elemento.hasAttribute("refid")){
							System.out.println("Atributo ID: " + elemento.getAttribute("refid"));
						}else{
							System.out.println("Atributo ID: " + elemento.getAttribute("id"));
						}
						
						if(elemento.getTagName()=="ementa"){
							System.out.println("Atributo MODO: " + elemento.getAttribute("modo"));
						}
					}
					transverse(child);
				}
				
				
//				if(children.item(i).hasAttributes()){
//					Element elemento = (Element) children.item(i);
//					System.out.println("atributo: " + elemento.getAttribute("id"));
//				}
				
				
			}
		}
	}
	
	
	
	public static void main(String [] args){
		File file = new File("C:\\Users\\Denga\\Desktop\\items.xml");
		new XMLRead(file);

	}

}
