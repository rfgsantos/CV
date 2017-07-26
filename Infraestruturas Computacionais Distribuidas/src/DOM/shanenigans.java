package DOM;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class shanenigans {
	
	DocumentBuilderFactory DBF = null;
	DocumentBuilder DB = null;
	Document D = null;
	
	public boolean XMLLoad(String XMLdoc) {
		try {
			// create a DocumentBuilderFactory object
			DBF = DocumentBuilderFactory.newInstance();

			// create a DocumentBuilder object
			DB = DBF.newDocumentBuilder();

			// get the DOM tree
			D = DB.parse(new File(XMLdoc));
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
	
	public boolean XMLSave (String doc) {
		/* implementa��o da escrita da arvore num ficheiro recorrendo ao XSLT */
		try {
			OutputStream output = new FileOutputStream(doc);
			DOMSource domSource = new DOMSource(D);
			StreamResult resultStream = new StreamResult(output);
			TransformerFactory transformFactory = TransformerFactory
					.newInstance();

			// transforma��o vazia

			Transformer transformer = transformFactory.newTransformer();

			transformer
					.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
			if (D.getXmlEncoding() != null)
				transformer.setOutputProperty(OutputKeys.ENCODING,
						D.getXmlEncoding());
			else
				transformer
						.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			try {
				transformer.transform(domSource, resultStream);
			} catch (javax.xml.transform.TransformerException e) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	private enum Tipo{
		ALMOCO, JANTAR
	}
	
	private enum Modo{
		DIAUTIL, FDSFERIADO
	}
	
	private Tipo strToTipo(String tipo){
		if(tipo.compareToIgnoreCase("almoco") == 0){
			return Tipo.ALMOCO;
		}
		if(tipo.compareToIgnoreCase("jantar") == 0){
			return Tipo.JANTAR;
		}
		return null;
	}
	
	private String tipoToStr(Tipo tipo){
		if(Tipo.ALMOCO == tipo){
			return "almoco";
		}
		if(tipo == Tipo.JANTAR){
			return "jantar";
		}
		return null;
	}
	
	public boolean acrescentar(int iditem, Tipo tipo, Modo modo, float preco){
		return true;
	}
	
	public boolean actualizar(int iditem, Tipo tipo, Modo modo, float preco){
		return true;
	}
	
	public boolean remover(int iditem, Tipo tipo, Modo modo){
		return true;
	}
	
	public ArrayList<String> procurar(int idingrediente, Tipo tipo, Modo modo){
		ArrayList resultado = null;
		return resultado;
	}
	
	public static void main(String [] args){
		
	}
	
	
}
