package com.ood.clean.waterball.gracehotel.Model.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class User implements Serializable{
	private int money;
	private String roomNumber;
	private String deviceId;
	private String email;
	private Collection<Permission> permissions = new ArrayList<>();

	public User(String roomNumber, String deviceId) {
		this.roomNumber = roomNumber;
		this.deviceId = deviceId;

	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Collection<Permission> getPermissions() {
		return permissions;
	}

	public void addPermission(Permission permission){
		getPermissions().add(permission);
	}

	public boolean hasPermission(Permission permission){
		return getPermissions().contains(permission);
	}

	public void setPermissions(Collection<Permission> permissions) {
		this.permissions = permissions;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format(Locale.TAIWAN, "User room number %s, deviceId %s, money %d", getRoomNumber(), getDeviceId(), getMoney());
	}
}
