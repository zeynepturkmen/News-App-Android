package com.sabanciuniv.model;

public class Message<T> {

	private String serviceMessageCode;
	private T items;
	
	
	public Message() {
		// TODO Auto-generated constructor stub
	}
	
	public Message(String serviceMessageCode, T items) {
		super();
		this.serviceMessageCode = serviceMessageCode;
		this.items = items;
	}
	
	public String getServiceMessageCode() {
		return serviceMessageCode;
	}
	public void setServiceMessageCode(String serviceMessageCode) {
		this.serviceMessageCode = serviceMessageCode;
	}
	public T getItems() {
		return items;
	}
	public void setItems(T items) {
		this.items = items;
	}
	
	
	
	
}
