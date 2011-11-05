package com.sid;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;



public class ParsedExampleDataSet  {
	private String name = null;
	private String fileurl = null;
	private String description = null;
	private String type = null;
	 List list;
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public String getdescription() {
		return description;
	}
	public void setdescription(String description) {
		this.description = description;
	}
	public String gettype() {
		return type;
	}
	public void settype(String type) {
		this.type = type;
	}

	public String getfileurl() {
		return fileurl;
	}
	public void setfileurl(String fileurl) {
		this.fileurl = fileurl;
	}
	public void setList(List list) {
		this.list = list;
	}

	public  List<ParsedExampleDataSet> getParsedData(){
		
		return list;
		
	}
	
	
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Event ");
		sb.append("Name:" + getname());
		sb.append("Type ");
		sb.append("Type:" + gettype());
		sb.append(", ");
		sb.append("Url" + getfileurl());
		sb.append(", ");
		sb.append("Description" + getdescription());
		sb.append(".");
		
		return sb.toString();
	}
}
