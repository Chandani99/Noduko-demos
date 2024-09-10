package com.noduco.reposatory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noduco.entity.CustomUser;

@Repository
public interface CustomUserRepo extends JpaRepository<CustomUser, Long>{
	Optional<CustomUser> findByUsername(String username);
}
