package com.mindbowser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Optional;
import com.mindbowser.dto.ManagerDTO;
import com.mindbowser.entity.ManagerEntity;
import com.mindbowser.repository.ManagerRepository;

@Service
public class ManagerServiceImpl implements UserDetailsService {

	@Autowired
	ManagerRepository managerRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		Optional<ManagerEntity> manager = managerRepository.findByEmail(email);
				if (!manager.isPresent()) {
					throw new UsernameNotFoundException("User not found with email : " + email);
				}
		return ManagerDTO.build(manager.get());
	}

	@Transactional
	public UserDetails loadUserById(Long id) {
		ManagerEntity manager = managerRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));
		return ManagerDTO.build(manager);
	}
}
