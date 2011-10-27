package com.sid;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;



public class ParsedExampleDataSet implements  List<ParsedExampleDataSet> {
	private String name = null;
	private String fileurl = null;
	private String description = null;
	private String type = null;

	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public String getdescription() {
		return description;
	}
	public void setdescription(String name) {
		this.name = name;
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
	/*public ParsedExampleDataSet getpeds(){
		return parsedExampleSetData ;
	}
	*/
	public boolean add(ParsedExampleDataSet object) {
		// TODO Auto-generated method stub
		return false;
	}
	public void add(int location, ParsedExampleDataSet object) {
		// TODO Auto-generated method stub
		
	}
	public boolean addAll(Collection<? extends ParsedExampleDataSet> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean addAll(int arg0,
			Collection<? extends ParsedExampleDataSet> arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	public boolean contains(Object object) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	public ParsedExampleDataSet get(int location) {
		// TODO Auto-generated method stub
		return null;
	}
	public int indexOf(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	public Iterator<ParsedExampleDataSet> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	public int lastIndexOf(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}
	public ListIterator<ParsedExampleDataSet> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}
	public ListIterator<ParsedExampleDataSet> listIterator(int location) {
		// TODO Auto-generated method stub
		return null;
	}
	public ParsedExampleDataSet remove(int location) {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean remove(Object object) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	public ParsedExampleDataSet set(int location, ParsedExampleDataSet object) {
		// TODO Auto-generated method stub
		return null;
	}
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	public List<ParsedExampleDataSet> subList(int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}
	public <T> T[] toArray(T[] array) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString(){
		return "ExtractedString = " + this.name + " description " + this.description
				+ "FileURL = " + this.fileurl;
	}
}
