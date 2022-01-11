package com.mindbowser.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindbowser.entity.RoleEntity;
import com.mindbowser.entity.RoleEnum;
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	Optional<RoleEntity> findByRoleName(RoleEnum name);
}
