package com.manleen.spot.reservation.service;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.manleen.spot.reservation.model.NearbyDTO;
import com.manleen.spot.reservation.model.Spot;
import com.manleen.spot.reservation.registry.SpotRegistry;

@RunWith(SpringRunner.class)
public class SpotServiceTest {
	
	Map<String, Spot> spots = new HashMap<>();
	@Mock
	private SpotRegistry spotRegistry;
	@InjectMocks
	private SpotService spotService = new SpotService(spotRegistry);
	
	@Before
	public void setUp(){
		buildSpots();
		when(spotRegistry.getSpots()).thenReturn(spots);
	}
	
	@Test
	public void shouldGetAvailableSpots() {
		List<Spot> spots = spotService.getAvailableSpots();
		assertNotNull(spots);
		assertEquals(1, spots.size());
	}
	
	@Test
	public void shouldGetCostOfSpot() {
		assertEquals((Object) 5.0,spotService.getCost("A1"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionToGetCostOfInvalidSpotId() {
		assertEquals((Object) 5.0,spotService.getCost("A8"));
	}
	
	@Test
	public void shouldGetNearBySpots() {
		NearbyDTO nearbyDTO = NearbyDTO.builder()
										.lat(1.22)
										.lng(5.34)
										.radius(2)
										.build();
		List<Spot> spots = spotService.getNearBySpot(nearbyDTO);
		assertNotNull(spots);
		assertEquals(1, spots.size());
	}
	
	private void buildSpots() {
			
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
		}

}
