package com.manleen.spot.reservation.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Spot {

	private String id;
	private double lat;
	private double lng;
	private boolean reserved;
	private double cost;
	private String reservedBy;
	private String reservedByPhone;
}
