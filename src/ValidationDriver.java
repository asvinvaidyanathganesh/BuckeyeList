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
		//Schema
		boolean flag = true;
		try {
			validateWithSchema("account.xml","account.xsd");
		} catch (SAXException e) {
			flag = false;
		} catch (IOException e) {
			flag = false;
		}
		
		System.out.println("XML and Schema "+flag);
		
		
		// DTD
		flag = true;
		try {
			validateWithDTD("account.xml");
		} catch (FileNotFoundException e) {
			flag = false;
		} catch (ParserConfigurationException e) {
			flag = false;
		} catch (SAXException e) {
			flag = false;
		} catch (IOException e) {
			flag = false;
		}
		System.out.println("XML and DTD "+flag);
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
