package DOM;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Enumerators.ModoPrato;
import Enumerators.TipoEmenta;

public class Ementa {
	//XML EMENTA
	final static String DIRETORIA = "WebContent\\Ementa.xml";
	DocumentBuilderFactory DBF;
	DocumentBuilder DB;
	Document D;
	
	//XML EMPREGADOS
	final static String DIRETORIA2 = "WebContent\\Empregados.xml";
	DocumentBuilderFactory DBF2;
	DocumentBuilder DB2;
	Document D2;
	
	//XML PEDIDOS
	final static String DIRETORIA3 = "WebContent\\Pedidos.xml";
	DocumentBuilderFactory DBF3;
	DocumentBuilder DB3;
	Document D3;
	
	public Ementa(){
		
		DBF = null;
		DB = null;
		D = null;
		
		DBF2 = null;
		DB2 = null;
		D2 = null;
		
		DBF3 = null;
		DB3 = null;
		D3 = null;
		
		XMLoad(DIRETORIA);
		XMLoadEmpregados(DIRETORIA2);
		XMLoadPedidos(DIRETORIA3);
		
		
	}
	
	public boolean XMLoad(String path){
		try{
			DBF = DocumentBuilderFactory.newInstance();
			DB = DBF.newDocumentBuilder();
			//object that holds XML
			D = DB.parse(new File(path));
			return true;
		} catch (javax.xml.parsers.ParserConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (org.xml.sax.SAXException e) {
			System.out.println(e.getMessage());
		} catch (java.io.IOException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean XMLoadEmpregados(String path){
		try{
			DBF2 = DocumentBuilderFactory.newInstance();
			DB2 = DBF2.newDocumentBuilder();
			//object that holds XML
			D2 = DB2.parse(new File(path));
			return true;
		} catch (javax.xml.parsers.ParserConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (org.xml.sax.SAXException e) {
			System.out.println(e.getMessage());
		} catch (java.io.IOException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean XMLoadPedidos(String path){
		try{
			DBF3 = DocumentBuilderFactory.newInstance();
			DB3 = DBF3.newDocumentBuilder();
			//object that holds XML
			D3 = DB3.parse(new File(path));
			return true;
		} catch (javax.xml.parsers.ParserConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (org.xml.sax.SAXException e) {
			System.out.println(e.getMessage());
		} catch (java.io.IOException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	
	public int procuraIdItem(String item){
		try {
			XPath xpath = XPathFactory.newInstance().newXPath();
			String expressao = "/restaurante/items/item";
			NodeList elementos = (NodeList) xpath.compile(expressao).evaluate(D, XPathConstants.NODESET);
			for(int i=0;i<elementos.getLength();i++){
				Element elemento = (Element)elementos.item(i);
				if(elemento.getTextContent().equals(item)){
					return Integer.parseInt(elemento.getAttribute("iditem"));
				}
			}
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	
	public String procuraItem(int idItem){

		try {
			XPath xpath = XPathFactory.newInstance().newXPath();
			String expressao = "/restaurante/items/item[@iditem="+idItem+"]";
			Element elemento = (Element) xpath.compile(expressao).evaluate(D, XPathConstants.NODE);
			return elemento.getTextContent();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "debug";
	}
	
	public String devolvePreco(ModoPrato modo, TipoEmenta tipo, int refId){
		
		try {
			XPath xpath = XPathFactory.newInstance().newXPath();
			String newModo = modo.toStringModo(modo);
			String newTipo = tipo.toStringementa(tipo);
			String caminho = "/restaurante/ementas/ementa[@modo='"+newModo+"'][@tipo='"+newTipo+"']/serve";
			NodeList nl = (NodeList) xpath.compile(caminho).evaluate(D, XPathConstants.NODESET);
			
			
			for(int i=0;i<nl.getLength();i++){
				Element elemento = (Element) nl.item(i).getFirstChild();
				int ID = Integer.parseInt(elemento.getAttribute("refid"));
				if(ID == refId){
					return elemento.getAttribute("preco");
				}
				
			}

		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return "debug";
	}
	


	
	public String consultaEmenta(ModoPrato modo, TipoEmenta tipo){
		//usar o metodo procurar

		try {
			XPath xpath = XPathFactory.newInstance().newXPath();
			String newModo = modo.toStringModo(modo);
			String newTipo = tipo.toStringementa(tipo);
			String caminho = "/restaurante/ementas/ementa[@modo='"+newModo+"'][@tipo='"+newTipo+"']/serve";
			NodeList nl = (NodeList) xpath.compile(caminho).evaluate(D, XPathConstants.NODESET);
			
			String ementaCompleta = "";
			for(int i=0;i<nl.getLength();i++){
				Element elemento = (Element) nl.item(i).getFirstChild();
				int ID = Integer.parseInt(elemento.getAttribute("refid"));
				String preco = elemento.getAttribute("preco");
				ementaCompleta += procuraItem(ID)+"_" + preco+":"; //tiramos daqui o break line
			}
			
			return ementaCompleta;

			
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "debug";
	}
	
	//metodos do empregado ///
	
	public boolean verificarUser(String user, String pass){
		try {
			XPath xpath = XPathFactory.newInstance().newXPath();
			String expressao = "/empregados/empregado";
			NodeList filhos = (NodeList) xpath.compile(expressao).evaluate(D2, XPathConstants.NODESET);
//			System.out.println("tamanho primeira lista : " + filhos.getLength());
			for(int i=0;i<filhos.getLength();i++){
				NodeList filho = filhos.item(i).getChildNodes();
//				System.out.println("tamanho lista filho: " + filho.getLength());
				String aux = "";
				for(int j=0; j<filho.getLength(); j++){
					if(filho.item(j).getNodeType() == Node.ELEMENT_NODE){
						Element elemento = (Element) filho.item(j);
//						System.out.println("content do filho: " + elemento.getTextContent());
						if(elemento.getTextContent().equals(user)){
							aux += "t";
						}
						if(elemento.getTextContent().equals(pass)){
							aux += "t";
						}
						if(aux.equals("tt") && j<filho.getLength()-1){
							return true;
						}
					}
				}
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	public boolean atribuiEstrela(String valor, String empregado){
		try{
			XPath xpath = XPathFactory.newInstance().newXPath();
			String caminho = "/empregados/empregado[@nome='"+empregado+"']/rating";
			Node noempregado = (Node) xpath.compile(caminho).evaluate(D2, XPathConstants.NODE);
		
			Element elementoInserir = D2.createElement("estrela");
			elementoInserir.setAttribute("value", valor);
			noempregado.appendChild(elementoInserir);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

			DOMSource source = new DOMSource(D2);
			StreamResult result = new StreamResult(new File(DIRETORIA2));

			transformer.transform(source, result);
			
			return true;

		
		} catch (XPathExpressionException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		 catch (TransformerException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	
	public float devolveMediaEstrelas(String empregado){
		try {
			XPath xpath = XPathFactory.newInstance().newXPath();
			String expressao = "/empregados/empregado[@nome='"+empregado+"']/rating/estrela";
			NodeList estrelas = (NodeList) xpath.compile(expressao).evaluate(D2, XPathConstants.NODESET);
			float aux = 0;
			float total = (float) estrelas.getLength();
			for(int i=0; i<estrelas.getLength(); i++){
				Element elemento = (Element) estrelas.item(i);
				float value = Float.parseFloat(elemento.getAttribute("value"));
				aux += value;
			}
			
			return aux/total;
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	public String getEmpregado(int idEmpregado){
		
		try {
			XPath xpath = XPathFactory.newInstance().newXPath();
			String expressao = "/empregados/empregado[@id="+idEmpregado+"]";
			Element elemento = (Element) xpath.compile(expressao).evaluate(D2, XPathConstants.NODE);
			return elemento.getAttribute("nome");
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "DEBUG GET EMPREGADO";
		
		
	}
	
	public String todosPedidos(){
		
		try {
			String pedido = "/pedidos/pedido";
			XPath xpath = XPathFactory.newInstance().newXPath();
			NodeList noPedidos = (NodeList) xpath.compile(pedido).evaluate(D3, XPathConstants.NODESET);
			String pedidos = "";
			for(int i =0;i<noPedidos.getLength();i++){
				if(noPedidos.item(i).getNodeType() == Node.ELEMENT_NODE){
					Element elemento = (Element) noPedidos.item(i);
					pedidos += elemento.getAttribute("cliente") + "_" + elemento.getAttribute("estado") + ":";
				}
			}
			
			return pedidos;
			
			
			
		} catch (XPathExpressionException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		return "DEBUG TODOS OS PEDIDOS";
	}
			

	
	public static void main(String []args){
		Ementa eme = new Ementa();
		
		System.out.println(eme.todosPedidos());
//		System.out.println(eme.devolvePreco(ModoPrato.DIASEMANA, TipoEmenta.ALMOCO, 5));
//		System.out.println(eme.consultaEmenta(ModoPrato.DIASEMANA, TipoEmenta.ALMOCO));
//		
//		System.out.println(eme.atribuiEstrela("3", "Joaquim"));
//		
//		System.out.println(eme.verificarUser("abel", "cenas"));
//		
//		System.out.println(eme.getEmpregado(3));
//		
//		System.out.println(eme.devolveMediaEstrelas("Joaquim"));
		
	}
	
	
}
