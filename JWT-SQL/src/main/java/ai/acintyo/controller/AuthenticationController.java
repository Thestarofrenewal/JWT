package ai.acintyo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.acintyo.dto.LoginRequestDTO;
import ai.acintyo.dto.LoginResponseDTO;
import ai.acintyo.dto.RegisterUserRequestDTO;
import ai.acintyo.model.User;
import ai.acintyo.service.AuthenticationService;
import ai.acintyo.service.JwtService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final JwtService jwtService;

	private final AuthenticationService authenticationService;

	@PostMapping("/signup")
	public ResponseEntity<User> registerUser(@RequestBody RegisterUserRequestDTO registerUserRequestDTO) {
		User user = authenticationService.signUp(registerUserRequestDTO);
		return ResponseEntity.ok(user);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {

		User authenticate = authenticationService.authenticate(loginRequestDTO);
		String token = jwtService.generateToken(authenticate);

		LoginResponseDTO dto = new LoginResponseDTO();
		dto.setToken(token);
		dto.setExpiresIn(jwtService.getExpirationTime());
		return ResponseEntity.ok(dto);
	}

}
