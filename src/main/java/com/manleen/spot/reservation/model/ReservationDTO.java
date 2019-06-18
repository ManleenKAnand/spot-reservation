package com.manleen.spot.reservation.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReservationDTO {
	
	private String spotId;
	private String userName;
	private String phone;
	
	public ReservationDTO() {
		
	}

	public ReservationDTO(String spotId, String userName, String phone) {
		this.spotId = spotId;
		this.userName = userName;
		this.phone = phone;
	}	
	
}
