package ibf2022.batch2.miniproject.server.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import ibf2022.batch2.miniproject.server.models.Role;
import ibf2022.batch2.miniproject.server.models.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.miniproject.server.models.UserRoleEnum;
import ibf2022.batch2.miniproject.server.models.UserRole;

@Repository
public class UserRoleRepository {

    private static final String SQL_FIND_USER_ROLE_BY_NAME = """
        SELECT * FROM user_roles WHERE name = ?
        """;

    private static final String SQL_FIND_USER_ROLE_BY_USER_ID = """
            SELECT ur.role_id AS id,
            (SELECT r.name FROM roles r WHERE r.id= ur.role_id) AS name  
            FROM user_roles ur WHERE ur.user_id = ?
            """;

    public static final String SQL_SAVE_USER_ROLE = """
    INSERT INTO user_roles (user_id, role_id) VALUES (?, ?)
    """;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<UserRole> findByName(UserRoleEnum name) {
        try {
            UserRole userRole = jdbcTemplate.queryForObject(SQL_FIND_USER_ROLE_BY_NAME, (rs, rowNum) -> {
                UserRole role = new UserRole();
                role.setId(rs.getInt("id"));
                role.setName(UserRoleEnum.valueOf(rs.getString("name")));
                return role;
            }, name.name());
            return Optional.ofNullable(userRole);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<Set<Role>> findByUserId(Long userId) {
        try {
            List<Map<String, Object>> resMap = jdbcTemplate.queryForList(SQL_FIND_USER_ROLE_BY_USER_ID, userId);
            return Optional.of(resMap.stream().map(r-> {
                Role role = new Role();
                role.setId((Integer) r.get("id"));
                role.setName(RoleEnum.valueOf(String.valueOf(r.get("name"))));
                return role;
            }).collect(Collectors.toSet()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void save(List<Object[]> userRoleList) {
        jdbcTemplate.batchUpdate(SQL_SAVE_USER_ROLE, userRoleList);
    }
}
