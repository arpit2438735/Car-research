package com.hashedin.carresearch;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomParser implements ModelParser {

	public List<Model> parse(InputStream xmlFile) {
		
		List<Model> models = new ArrayList<Model>();
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			
			NodeList nodes = doc.getElementsByTagName("Model");
			for (int i = 0; i < nodes.getLength(); i++) {
				
				Element element = (Element)nodes.item(i);
				String year = getTagValue("Year", element);
				String modelName = getTagValue("ModelName", element);
				String make = getTagValue("Make", element);
			
				Element low = (Element)element.getElementsByTagName("Low").item(0);
				Element high = (Element)element.getElementsByTagName("High").item(0);
				
				
				String startPriceAsStr = getTagValue("BaseMSRP", low);
				String endPriceAsStr = getTagValue("BaseMSRP", high);
			
				Model m = new Model();
				m.setMake(make);
				m.setModelName(modelName);
				m.setYear(year);
				m.setMaxPrice(asDobule(endPriceAsStr));
				m.setMinPrice(asDobule(startPriceAsStr));
				
				models.add(m);
			}
		}
		catch (ParserConfigurationException e) {
			
		}
		catch (SAXException e) {
			
		}
		catch (IOException e) {
			
		}		
		return models;
	}
	
	private double asDobule(String str) {
		try {
			return Double.parseDouble(str);
		}
		catch(NumberFormatException nfe) {
			return 0d;
		}
	}

	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();
	}

}
