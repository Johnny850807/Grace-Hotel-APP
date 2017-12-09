package com.ood.clean.waterball.gracehotel.Model.datamodel;

import java.util.Collection;

public class User {

	private int money;

	private String roomNumber;

	private Collection<Permission> permission;

	public User(String roomNumber) {
		this.roomNumber = roomNumber;
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

	public boolean hasPermission(PermissionName permissionName){
		return getPermission().contains(permissionName);
	}

	public void setPermission(Collection<Permission> permission) {
		this.permission = permission;
	}
}
