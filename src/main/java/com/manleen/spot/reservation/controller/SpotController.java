package com.manleen.spot.reservation.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manleen.spot.reservation.model.ExceptionMessageDTO;
import com.manleen.spot.reservation.model.NearbyDTO;
import com.manleen.spot.reservation.model.Spot;
import com.manleen.spot.reservation.service.SpotService;

@RestController
public class SpotController {

	private SpotService spotService;
	
	public SpotController(SpotService spotService) {
		this.spotService = spotService;
	}
	
	@RequestMapping(path = "/spots", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity getSpots() {
		Map<String, Spot> spots = spotService.getSpots();
		return ResponseEntity
							.accepted()
							.body(spots);
	}
	
	@RequestMapping(path = "/availableSpots", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity getAvailableSpots() {
		List<Spot> spots = spotService.getAvailableSpots();
		return ResponseEntity
							.accepted()
							.body(spots);
	}
	
	@RequestMapping(path = "/nearbySpots", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity getNearBySpots(@RequestBody NearbyDTO nearbyDTO) {
		List<Spot> nearbySpots = spotService.getNearBySpot(nearbyDTO);
		return ResponseEntity
							.accepted()
							.body(nearbySpots);
	}
	
	@RequestMapping(path = "/spotCost", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity getCost(@RequestParam(value = "id", required = true) String id) {
		try {
			Double cost = spotService.getCost(id);
			return ResponseEntity
							.accepted()
							.body(cost);
		} catch (IllegalArgumentException e) {
			return ResponseEntity
								.badRequest()
								.body(new ExceptionMessageDTO(e.getMessage()));
		}
	}
	
}
