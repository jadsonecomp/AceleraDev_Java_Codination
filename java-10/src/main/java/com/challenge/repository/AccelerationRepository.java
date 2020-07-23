package com.challenge.repository;

import com.challenge.entity.Acceleration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccelerationRepository extends CrudRepository<Acceleration, Long> {

    List<Acceleration> findAll();

    List<Acceleration> findAccelerationById(Long id);

    List<Acceleration> findAccelerationByName(String name);

    @Query(value = "from Acceleration acce " +
            "inner join Candidate canId ON acce.id = canId.id.acceleration.id " +
            "inner join Company com ON canId.id.company.id = com.id " +
            "where com.id = :companyId")
    List<Acceleration> findByCompanyId(@Param("companyId") Long companyId);
}
