package com.ood.clean.waterball.gracehotel.Model.entity;

public class Answer {
	private int questionId;
	private String deviceUID;
	private String roomNumber;
	private String responses;
	private String email;

	public Answer(int questionId, String responses, String deviceUID, String roomNumber, String email) {
		this.questionId = questionId;
		this.deviceUID = deviceUID;
		this.responses = responses;
		this.roomNumber = roomNumber;
		this.email = email;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
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

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("Answer id: %s, deviceUID: %s, roomNumber: %s, email: %s, responses: %s",
				questionId, deviceUID, roomNumber, email, responses);
	}
}
