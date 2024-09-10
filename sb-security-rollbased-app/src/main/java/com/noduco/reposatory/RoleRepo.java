package com.noduco.reposatory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noduco.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{

}
