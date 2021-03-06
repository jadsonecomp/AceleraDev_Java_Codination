package com.challenge.repository;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends CrudRepository<Candidate, CandidateId> {

    List<Candidate> findAll();

    @Query("from Candidate can where can.id.company.id = :companyId")
    List<Candidate> findByCompanyId(@Param("companyId") Long companyId);

    @Query("from Candidate can where can.id.acceleration.id = :accelerationId")
    List<Candidate> findByAccelerationId(@Param("accelerationId") Long accelerationId);

    @Query("FROM Candidate ca WHERE ca.id.user.id = :userId " +
            "AND ca.id.company.id = :companyId AND ca.id.acceleration.id = :accelerationId")
    Optional<Candidate> findById(@Param("userId") Long userId,
                                 @Param("companyId") Long companyId,
                                 @Param("accelerationId") Long accelerationId);

    Optional<Candidate> findById(CandidateId id);
}
