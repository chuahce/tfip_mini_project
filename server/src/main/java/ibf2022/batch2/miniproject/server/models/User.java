package ibf2022.batch2.miniproject.server.models;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Validated
public class User {
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Email
    @Size(max = 50)
    private String email;

    @NotBlank
    @Email
    @Size(max = 50)
    private String password;
    private Set<Role> roles = new HashSet<>();

    public User() {}

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }

    public static User populate(SqlRowSet rs) {
    User user = new User();
    user.setId(rs.getLong("id"));
    user.setUsername(rs.getString("username"));
    user.setEmail(rs.getString("email"));
    user.setPassword(rs.getString("password"));

    String roleEnumName = rs.getString("role");
    if (roleEnumName != null && !roleEnumName.isEmpty()) {
        RoleEnum userRoleEnum = RoleEnum.valueOf(roleEnumName);
        Role userRole = new Role(userRoleEnum);
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
    }

    return user;
    }
}

