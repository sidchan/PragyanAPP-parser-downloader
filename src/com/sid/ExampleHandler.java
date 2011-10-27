package com.sid;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class ExampleHandler extends DefaultHandler{

	private boolean in_pragyan=true;
	private boolean in_type = false;
	private boolean in_name = false;
	private boolean in_photourl = false;
	private boolean in_description=false;
	
	private ParsedExampleDataSet parsedExampleDataSet = new ParsedExampleDataSet();

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
		}else if (localName.equals("name")) {
			this.in_name = true;
		}else if (localName.equals("imageurl")) {
			this.in_photourl = true;
		}else if (localName.equals("des")) {
			this.in_description = true;
			// Extract an Attribute
		//	String attrValue = atts.getValue("name");
			//int i = Integer.parseInt(attrValue);
		//	parsedExampleDataSet.setname(attrValue);
		}
	}
	
	/** Gets be called on closing tags like: 
	 * </tag> */
	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		 if (localName.equals("pragyan")) {
				this.in_pragyan = true;
			}
		else if (localName.equals("type")) {
			this.in_type = false;
		}else if (localName.equals("name")) {
			this.in_name = false;
		}else if (localName.equals("imageurl")) {
			this.in_photourl = false;
		}else if (localName.equals("des")) {
			this.in_description = false;
		}
	}
	
	/** Gets be called on the following structure: 
	 * <tag>characters</tag> */
	@Override
    public void characters(char ch[], int start, int length) {
		if(this.in_description){
    		parsedExampleDataSet.setdescription(new String(ch, start, length));
	    	}
		else if(this.in_name)
		{ parsedExampleDataSet.setname(new String(ch, start, length));
    }
		else if(this.in_type)
		{   
			//parsedExampleDataSet.setname(new String(ch, start, length));
		}
    
		else if(this.in_photourl)
		{ parsedExampleDataSet.setfileurl(new String(ch, start, length));
    }
}
}