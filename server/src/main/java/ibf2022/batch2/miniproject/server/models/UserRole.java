package ibf2022.batch2.miniproject.server.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {

  private Integer id;
  private UserRoleEnum name;

  private Long userId;
  private Long roleId;
}
