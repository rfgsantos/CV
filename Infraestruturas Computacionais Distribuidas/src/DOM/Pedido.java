package DOM;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
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


public class Pedido {

	final static String DIRETORIA = "WebContent\\Pedidos.xml";
	private DocumentBuilderFactory DBF;
	private DocumentBuilder DB;
	private Document D;
	private int idPedido;
	private File XML;
	// CORRESPONDE AO IP DO CLIENTE
	private String idCliente;
	private Ementa ementa;

	public Pedido(String idCliente) {
		// TODO - O PEDIDO TEM UMA DATA E UM ESTADO CORRENTE
		this.ementa = new Ementa();
		this.idCliente = idCliente;
		this.idPedido = 0;
		this.DBF = null;
		this.DB = null;
		this.D = null;
		this.XML = new File(DIRETORIA);
		// criacao do pedido
		leXML(XML);
		// System.out.println(
		// "DEBUG------------------- numero de filhos : " +
		// D.getDocumentElement().getChildNodes().getLength());
		if(!existeIdClienteNoXML(idCliente)){
			atribuiNumeroPedido();
			criarPedidoXML();
		}else{
			idPedido = returnIDPedidoCliente(idCliente);
		}
		
		// System.out.println("Numero do pedido atual: " + idPedido);
//		criarPedidoXML();
	}
	public String getIdCliente(){
		return this.idCliente;
	}
	
