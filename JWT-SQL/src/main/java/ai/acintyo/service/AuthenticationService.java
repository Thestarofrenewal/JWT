package ai.acintyo.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ai.acintyo.dto.LoginRequestDTO;
import ai.acintyo.dto.RegisterUserRequestDTO;
import ai.acintyo.model.User;
import ai.acintyo.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	public User signUp(RegisterUserRequestDTO registerUserRequestDTO) {

		User user = User.builder().fullName(registerUserRequestDTO.getFullName()).email(registerUserRequestDTO.getEmail())
				.password(passwordEncoder.encode(registerUserRequestDTO.getPassword())).build();

		return userRepository.save(user);
	}

	public User authenticate(LoginRequestDTO loginRequestDTO) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));
		return userRepository.findByEmail(loginRequestDTO.getEmail()).orElseThrow();
	}

}
