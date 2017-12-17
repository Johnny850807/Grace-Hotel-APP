package com.ood.clean.waterball.gracehotel.Model;


import com.ood.clean.waterball.gracehotel.Model.datamodel.Permission;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;

public interface UserRepository {

	public boolean userExists();

	public User getUser();

	public User createUser(String roomNumber);

	public void addMoney(User user, int point);

	public void buyPermission(User user, Permission permission);

	public void addFilledQuestionGroupId(User user, int questionGroupId);

}
