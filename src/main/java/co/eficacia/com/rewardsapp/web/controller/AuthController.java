package co.eficacia.com.rewardsapp.web.controller;

import co.eficacia.com.rewardsapp.constant.RoleEnum;
import co.eficacia.com.rewardsapp.persistance.model.Role;
import co.eficacia.com.rewardsapp.persistance.model.User;
import co.eficacia.com.rewardsapp.persistance.repository.RoleRepository;
import co.eficacia.com.rewardsapp.persistance.repository.UserRepository;
import co.eficacia.com.rewardsapp.security.jwt.JwtResponse;
import co.eficacia.com.rewardsapp.security.jwt.JwtUtils;
import co.eficacia.com.rewardsapp.security.jwt.MessageResponse;
import co.eficacia.com.rewardsapp.service.impl.UserDetailsImpl;
import co.eficacia.com.rewardsapp.web.api.AuthAPI;
import co.eficacia.com.rewardsapp.web.dto.LoginRequestDTO;
import co.eficacia.com.rewardsapp.web.dto.SignupRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthAPI {
	private final AuthenticationManager authenticationManager;

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final PasswordEncoder passwordEncoder;

	private final JwtUtils jwtUtils;

	@Override
	public ResponseEntity<?> authenticateUser(LoginRequestDTO loginRequestDTO) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roleList = userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getEmail(), roleList));
	}

	@Override
	public ResponseEntity<?> registerUser(SignupRequestDTO signUpRequestDTO) {
		if (userRepository.existsByEmail(signUpRequestDTO.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequestDTO.getName(), signUpRequestDTO.getLastName(),
				signUpRequestDTO.getEmail(), passwordEncoder.encode(signUpRequestDTO.getPassword()));

		Set<String> strRoles = signUpRequestDTO.getRole();
		Set<Role> roleSet = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roleSet.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roleSet.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(RoleEnum.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roleSet.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roleSet.add(userRole);
				}
			});
		}
		user.setRoleSet(roleSet);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
