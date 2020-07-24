package com.challenge.repository;

import com.challenge.entity.Submission;
import com.challenge.entity.SubmissionId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends CrudRepository<Submission, SubmissionId> {

    @Query("select coalesce(max(sub.score), 0) from Submission sub " +
            "where sub.id.challenge.id = :challengId ")
    BigDecimal findHigherScoreByChallengeId(@Param("challengId") Long challengId);

    @Query("from Submission sub " +
            "inner join sub.id.challenge cha " +
            "inner join cha.accelerations acc " +
            "where cha.id = :challegeId and acc.id = :accelerationId")
    List<Submission> findByChallegeIdAndAccelerationId(@Param("challegeId") Long challegeId, @Param("accelerationId") Long accelerationId);
}
