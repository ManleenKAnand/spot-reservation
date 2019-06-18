package com.manleen.spot.reservation.registry;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.manleen.spot.reservation.model.Spot;

@Component
public class SpotRegistry {
	
	public SpotRegistry(){
		buildSpotsRegistry();
	}

	private Map<String, Spot> spots = new HashMap<>();
	
	private void buildSpotsRegistry() {
		Spot spot1 = Spot.builder()
							.id("A1")
							.lat(1.22)
							.lng(5.34)
							.reserved(false)
							.cost(5)
							.build();
		spots.put(spot1.getId(), spot1);
		
		Spot spot2 = Spot.builder()
							.id("A2")
							.lat(6.62)
							.lng(8.90)
							.reserved(true)
							.reservedBy("Will Smith")
							.cost(10)
							.build();
		spots.put(spot2.getId(), spot2);
		
		Spot spot3 = Spot.builder()
							.id("A3")
							.lat(7.12)
							.lng(1.67)
							.reserved(true)
							.reservedBy("Leo Caprio")
							.cost(10)
							.build();
		spots.put(spot3.getId(), spot3);
		
		Spot spot4 = Spot.builder()
							.id("A4")
							.lat(5.45)
							.lng(6.56)
							.reserved(false)
							.cost(5)
							.build();
		spots.put(spot4.getId(), spot4);
	}
	
	public Map<String, Spot> getSpots() {
		return spots;
	}
}
