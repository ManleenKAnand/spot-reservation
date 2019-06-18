package com.manleen.spot.reservation.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.manleen.spot.reservation.model.ReservationDTO;
import com.manleen.spot.reservation.model.Spot;
import com.manleen.spot.reservation.registry.SpotRegistry;

@RunWith(SpringRunner.class)
public class ReservationServiceTest {

	Map<String, Spot> spots = new HashMap<>();
	@Mock
	private SpotRegistry spotRegistry;
	@InjectMocks
	private ReservationService reservationService = new ReservationService(spotRegistry);
	
	@Before
	public void setUp(){
		buildSpots();
		when(spotRegistry.getSpots()).thenReturn(spots);
	}
	
	@Test
	public void shouldGetReservations() {
		Map<String, String> reservations = reservationService.getReservations();
		assertNotNull(reservations);
	}
	
	@Test
	public void shouldReserveSpot() {
		ReservationDTO reservation = ReservationDTO.builder()
													.spotId("A1")
													.userName("Manleen A")
													.phone("7873923408")
													.build();
		reservationService.reserveSpot(reservation);
		assertTrue(spots.get("A1").isReserved());
		assertEquals("Manleen A", spots.get("A1").getReservedBy());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionForInvalidSpotId() {
		ReservationDTO reservation = ReservationDTO.builder()
													.spotId("A8")
													.userName("Manleen A")
													.phone("7873923408")
													.build();
		reservationService.reserveSpot(reservation);
	}
	
	@Test
	public void shouldCancelReservation() {
		reservationService.cancelReservation("A2");
		assertTrue(!spots.get("A2").isReserved());
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
