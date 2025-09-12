package project.model;

import javax.validation.Valid;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	
	@Valid
	private String name;
	
	@Valid
	private String email;

}
