package com.ood.clean.waterball.gracehotel.Model.domain;


import com.ood.clean.waterball.gracehotel.Model.datamodel.OneHourItemPool;

import java.util.List;

public interface ItemArranger {
	/**
	 * Arrange the item show up times, this is a strategy pattern method.
	 * @param durationDays the days the user stay at the hotel.
	 * @return the list contains all the arranged item show up records.
	 */
	List<OneHourItemPool> arrange(int durationDays);
}
