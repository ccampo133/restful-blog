package ccampo133.blog.repository.mapper;

import ccampo133.blog.domain.Post;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by chriscampo on 7/22/14.
 */
public class PostRowMapper implements RowMapper {

    @Override public Post mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Post post = new Post();
        post.setId(rs.getLong("id"));
        post.setDate(rs.getDate("date"));
        post.setAuthor(rs.getString("author"));
        post.setBody(rs.getString("body"));
        post.setTitle(rs.getString("title"));
        return post;
    }
}
