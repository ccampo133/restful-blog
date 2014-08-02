package ccampo133.blog.resource.assembler;

import ccampo133.blog.domain.Post;
import ccampo133.blog.resource.PostResource;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(JMockit.class)
public class PostResourceAssemblerTest {

    @Tested
    private PostResourceAssembler postResourceAssembler;

    @Test
    public void instantiateResourceMapsCorrectly(@Mocked final Post post) {
        new NonStrictExpectations() {{
            post.getAuthor();
            result = "TEST_AUTHOR";

            post.getBody();
            result = "TEST_BODY";

            post.getDate();
            result = new Date(1234);

            post.getTitle();
            result = "TEST_TITLE";

            post.getId();
            result = 1984;
        }};

        PostResource result = postResourceAssembler.instantiateResource(post);

        assertEquals("Author mismatch.", "TEST_AUTHOR", result.getAuthor());
        assertEquals("Body mismatch.", "TEST_BODY", result.getBody());
        assertEquals("Date mismatch.", new Date(1234), result.getDate());
        assertEquals("Title mismatch.", "TEST_TITLE", result.getTitle());
        assertEquals("Post ID mismatch.", 1984, result.getPostId());
    }
}
