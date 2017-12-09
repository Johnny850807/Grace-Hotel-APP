package com.ood.clean.waterball.gracehotel.Model;


import com.ood.clean.waterball.gracehotel.Model.datamodel.Permission;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;

public interface UserRepository {

	public abstract boolean userExists();

	public abstract User getUser();

	public abstract User createUser(String roomNumber);

	public abstract void addMoney(User user, int point);

	public abstract void buyPermission(User user, Permission permission);

}
