package ccampo133.blog.service;

import ccampo133.blog.domain.Comment;
import ccampo133.blog.domain.Post;
import ccampo133.blog.repository.CommentRepository;
import mockit.*;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;

@RunWith(JMockit.class)
public class CommentServiceTest {

    @Mocked
    @Injectable
    private CommentRepository commentRepository;

    @Tested
    private CommentService commentService;

    @Test
    public void createCommentCreatesNewPostAndUpdatesPostDateAuthor(@Mocked final Comment comment,
            @Mocked final Post post, @Mocked final Date date) {
        final long postId = 123;
        final String username = "TEST_USER";

        new NonStrictExpectations() {{
            new Date();
            result = date;
        }};

        new Expectations() {{
            new Post();
            result = post;

            post.setId(postId);
            comment.setPost(post);
            comment.setDate(new Date());
            comment.setAuthor(username);

            commentRepository.save(comment);
            result = comment;
        }};

        Comment result = commentService.createComment(postId, comment, username);
        assertEquals("Result does not match expected.", comment, result);
    }

    @Test
    public void getAllCommentsReturnsList(@Mocked final List<Comment> comments) {
        final long postId = 9;
        new Expectations() {{
            commentRepository.findByPostId(postId);
            result = comments;
        }};

        List<Comment> result = commentService.getAllComments(postId);
        assertEquals("Result does not match expected", comments, result);
    }

    @Test
    public void testGetCommentById() {
        // TODO
    }

    @Test
    public void testUpdateCommentById() {
        // TODO
    }

    @Test
    public void testDeleteCommentById() {
        // TODO
    }
}