	private int returnIDPedidoCliente(String ip){
		try {
			XPath xpath = XPathFactory.newInstance().newXPath();
			String expressao = "/pedidos/pedido";
			NodeList pedido = (NodeList) xpath.compile(expressao).evaluate(D, XPathConstants.NODESET);
			for(int i=0;i<pedido.getLength();i++){
				Element elemento = (Element) pedido.item(i);
				if(elemento.getAttribute("cliente").equals(ip)){
					return Integer.parseInt(elemento.getAttribute("id"));
				}
			}


		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public boolean existeIdClienteNoXML(String ip){
		try {
			XPath xpath = XPathFactory.newInstance().newXPath();
			String expressao = "/pedidos/pedido";
			NodeList pedido = (NodeList) xpath.compile(expressao).evaluate(D, XPathConstants.NODESET);
			for(int i=0;i<pedido.getLength();i++){
				Element elemento = (Element) pedido.item(i);
				System.out.println("DEBUG IP PEDIDO: " + ip);
				System.out.println("ATRIBUTO DO PEDIDO: " + elemento.getAttribute("cliente"));
				if(elemento.getAttribute("cliente").equals(ip)){
					return true;
				}
			}


		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private void atribuiNumeroPedido() {
		// Atribui um numero com base no numero do ultimo pedido no ficheiro XML
		try {
			if (D.getDocumentElement().getChildNodes().getLength() > 2) {
				XPath pedidos = XPathFactory.newInstance().newXPath();
				String expressao = "/pedidos";
				NodeList nl = (NodeList) pedidos.compile(expressao).evaluate(D, XPathConstants.NODE);
				Element ultimoElemento = (Element) nl.item(nl.getLength() - 2);
				idPedido = Integer.parseInt(ultimoElemento.getAttribute("id")) + 1;
			} else {
				idPedido = 1;
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}

	private void leXML(File file) {
		// carrega o ficheiro de XML para o codigo
		try {
			DBF = DocumentBuilderFactory.newInstance();
			DB = DBF.newDocumentBuilder();
			D = DB.parse(file);

		} catch (javax.xml.parsers.ParserConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (org.xml.sax.SAXException e) {
			System.out.println(e.getMessage());
		} catch (java.io.IOException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public ArrayList<Integer> idElementosPedido(){
		
		try {
			ArrayList<Integer> elementos = new ArrayList<Integer>();
			XPath xpath = XPathFactory.newInstance().newXPath();
			String expressao = "/pedidos/pedido[@cliente="+idCliente+"]/produto";
			NodeList pedido = (NodeList) xpath.compile(expressao).evaluate(D, XPathConstants.NODESET);
			for(int i=0;i<pedido.getLength();i++){
				Element produto = (Element) pedido.item(i);
				int ID = Integer.parseInt(produto.getAttribute("refid"));
				elementos.add(ID);
				
			}
			return elementos;

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	public String consultaPedido() {
		try {
			String pedidoFinal = "";
			XPath xpath = XPathFactory.newInstance().newXPath();
			String expressao = "/pedidos/pedido[@cliente="+idCliente+"]/produto";
			NodeList pedido = (NodeList) xpath.compile(expressao).evaluate(D, XPathConstants.NODESET);
			
			String expressao2 = "/pedidos/pedido[@cliente="+idCliente+"]";
			Element elementoPedido = (Element) xpath.compile(expressao2).evaluate(D, XPathConstants.NODE);
			String estado = elementoPedido.getAttribute("estado");
			System.out.println("DEBUG ESTADO CONSULTA PEDIDO: " + estado);
			for(int i=0;i<pedido.getLength();i++){
				Element produto = (Element) pedido.item(i);
				int ID = Integer.parseInt(produto.getAttribute("refid"));
				pedidoFinal += ementa.procuraItem(ID) + ":";
			}
			return pedidoFinal+estado;

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return "DEBUG PEDIDO";
	}
	
	
	
	public void mudaEstadoPedido(String id, String muda){
		
		
		try {
			XPath xpath = XPathFactory.newInstance().newXPath();
			String expressao = "/pedidos/pedido[@cliente='"+id+"']";
			Node pedido = (Node) xpath.compile(expressao).evaluate(D, XPathConstants.NODE);
			Element elemento = (Element) pedido;
			System.out.println("ELEMENTO ATRIBUTO ESTADO: " + elemento.getAttribute("estado"));
			elemento.setAttribute("estado", muda);
			System.out.println("ELEMENTO ATRIBUTO ESTADO 2 : " + elemento.getAttribute("estado"));

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

			DOMSource source = new DOMSource(D);
			StreamResult result = new StreamResult(new File(DIRETORIA));

			transformer.transform(source, result);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}catch (TransformerException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public boolean insereElementoPedido(int idElemento) {
		// insere um elemento que e servido, no seu pedido

		try {
			String pedido = "/pedidos/pedido[@cliente="+idCliente+"]";
			XPath xpath = XPathFactory.newInstance().newXPath();
			Node noPedido = (Node) xpath.compile(pedido).evaluate(D, XPathConstants.NODE);
			Element elementoInserir = D.createElement("produto");
			elementoInserir.setAttribute("refid", Integer.toString(idElemento));
			noPedido.appendChild(elementoInserir);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

			DOMSource source = new DOMSource(D);
			StreamResult result = new StreamResult(new File(DIRETORIA));

			transformer.transform(source, result);
			
			return true;
			
			
		} catch (XPathExpressionException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

	public void criarPedidoXML() {
		// criacao do pedido no XML
		try {
			Node rootPedidos = D.getDocumentElement();
			Element pedido = D.createElement("pedido");
			String novoId = Integer.toString(idPedido);
			pedido.setAttribute("id", novoId);
			pedido.setAttribute("cliente", idCliente);
			pedido.setAttribute("estado", "aceitar");

			rootPedidos.appendChild(pedido);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

			DOMSource source = new DOMSource(D);
			StreamResult result = new StreamResult(new File(DIRETORIA));

			transformer.transform(source, result);

		
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		
	}


	public boolean removePedido() {

		// remove o elemento da base de dados
		try {

			String expressao = "/pedidos/pedido[@cliente=" + idCliente + "]";
			XPath xpath = XPathFactory.newInstance().newXPath();
			Element pedido = (Element) xpath.compile(expressao).evaluate(D, XPathConstants.NODE);
			pedido.getParentNode().removeChild(pedido);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(D);
			StreamResult result = new StreamResult(new File(DIRETORIA));

			transformer.transform(source, result);

			return true;

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		return false;

	}

	public static void main(String[] args) {

		Pedido pedido = new Pedido("127002");
//		System.out.println(pedido.consultaPedido());
		Ementa em = new Ementa();
		
		pedido.insereElementoPedido(em.procuraIdItem("Lasagna"));
		pedido.insereElementoPedido(em.procuraIdItem("Lasagna"));
		pedido.insereElementoPedido(em.procuraIdItem("Lasagna"));
		pedido.insereElementoPedido(em.procuraIdItem("Lasagna"));
		pedido.insereElementoPedido(em.procuraIdItem("Lasagna"));
		pedido.insereElementoPedido(em.procuraIdItem("Lasagna"));
		
		System.out.println(pedido.consultaPedido());
		
		pedido.mudaEstadoPedido("127002","mudanasdljjgkjsd vjk");
		
//		ArrayList<Integer> cenas = pedido.idElementosPedido();
//		for(int i=0; i<cenas.size(); i++){
//			System.out.println(cenas.get(i));
//		}
//		pedido.insereElementoPedido(11);


//		System.out.println(pedido.consultaPedido());
//		pedido.removePedido();

	}

}
