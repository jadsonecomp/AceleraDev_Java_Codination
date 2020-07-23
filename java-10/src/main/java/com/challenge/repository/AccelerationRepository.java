package com.challenge.repository;

import com.challenge.entity.Acceleration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccelerationRepository extends CrudRepository<Acceleration, Long> {

    List<Acceleration> findAll();

    @Query("from Acceleration acc where acc.id = :idAcceleration")
    Optional<Acceleration> findAccelerationById(@Param("idAcceleration") Long idAcceleration);

    @Query("from Acceleration acc where acc.name = :nameAcceleration")
    Optional<Acceleration> findAccelerationByName(@Param("nameAcceleration") String nameAcceleration);

    @Query("from Acceleration acc " +
            "inner join Candidate canId ON acc.id = canId.id.acceleration.id " +
            "inner join Company com ON canId.id.company.id = com.id " +
            "where com.id = :companyId")
    List<Acceleration> findByCompanyId(@Param("companyId") Long companyId);
}
