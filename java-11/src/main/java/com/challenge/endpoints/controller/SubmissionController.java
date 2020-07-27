package com.challenge.endpoints.controller;

import com.challenge.dto.SubmissionDTO;
import com.challenge.entity.Challenge;
import com.challenge.entity.Submission;
import com.challenge.mappers.CandidateMapper;
import com.challenge.mappers.SubmissionMapper;
import com.challenge.service.impl.ChallengeService;
import com.challenge.service.impl.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submission")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private SubmissionMapper submissionMapper;

    @GetMapping
    public ResponseEntity<List<SubmissionDTO>> findByChallengeIdAndAccelerationId(@RequestParam(name = "challengeId") Long challengeId,
                                                                                  @RequestParam(name = "accelerationId") Long accelerationId) {
        return ResponseEntity.ok(this.submissionMapper.map(this.submissionService.findByChallengeIdAndAccelerationId(challengeId, accelerationId)));
    }

}
