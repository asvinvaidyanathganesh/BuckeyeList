///////////////////////////////////////////////////////////////////////////////
// File: ValidationDriver.java
// Version: 0.1
// Date: Oct 7 2014
// Class: ValidationDriver
//	This class validates the XML against the Schema and DTD
// Authors: Asvin Vaidyanath Ganesh & Bryan DAgostino
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ValidationDriver {

	public static void main(String[] args) {
		boolean flag = true;
		
		try {
			validateWithSchema("person.xml","person.xsd");
		} catch (SAXException e) {
			flag = false;
		} catch (IOException e) {
			flag = false;
		}
		
		System.out.println(flag);
		
		
		// DTD
		
		try {
			validateWithDTD("person.xml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void validateWithSchema(String xmlFile, String validationFile) throws SAXException, IOException{
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		((schemaFactory.newSchema(new File(validationFile))).newValidator()).validate(new StreamSource(new File(xmlFile)));
	}
	
	public static void validateWithDTD(String xmlFile) throws ParserConfigurationException, FileNotFoundException, SAXException, IOException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setValidating(true);
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		documentBuilder.setErrorHandler(new org.xml.sax.ErrorHandler(){
			public void error(SAXParseException exception) throws SAXException{
				throw exception;
			}
			public void fatalError(SAXParseException exception) throws SAXException{
				throw exception;
			}
			
			public void warning(SAXParseException exception) throws SAXException{
				throw exception;
			}
		});
		documentBuilder.parse(new FileInputStream(xmlFile));
		
	}


}
