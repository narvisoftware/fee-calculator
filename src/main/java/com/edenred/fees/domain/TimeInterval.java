package com.edenred.fees.domain;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.NonNull;

/**
 *
 * @author Mihai
 */
@Getter
public class TimeInterval {

	private LocalTime startTime;
	private LocalTime endTime;

	public TimeInterval(String startTime, String endTime) {
		this(
				LocalTime.parse(startTime, DateTimeFormatter.ISO_LOCAL_TIME),
				LocalTime.parse(endTime, DateTimeFormatter.ISO_LOCAL_TIME));

	}

	public TimeInterval(LocalTime startTime, LocalTime endTime) {
		if (startTime.isAfter(endTime)) {
			throw new IllegalArgumentException("Stat date after end date.");
		}
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public static TimeInterval between(String startTime, String endTime) {
		return new TimeInterval(startTime, endTime);
	}
	
	public boolean isInsideInterval(LocalTime time) {
		if(time.isBefore(startTime)) {
			return false;
		}
		if(time.isAfter(endTime)) {
			return false;
		}
		return true;
	}

}
