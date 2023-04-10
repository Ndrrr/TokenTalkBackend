package com.ndrrr.userprofile.repository;

import com.ndrrr.userprofile.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByEmail(String email);

    List<UserProfile> findByEmailIsContainingIgnoreCase(String email);

}
