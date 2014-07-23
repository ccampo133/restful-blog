package ccampo133.blog.repository;

import ccampo133.blog.domain.Post;
import ccampo133.blog.repository.mapper.PostRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created by chriscampo on 7/22/14.
 */
@Repository
public class PostRepository {

    private JdbcTemplate jdbcTemplate;

    // Spring Boot automagically configures the JdbcTemplate class so we can just autowire it directly.
    // http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#boot-features-using-jdbc-template
    @Autowired
    public PostRepository(@SuppressWarnings("SpringJavaAutowiringInspection") final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Post add(final Post post) {
        final String insertSql = "insert into posts (author, title, body, date) values (?, ?, ?, ?)";

        // Use this specific 'update' overload to get the auto-generated 'id' back from the database.
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override public PreparedStatement createPreparedStatement(final Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, post.getAuthor());
                statement.setString(2, post.getTitle());
                statement.setString(3, post.getBody());
                statement.setDate(4, new Date(post.getDate().getTime()));
                return statement;
            }
        }, keyHolder);

        post.setId(keyHolder.getKey().longValue());
        return post;
    }

    @SuppressWarnings("unchecked")
    public List<Post> getAll() {
        return jdbcTemplate.query("select * from posts", new PostRowMapper());
    }

    public Post getById(final long id) {
        return (Post) jdbcTemplate.queryForObject("select * from posts where id = ?", new PostRowMapper(), id);
    }

    public void updateById(final long id, final Post post) {
        final String updateSql = "update posts set author = ?, title = ?, body = ?, date = ? where id = ?";
        jdbcTemplate.update(updateSql, post.getAuthor(), post.getTitle(), post.getBody(), post.getDate(), id);
    }

    public void remove(final long id) {
        jdbcTemplate.update("delete from posts where id = ?", id);
    }
}

