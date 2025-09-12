package project.controler;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import project.entity.User;
import project.model.UserDTO;
import project.repo.UserRepo;
import project.service.AIService;

@RestController
//@RequestMapping("project/")
public class Controler {

//	@Autowired
//	UserRepo userRepo;

	@Autowired
	AIService aIService;

	@PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Decrypt encrypted text")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "ok", content = @Content(schema = @Schema(implementation = Controler.class), examples = @ExampleObject(name = "success", value = ""))),
			@ApiResponse(responseCode = "400", description = "", content = @Content(schema = @Schema(implementation = String.class), examples = @ExampleObject(name = "failure", value = ""))),
			@ApiResponse(responseCode = "401", description = "", content = @Content(schema = @Schema(implementation = String.class), examples = @ExampleObject(name = "failure", value = ""))),
			@ApiResponse(responseCode = "404", description = "", content = @Content(schema = @Schema(implementation = String.class), examples = @ExampleObject(name = "failure", value = ""))),
			@ApiResponse(responseCode = "500", description = "", content = @Content(schema = @Schema(implementation = String.class), examples = @ExampleObject(name = "failure", value = ""))) })
	public ResponseEntity<String> commonDecryption(@RequestHeader(value = "Authorization") String authorization,
			@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = UserDTO.class), examples = @ExampleObject(name = "success", value = ""))) @RequestBody @Valid UserDTO decryptionRequest)
			throws Exception {

		ResponseEntity<String> response = null;

		try {
			String output = aIService.getAiResponse(decryptionRequest);
			return ResponseEntity.ok(output);
		} catch (Exception e) {
			throw e;
		}

	}
//     String input = user.getName();
//     String output=aIService.getAiResponse(input);
//     
//     try {
//     User data= new User();
//     data.setEmail(user.getName());
//     data.setUsername(user.getEmail());
//     userRepo.save(data);
//     }catch(Exception e) {
//    	return ResponseEntity.ok(e.getMessage());
//     }
//     return ResponseEntity.ok(output);
// }
}
