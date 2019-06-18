package com.manleen.spot.reservation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.manleen.spot.reservation.model.NearbyDTO;
import com.manleen.spot.reservation.model.Spot;
import com.manleen.spot.reservation.registry.SpotRegistry;

@Service
public class SpotService {
	
	private SpotRegistry spotRegistry;
	
	public SpotService(SpotRegistry spotRegistry) {
		this.spotRegistry = spotRegistry;
	}

	public Map<String, Spot> getSpots() {
		return spotRegistry.getSpots();
	}
	
	public List<Spot> getAvailableSpots() {
		return spotRegistry.getSpots()
							.values()
							.stream()
							.filter(spot -> !spot.isReserved())
							.collect(Collectors.toList());
	}
	
	public double getCost(String spotId) throws IllegalArgumentException{
		try {
			return spotRegistry.getSpots()
							.get(spotId)
							.getCost();
		} catch(NullPointerException e) {
			throw new IllegalArgumentException("Please enter a valid spot ID.");
		}
	}
	
	public List<Spot> getNearBySpot(NearbyDTO nearbyDTO) {
		List<Spot> nearBySpots = new ArrayList<>();
		for(Spot spot: spotRegistry.getSpots().values()) {
			double dist = distance(nearbyDTO.getLat(), nearbyDTO.getLng(), spot.getLat(), spot.getLng());
			if(dist<=nearbyDTO.getRadius()) {
				nearBySpots.add(spot);
			}
		}
		return nearBySpots;
	}
	
	private double distance(double lat1, double lon1, double lat2, double lon2) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515 * 1609.344;
			return dist;
		}
	}
}
