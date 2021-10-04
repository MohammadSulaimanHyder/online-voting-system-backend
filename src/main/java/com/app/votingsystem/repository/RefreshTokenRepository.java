package com.app.votingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.votingsystem.models.VerificationToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<VerificationToken, Long> {

}
