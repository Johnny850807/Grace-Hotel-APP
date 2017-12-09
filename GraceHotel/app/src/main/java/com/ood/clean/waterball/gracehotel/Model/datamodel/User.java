package com.ood.clean.waterball.gracehotel.Model.datamodel;

import java.io.Serializable;
import java.util.Collection;
import java.util.Locale;

public class User implements Serializable{
	private int money;
	private String roomNumber;
	private String deviceId;
	private Collection<Permission> permission;

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

	public Collection<Permission> getPermission() {
		return permission;
	}

	public void addPermission(Permission permission){
		getPermission().add(permission);
	}

	public boolean hasPermission(Permission permission){
		return getPermission().contains(permission);
	}

	public void setPermission(Collection<Permission> permission) {
		this.permission = permission;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public String toString() {
		return String.format(Locale.TAIWAN, "User room number %s, deviceId %s, money %d", getRoomNumber(), getDeviceId(), getMoney());
	}
}
