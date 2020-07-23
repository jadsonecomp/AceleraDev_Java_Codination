package com.challenge.repository;

import com.challenge.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findById(Long id);

    @Query("from User use " +
            "inner join Candidate can on use.id = can.id.user.id " +
            "inner join Acceleration acc on can.id.acceleration.id = acc.id " +
            "where acc.name = :accelerationName")
    List<User> findByAccelerationName(@Param("accelerationName") String accelerationName);

    @Query("from User use " +
            "inner join Candidate can on use.id = can.id.user.id " +
            "inner join Company com on can.id.company.id = com.id " +
            "where com.id = :companyId")
    List<User> findByCompanyId(@Param("companyId") Long companyId);
}
