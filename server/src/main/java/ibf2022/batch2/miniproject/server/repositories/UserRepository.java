package ibf2022.batch2.miniproject.server.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.miniproject.server.models.User;

@Repository
public class UserRepository {

	public static final String SQL_FIND_BY_USERNAME = """
		select * from users where username = ?
		""";
  
  public static final String SQL_EXISTS_BY_USERNAME = """
    select count(1) from users where username = ?
    """;

  public static final String SQL_EXISTS_BY_EMAIL = """
    select count(1) from users where email = ?
    """;

    public static final String SQL_SAVE_USER = """
    INSERT INTO users (username, email, password) VALUES (?, ?, ?)
    """;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<User> findByUsername(String username) {
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_FIND_BY_USERNAME, username);
        if(rs.next()){
            return Optional.of(User.populate(rs));
        }
        return Optional.empty();
    }

    public boolean userExistsByUsername(String username) {
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_EXISTS_BY_USERNAME, username);
        return (rs.next() && rs.getInt(1) > 0);
    }

    public boolean existsByEmail(String email) {
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_EXISTS_BY_EMAIL, email);
        return (rs.next() && rs.getInt(1) > 0);
    }

    public Long save(User user) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_SAVE_USER, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
}
