package ibf2022.batch2.miniproject.server.repositories;

import ibf2022.batch2.miniproject.server.models.Role;
import ibf2022.batch2.miniproject.server.models.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepository {

    private static final String SQL_FIND_ROLE_BY_NAME = """
        SELECT * FROM roles WHERE name = ?
        """;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<Role> findByName(RoleEnum name) {
        try {
            Role role = jdbcTemplate.queryForObject(SQL_FIND_ROLE_BY_NAME, (rs, rowNum) -> {
                Role roleRes = new Role();
                roleRes.setId(rs.getInt("id"));
                roleRes.setName(RoleEnum.valueOf(rs.getString("name")));
                return roleRes;
            }, name.name());
            return Optional.ofNullable(role);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
