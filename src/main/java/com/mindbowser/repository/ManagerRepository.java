	package com.mindbowser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;
import com.mindbowser.entity.ManagerEntity;

@Repository
public interface ManagerRepository extends JpaRepository<ManagerEntity, Long>{

	Optional<ManagerEntity> findByEmail(String email);

	boolean existsByEmail(String email);

}
