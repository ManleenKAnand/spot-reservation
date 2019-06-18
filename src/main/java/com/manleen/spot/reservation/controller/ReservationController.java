package com.manleen.spot.reservation.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manleen.spot.reservation.model.ExceptionMessageDTO;
import com.manleen.spot.reservation.model.ReservationDTO;
import com.manleen.spot.reservation.model.Spot;
import com.manleen.spot.reservation.service.ReservationService;

@RestController
public class ReservationController {
	
	private ReservationService reservationService;
	
	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	@RequestMapping(path = "/reservations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity getReservations() {
		Map<String, String> reservations = reservationService.getReservations();
		return ResponseEntity
							.accepted()
							.body(reservations);
	}
	
	@RequestMapping(path = "/reserve", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity reserve(@RequestBody ReservationDTO reservation) {
		try {
			Spot spot = reservationService.reserveSpot(reservation);
			return ResponseEntity
							.accepted()
							.body(spot);
		} catch (IllegalArgumentException e) {
			return ResponseEntity
								.badRequest()
								.body(new ExceptionMessageDTO(e.getMessage()));
		}
	}
	
	@RequestMapping(path = "/cancel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity cancelReservation(@RequestParam(value = "id", required = true) String id) {
		try { 
			Spot spot = reservationService.cancelReservation(id);
			return ResponseEntity
							.accepted()
							.body(spot);
		} catch (IllegalArgumentException e) {
			return ResponseEntity
								.badRequest()
								.body(new ExceptionMessageDTO(e.getMessage()));
		}
	}

}
