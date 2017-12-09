package com.ood.clean.waterball.gracehotel.Model.datamodel;

public class Permission {

	private PermissionName name;

	private boolean enabled;

	private int cost;

	public PermissionName getName() {
		return name;
	}

	public void setName(PermissionName name) {
		this.name = name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}
