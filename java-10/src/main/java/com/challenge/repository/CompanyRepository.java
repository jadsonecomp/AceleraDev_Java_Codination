package com.challenge.repository;

import com.challenge.entity.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

    @Query(value = "select distinct com from Company com " +
            "join com.candidates can " +
            "where can.id.acceleration.id = :accelerationId")
    List<Company> findbyAccelerationId(@Param("accelerationId") Long accelerationId);

    @Query("from Company com " +
            "join com.candidates can " +
            "where can.id.user.id = :useId")
    List<Company> findByUserId(@Param("useId") Long useId);
}
