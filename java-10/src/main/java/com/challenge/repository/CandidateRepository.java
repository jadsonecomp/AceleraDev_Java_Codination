package com.challenge.repository;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CandidateRepository extends CrudRepository<Candidate, CandidateId> {

    List<Candidate> findAll();

    @Query("from Candidate can where can.id.company = :companyId")
    List<Candidate> findByCompanyId(@Param("companyId") Long companyId);

    @Query("from Candidate can where can.id.acceleration = :accelerationId")
    List<Candidate> findByAccelerationId(@Param("accelerationId") Long accelerationId);
}
