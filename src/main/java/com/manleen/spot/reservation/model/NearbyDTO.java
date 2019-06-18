package com.manleen.spot.reservation.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NearbyDTO {

	private double lat;
	private double lng;
	private double radius;
	
	public NearbyDTO() {
		
	}
	
	public NearbyDTO(double lat, double lng, double radius) {
		this.lat = lat;
		this.lng = lng;
		this.radius = radius;
	}
}
