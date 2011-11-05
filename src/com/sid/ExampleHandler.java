package com.sid;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class ExampleHandler extends DefaultHandler{

	private boolean in_pragyan=true;
	private boolean in_type = false;
	private boolean in_name = false;
	private boolean in_photourl = false;
	private boolean in_description=false;
	String tempval;
	public List list = new ArrayList() ;
	 ParsedExampleDataSet parsedExampleDataSet;
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public ParsedExampleDataSet getParsedData() {
		return this.parsedExampleDataSet;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	@Override
	public void startDocument() throws SAXException {
		this.parsedExampleDataSet = new ParsedExampleDataSet();
	}

	@Override
	public void endDocument() throws SAXException {
		// Nothing to do
	}

	/** Gets be called on opening tags like: 
	 * <tag> 
	 * Can provide attribute(s), when xml was like:
	 * <tag attribute="attributeValue">*/
	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {
		 if (localName.equals("pragyan")) {
			this.in_pragyan = true;
		}
		else if (localName.equals("type")) {
			this.in_type = true;
		 parsedExampleDataSet = new ParsedExampleDataSet();

			String attrValue = atts.getValue("name");
			//int i = Integer.parseInt(attrValue);
			parsedExampleDataSet.settype(attrValue);
		}else if (localName.equals("name")) {
			this.in_name = true;
		}else if (localName.equals("imageurl")) {
			this.in_photourl = true;
		}else if (localName.equals("des")) {
			this.in_description = true;
			// Extract an Attribute
		
		}
	}
	
	/** Gets be called on closing tags like: 
	 * </tag> */
	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		 if (localName.equals("pragyan")) {
				this.in_pragyan = false;
				parsedExampleDataSet.setList(list);
			}
		else if (localName.equals("type")) {
			this.in_type = false;
			
			list.add(parsedExampleDataSet);
		}else if (localName.equals("name")) {
			this.in_name = false;
			parsedExampleDataSet.setname(tempval);
		}else if (localName.equals("imageurl")) {
			this.in_photourl = false;
			parsedExampleDataSet.setfileurl(tempval);
		}else if (localName.equals("des")) {
			this.in_description = false;
			parsedExampleDataSet.setdescription(tempval);
		}
	}
	
	/** Gets be called on the following structure: 
	 * <tag>characters</tag> */
	@Override
    public void characters(char ch[], int start, int length) {
		 tempval=new String(ch, start, length);
	    	
}
  public  List getData(){
		
		return list;
		
	}
  
}