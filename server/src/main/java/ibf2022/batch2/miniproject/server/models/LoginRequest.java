package ibf2022.batch2.miniproject.server.models;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;

@Validated
@Data
public class LoginRequest {

  @NotBlank
  private String username;

  @NotBlank
  private String password;
}
