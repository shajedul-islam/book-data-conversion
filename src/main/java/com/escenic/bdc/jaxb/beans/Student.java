package main.java.com.escenic.bdc.jaxb.beans;

import java.util.List;

public class Student {

	private List<String> name;
	private String id;
	
	
	public List<String> getName() {
		return name;
	}
	public void setName(List<String> name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
