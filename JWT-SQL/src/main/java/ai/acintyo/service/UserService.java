package ai.acintyo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ai.acintyo.model.User;
import ai.acintyo.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public List<User> getAllUsers() {

		List<User> users = new ArrayList<User>();	
		userRepository.findAll().forEach(users::add);
		return users;
	}

}
