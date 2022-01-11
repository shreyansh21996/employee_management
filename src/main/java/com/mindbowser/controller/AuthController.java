package com.mindbowser.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindbowser.dto.LoginRequest;
import com.mindbowser.dto.ManagerDTO;
import com.mindbowser.dto.MessageResponse;
import com.mindbowser.dto.SignupRequest;
import com.mindbowser.entity.ManagerEntity;
import com.mindbowser.entity.RoleEntity;
import com.mindbowser.entity.RoleEnum;
import com.mindbowser.repository.ManagerRepository;
import com.mindbowser.repository.RoleRepository;
import com.mindbowser.security.JwtResponse;
import com.mindbowser.security.JwtUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Authentication Controller")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private ManagerRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

	@RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
	public void corsHeaders(HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
		response.addHeader("Access-Control-Max-Age", "3600");
	}

	
	@ApiOperation(value = "Sign In", notes = "POST API to Sign In")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(
			@ApiParam(value = "LoginRequest Object", required = true) @Valid @RequestBody LoginRequest loginRequest)throws Exception {
		String data = loginRequest.getEmail();
		if (userRepository.existsByEmail(loginRequest.getEmail()) == false) {

			if (data.matches("^[a-zA-Z]*$") == true) {
				return ResponseEntity.badRequest().body(new MessageResponse("Your Username or Password is incorrect!"));
			} else if (data.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$") == true) {
				return ResponseEntity.badRequest().body(new MessageResponse("This EmailId is not Registered With us!"));
			}

		}

		try {

			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);
			ManagerDTO userDetails = (ManagerDTO) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());
			return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getFirstName(),
					userDetails.getLastName(), userDetails.getEmail(), roles, userDetails.getAddress(),
					userDetails.getBirthDate(), userDetails.getCompanyName()));
		} catch (DisabledException e) {
			log.error("USER_DISABLED");
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: User is disabled for " + loginRequest.getEmail()));
		} catch (BadCredentialsException e) {
			log.error("INVALID_CREDENTIALS");
			return ResponseEntity.badRequest().body(new MessageResponse("Your Username or Password is incorrect!"));
		}

	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, HttpServletRequest request) {
		log.info("inside AuthController.registerUser() method");
		
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Email is already in use!"));
		}
		ManagerEntity user = new ManagerEntity(signUpRequest.getFirstName(), signUpRequest.getLastName(),
				signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()), signUpRequest.getAddress(),
				signUpRequest.getBirthDate(), signUpRequest.getCompanyName());

		Set<String> strRoles = signUpRequest.getRoles();
		Set<RoleEntity> roles = new HashSet<>();

		if (strRoles == null) {
			RoleEntity userRole = roleRepository.findByRoleName(RoleEnum.EMPLOYEE)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "MANAGER":
						RoleEntity adminRole = roleRepository.findByRoleName(RoleEnum.MANAGER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);

						break;
					case "EMPLOYEE":
						RoleEntity userRole = roleRepository.findByRoleName(RoleEnum.EMPLOYEE)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);

						break;
					default:
						RoleEntity defaultUserRole = roleRepository.findByRoleName(RoleEnum.EMPLOYEE)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(defaultUserRole);
				}
			});
		}
		user.setRole(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}