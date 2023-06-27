package ibf2022.batch2.miniproject.server.services;

import java.util.Optional;
import java.util.Set;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import ibf2022.batch2.miniproject.server.models.Role;
import ibf2022.batch2.miniproject.server.models.User;
import ibf2022.batch2.miniproject.server.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl
        implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRoleRepository userRoleRepository;

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));

        Optional<Set<Role>> roles = userRoleRepository.findByUserId(user.getId());

        roles.ifPresent(user::setRoles);

        return user;
    };

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final String SQL_FIND_BY_USERNAME = "SELECT * FROM users WHERE username = ?";

        try {
            User user = jdbcTemplate.query(SQL_FIND_BY_USERNAME, userRowMapper, username).stream().findFirst()
                    .orElse(null);
            if (user == null) {
                throw new UsernameNotFoundException("User Not Found with username: " + username);
            }
            return UserDetailsImpl.build(user);
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
    }
}