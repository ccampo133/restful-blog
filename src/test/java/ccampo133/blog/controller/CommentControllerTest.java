package ccampo133.blog.controller;

import ccampo133.blog.domain.Comment;
import ccampo133.blog.exception.CommentNotFoundException;
import ccampo133.blog.resource.CommentResource;
import ccampo133.blog.resource.assembler.CommentResourceAssembler;
import ccampo133.blog.service.CommentService;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JMockit.class)
public class CommentControllerTest {

    @Mocked
    @Injectable
    private CommentService commentService;

    @Mocked
    @Injectable
    private CommentResourceAssembler commentResourceAssembler;

    @Tested
    private CommentController commentController;

    @Test
    public void createCommentIsSuccessful(@Mocked final Comment comment, @Mocked final Principal principal,
            @Mocked final CommentResource commentResource) {
        final long postId = 1234;
        new Expectations() {{
            commentService.createComment(postId, comment, principal.getName());
            result = comment;

            commentResourceAssembler.toResource(comment);
            result = commentResource;
        }};

        ResponseEntity<CommentResource> result = commentController.createComment(postId, comment, principal);
        assertEquals("Result body does not match expected.", commentResource, result.getBody());
        assertEquals("Result status code should be 204 Created", HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    public void getAllCommentsIsSuccessful(@Mocked final List<Comment> comments,
            @Mocked final List<CommentResource> resources) {
        final long postId = 1337;
        new Expectations() {{
            commentService.getAllComments(postId);
            result = comments;

            commentResourceAssembler.toResources(comments);
            result = resources;
        }};

        List<CommentResource> result = commentController.getAllComments(postId);
        assertEquals("Result resources do not match expected", resources, result);
    }

    @Test
    public void getCommentByIdIsSuccessful(@Mocked final Comment comment, @Mocked final CommentResource resource)
            throws CommentNotFoundException {
        final long id = 1337;
        new Expectations() {{
            commentService.getCommentById(id);
            result = comment;

            commentResourceAssembler.toResource(comment);
            result = resource;
        }};

        CommentResource result = commentController.getCommentById(id);
        assertEquals("Result does not match expected.", resource, result);
    }

    @Test
    public void updateCommentByIdIsSuccessful(@Mocked final Comment comment) throws CommentNotFoundException {
        final long id = 1337;
        new Expectations() {{
            commentService.updateCommentById(id, comment);
        }};

        ResponseEntity<Void> result = commentController.updateCommentById(id, comment);
        assertEquals("Response status code should be 204 No Content", HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    public void deleteCommentByIdIsSuccessful() throws CommentNotFoundException {
        final long id = 1337;
        new Expectations() {{
            commentService.deleteCommentById(id);
        }};

        ResponseEntity<Void> result = commentController.deleteCommentById(id);
        assertEquals("Response status code should be 200 OK", HttpStatus.OK, result.getStatusCode());
    }
}
