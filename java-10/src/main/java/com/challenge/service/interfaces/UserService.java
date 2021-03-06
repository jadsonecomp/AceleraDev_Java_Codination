package com.challenge.service.interfaces;

import com.challenge.entity.User;
import com.challenge.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findById(Long userId) {
        return this.userRepository.findById(userId);
    }

    @Override
    public List<User> findByAccelerationName(String name) {
        return this.userRepository.findByAccelerationName(name);
    }

    @Override
    public List<User> findByCompanyId(Long companyId) {
        return this.userRepository.findByCompanyId(companyId);
    }

    @Override
    public User save(@NotNull User object) {
        object.setCreatedAt(LocalDateTime.now());
        return this.userRepository.save(object);
    }
}
