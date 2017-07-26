package DOM;


import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLDoc {
	
	public XMLDoc(){
		createXML();
	}
	
	
	public void createXML(){
		try{
			//criação do documento

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			//cria o documento e o elemento raiz
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Restaurante");
			doc.appendChild(rootElement);
			
			Element entradas = doc.createElement("Entradas");
			rootElement.appendChild(entradas);
			String cenas = "bola";
			Element descricao = doc.createElement("descricao");
			Element ingrediente = doc.createElement("ingrediente");
			descricao.setTextContent("arroz");
			descricao.setAttribute("id", cenas);
			ingrediente.setAttribute("id", cenas);
			descricao.appendChild(ingrediente);
			entradas.appendChild(descricao);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("C:\\Users\\Denga\\Desktop\\file.xml"));
			
			transformer.transform(source, result);

			System.out.println("File saved!");
		
		}
		catch(ParserConfigurationException pce){
			pce.printStackTrace();
		}
		catch(TransformerException tfe){
			tfe.printStackTrace();
		}
		
	}
	
	public void readXML(){
		
	}
	
	public static void main(String [] args){
		new XMLDoc();
	}
	

}
