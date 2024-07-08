package ai.acintyo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUserRequestDTO {

	private String fullName;

	private String email;

	private String password;

}
