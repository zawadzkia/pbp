package com.zawadzkia.pbp.model.repository;

import com.zawadzkia.pbp.model.Authority;
import com.zawadzkia.pbp.model.enums.AuthorityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByName(AuthorityType type);
}
