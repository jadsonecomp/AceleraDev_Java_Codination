package com.challenge.repository;

import com.challenge.entity.Challenge;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChallengeRepository extends CrudRepository<Challenge, Long> {

    @Query("from Challenge cha " +
            "inner join Acceleration acc on cha.id = acc.challenge.id " +
            "inner join Candidate can on acc.id = can.id.acceleration.id " +
            "inner join User use on can.id.user.id = use.id " +
            "where acc.id = :accelerationId and use.id = :userId")
    List<Challenge> findByAccelerationIdAndUserId(@Param("accelerationId") Long accelerationId, @Param("userId") Long userId);
}
