package ccampo133.blog.controller;

import ccampo133.blog.domain.Comment;
import ccampo133.blog.resource.CommentResource;
import ccampo133.blog.resource.assembler.CommentResourceAssembler;
import ccampo133.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;
    private final CommentResourceAssembler commentResourceAssembler;

    @Autowired
    public CommentController(final CommentService commentService,
            final CommentResourceAssembler commentResourceAssembler) {
        this.commentService = commentService;
        this.commentResourceAssembler = commentResourceAssembler;
    }

    // CREATE
    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<CommentResource> createComment(@PathVariable final long postId,
            @RequestBody final Comment comment, final Principal principal) {
        Comment newComment = commentService.createComment(comment, postId, principal.getName());
        CommentResource newCommentResource = commentResourceAssembler.toResource(newComment);
        return new ResponseEntity<CommentResource>(newCommentResource, HttpStatus.CREATED);
    }

    // READ
    @RequestMapping(method = RequestMethod.GET)
    public List<CommentResource> getAllComments() {
        List<Comment> allComments = commentService.getAllComments();
        return commentResourceAssembler.toResources(allComments);
    }

    public CommentResource getCommentById(@RequestParam final long id) {
        return null;
    }

    // UPDATE
    public HttpEntity<Void> updateCommentById(@RequestParam final long id) {
        return null;
    }

    // DELETE
    public HttpEntity<Void> deleteCommentById(@RequestParam final long id) {
        return null;
    }
}
