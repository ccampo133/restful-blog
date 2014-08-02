package ccampo133.blog.resource.assembler;

import ccampo133.blog.domain.Comment;
import ccampo133.blog.resource.CommentResource;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(JMockit.class)
public class CommentResourceAssemblerTest {

    @Tested
    private CommentResourceAssembler commentResourceAssembler;

    @Test
    public void testInstantiateResource(@Mocked final Comment comment) {
        new NonStrictExpectations() {{
            comment.getAuthor();
            result = "TEST_AUTHOR";

            comment.getBody();
            result = "TEST_BODY";

            comment.getDate();
            result = new Date(1234);

            comment.getId();
            result = 1984;
        }};

        CommentResource result = commentResourceAssembler.instantiateResource(comment);

        assertEquals("Author mismatch.", "TEST_AUTHOR", result.getAuthor());
        assertEquals("Body mismatch.", "TEST_BODY", result.getBody());
        assertEquals("Date mismatch.", new Date(1234), result.getDate());
        assertEquals("Comment ID mismatch.", 1984, result.getCommentId());
    }
}
