package ibf2022.batch2.miniproject.server.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@NoArgsConstructor
public class Role {
    private Integer id;

    private RoleEnum name;

    public Role(RoleEnum name) {
        this.name = name;
    }

}