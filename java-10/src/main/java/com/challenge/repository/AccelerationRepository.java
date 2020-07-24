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

    Optional<Acceleration> findAccelerationByName(String name);

    @Query("from Acceleration acc " +
            "inner join acc.candidates canId " +
            "inner join canId.id.company com " +
            "where com.id = :companyId")
    List<Acceleration> findByCompanyId(@Param("companyId") Long companyId);
}
