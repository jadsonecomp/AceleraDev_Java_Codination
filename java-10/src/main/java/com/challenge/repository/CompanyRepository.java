package com.challenge.repository;

import com.challenge.entity.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Long> {

    Optional<Company> findById(Long id);

    @Query("from Company com " +
            "inner join Candidate can on com.id = can.id.company.id " +
            "inner join Acceleration acc on can.id.acceleration.id = acc.id " +
            "where acc = :accelerationId")
    List<Company> findbyAccelerationId(@Param("accelerationId") Long accelerationId);

    @Query("from Company com " +
            "inner join Candidate can on com.id = can.id.company.id " +
            "inner join User use on can.id.user.id = use.id " +
            "where use = :useId")
    List<Company> findByUserId(@Param("useId") Long useId);
}
