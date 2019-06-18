package com.manleen.spot.reservation.service;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.manleen.spot.reservation.model.ReservationDTO;
import com.manleen.spot.reservation.model.Spot;
import com.manleen.spot.reservation.registry.SpotRegistry;

@Service
public class ReservationService {

	private SpotRegistry spotRegistry;
	
	public ReservationService(SpotRegistry spotRegistry) {
		this.spotRegistry = spotRegistry;
	}
	
	public Map<String, String> getReservations() {
		return spotRegistry.getSpots()
							.values()
							.stream()
							.filter(spot -> spot.isReserved())
							.collect(Collectors.toMap(Spot::getId, Spot::getReservedBy));
	}
	
	public Spot reserveSpot(ReservationDTO reservation) throws IllegalArgumentException{
		if(!isValid(reservation.getPhone())) {
			throw new IllegalArgumentException("Please enter a valid phone number");
		}
		try {
			String spotId = reservation.getSpotId();
			setReservation(spotId, true, reservation.getUserName(), reservation.getPhone());
			return spotRegistry.getSpots().get(spotId);
		} catch(NullPointerException e) {
			throw new IllegalArgumentException("Please enter a valid spot ID.");
		}
		
	}
	
	public Spot cancelReservation(String spotId) throws IllegalArgumentException{
		setReservation(spotId, false, "", "");
		return spotRegistry.getSpots().get(spotId);
	}
	
	private void setReservation(String spotId, boolean reserveStatus, String reservedBy, String phone) throws IllegalArgumentException{
		try {
			Spot spot = spotRegistry.getSpots().get(spotId);
			spot.setReserved(reserveStatus);
			spot.setReservedBy(reservedBy);
			spot.setReservedByPhone(phone);
		} catch(NullPointerException e) {
			throw new IllegalArgumentException("Please enter a valid spot ID.");
		}
	}
	
	private boolean isValid(String phone) {
		return phone.matches("(0/91)?[7-9][0-9]{9}");
	}
}
