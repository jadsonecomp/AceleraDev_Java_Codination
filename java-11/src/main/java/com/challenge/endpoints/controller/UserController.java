package com.challenge.endpoints.controller;

import com.challenge.endpoints.controller.advice.ResourceNotFoundException;
import com.challenge.entity.User;
import com.challenge.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<User>(this.userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User")), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> findByCompanyIdOrByAccelerationName(@RequestParam(required = false, name = "accelerationName") String accelerationName,
                                                                            @RequestParam(required = false, name = "companyId") Long companyId) {
        return (accelerationName != null) ? ResponseEntity.ok(this.userService.findByAccelerationName(accelerationName)) :
                (companyId != null) ? ResponseEntity.ok(this.userService.findByCompanyId(companyId))
                        : ResponseEntity.ok(Collections.emptyList());
    }

}
