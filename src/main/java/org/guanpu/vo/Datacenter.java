package org.guanpu.vo;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Datacenter {
	private int datacenterId;
	private String location;
	private String abbr;
	
	
	public int getDatacenterId() {
		return datacenterId;
	}
	
	@JsonSetter("DATACENTERID")
	public void setDatacenterId(int datacenterId) {
		this.datacenterId = datacenterId;
	}
	
	public String getLocation() {
		return location;
	}
	
	@JsonSetter("LOCATION")
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getAbbr() {
		return abbr;
	}
	
	@JsonSetter("ABBR")
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	
	
}
