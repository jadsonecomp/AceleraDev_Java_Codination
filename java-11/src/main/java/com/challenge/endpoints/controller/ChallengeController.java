package com.challenge.endpoints.controller;

import com.challenge.entity.Candidate;
import com.challenge.entity.Challenge;
import com.challenge.service.impl.CandidateService;
import com.challenge.service.impl.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @GetMapping
    public ResponseEntity<List<Challenge>> findByAccelerationIdAndUserId(@RequestParam(required = false, name = "accelerationId") Long accelerationId,
                                                                         @RequestParam(required = false, name = "userId") Long userId) {
        return ResponseEntity.ok(this.challengeService.findByAccelerationIdAndUserId(accelerationId, userId));
    }

}
