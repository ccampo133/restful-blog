package ccampo133.blog.service;

import ccampo133.blog.domain.Comment;
import ccampo133.blog.domain.Post;
import ccampo133.blog.exception.CommentNotFoundException;
import ccampo133.blog.repository.CommentRepository;
import mockit.*;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.EmptyResultDataAccessException;

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

    @Test(expected = CommentNotFoundException.class)
    public void getCommentByIdThrowsCommentNotFoundException() throws CommentNotFoundException {
        final long id = 14252213;
        new Expectations() {{
            commentRepository.findOne(id);
            result = null;
        }};

        commentService.getCommentById(id);
    }

    @Test
    public void getCommentByIdReturnsComment(@Mocked final Comment comment) throws CommentNotFoundException {
        final long id = 14252213;
        new Expectations() {{
            commentRepository.findOne(id);
            result = comment;
        }};

        Comment result = commentService.getCommentById(id);
        assertEquals("Result does not match expected.", comment, result);
    }

    @Test(expected = CommentNotFoundException.class)
    public void updateCommentByIdThrowsCommentNotFoundException(@Mocked final Comment comment)
            throws CommentNotFoundException {
        final long id = 9823529;
        new Expectations() {{
            commentRepository.findOne(id);
            result = null;
        }};

        commentService.updateCommentById(id, comment);
    }

    @Test
    public void updateCommentByIdSetsBodyAndSaves(@Mocked final Comment comment) throws CommentNotFoundException {
        final long id = 82342384;
        new Expectations() {{
            commentRepository.findOne(id);
            result = comment;

            comment.setBody(comment.getBody());
            commentRepository.save(comment);
        }};

        commentService.updateCommentById(id, comment);
    }

    @Test(expected = CommentNotFoundException.class)
    public void deleteCommentByIdThrowsCommentNotFoundException(@Mocked final EmptyResultDataAccessException ex)
            throws CommentNotFoundException {
        final long id = 90204;
        new Expectations() {{
            commentRepository.delete(id);
            result = ex;
        }};

        commentService.deleteCommentById(id);
    }

    @Test
    public void deleteCommentByIdIsSuccessful() throws CommentNotFoundException {
        final long id = 242;
        new Expectations() {{
            commentRepository.delete(id);
        }};

        commentService.deleteCommentById(id);
    }
}
