package com.ood.clean.waterball.gracehotel.Model.entity;

public class Answer {
	private int id;
	private String deviceUID;
	private String responses;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResponses() {
		return responses;
	}

	public void setResponses(String responses) {
		this.responses = responses;
	}

	public String getDeviceUID() {
		return deviceUID;
	}

	public void setDeviceUID(String deviceUID) {
		this.deviceUID = deviceUID;
	}
}
