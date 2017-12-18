package com.ood.clean.waterball.gracehotel.Model.datamodel;

import com.ood.clean.waterball.gracehotel.Model.domain.TimeItemPool;
import com.ood.clean.waterball.gracehotel.Model.entity.QuestionGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class User implements Serializable{
	private int money;
	private String roomNumber;
	private String deviceId;
	private String email;
	private List<TimeItemPool> timeItemPools; // the item arrangement results of this current user.
	private Collection<Permission> permissions = new ArrayList<>();  // app function permissions the user got
	private Collection<Integer> hasFilledQuestionGroupIds = new HashSet<>();  // record all question group ids the user has filled.

	public User(String roomNumber, String deviceId, String email) {
		this.roomNumber = roomNumber;
		this.deviceId = deviceId;
		this.email = email;
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

	public void setTimeItemPools(List<TimeItemPool> timeItemPools) {
		if (this.timeItemPools != null)
			throw new IllegalStateException("The time item pools has been set.");
		this.timeItemPools = timeItemPools;
	}

	public List<TimeItemPool> getTimeItemPools() {
		return timeItemPools;
	}

	public List<TimeItemPool> getCurrentTimeItemPools(){
		List<TimeItemPool> currentTimeItemPools = new ArrayList<>();
		for(TimeItemPool timeItemPool : timeItemPools)
		{
			Date now = new Date();
			Date start = timeItemPool.getStartTime();
			Date end = new Date();
			end.setTime(start.getTime() + timeItemPool.getDuration());
			if (start.before(now) && end.after(now))
				currentTimeItemPools.add(timeItemPool);
		}
		return currentTimeItemPools;
	}

	/**
	 * @return whether the user has filled the question group and committed.
	 */
	public boolean hasFilled(QuestionGroup questionGroup){
		return this.hasFilledQuestionGroupIds.contains(questionGroup.getId());
	}

	public void addFilledQuestionGroupId(int questionGroupId){
		this.hasFilledQuestionGroupIds.add(questionGroupId);
	}

	@Override
	public String toString() {
		return String.format(Locale.TAIWAN, "User room number %s, deviceId %s, money %d", getRoomNumber(), getDeviceId(), getMoney());
	}
}
