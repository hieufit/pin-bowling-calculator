package com.bowling.controller;

import com.bowling.model.Scoreboard;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping
public class BowlingController {
	@PostMapping("/score")
	public ResponseEntity<?> score(@RequestBody Scoreboard scoreboard) {
		if (!scoreboard.framesValid()) {
			return ResponseEntity.badRequest().body("The provided scores are not valid");
		}
		Integer score = !CollectionUtils.isEmpty(scoreboard.getFrames()) ? scoreboard.calculateScore() : 0;
		return new ResponseEntity<>(score, HttpStatus.OK);
	}

}
