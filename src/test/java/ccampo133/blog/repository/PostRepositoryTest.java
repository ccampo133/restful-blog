package ccampo133.blog.repository;

import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.Assert.*;

public class PostRepositoryTest {

    @Mocked
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testAdd() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {
        final String insertSql = "select * from posts";
        new Expectations(){{

        }};
    }

    @Test
    public void testGetById() throws Exception {

    }

    @Test
    public void testUpdateById() throws Exception {

    }

    @Test
    public void testRemove() throws Exception {

    }
}
