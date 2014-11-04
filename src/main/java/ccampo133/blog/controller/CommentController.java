package ccampo133.blog.controller;

import ccampo133.blog.domain.Comment;
import ccampo133.blog.exception.CommentNotFoundException;
import ccampo133.blog.resource.CommentResource;
import ccampo133.blog.resource.assembler.CommentResourceAssembler;
import ccampo133.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CommentResource> createComment(@PathVariable("postId") final long postId,
            @RequestBody final Comment comment, final Principal principal) {
        Comment newComment = commentService.createComment(postId, comment, principal.getName());
        CommentResource newCommentResource = commentResourceAssembler.toResource(newComment);
        return new ResponseEntity<>(newCommentResource, HttpStatus.CREATED);
    }

    // READ
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(method = RequestMethod.GET)
    public PagedResources<CommentResource> getAllComments(@PathVariable("postId") final long postId, Pageable pageable,
            PagedResourcesAssembler<Comment> assembler) {
        Page<Comment> comments = commentService.getAllComments(postId, pageable);
        return assembler.toResource(comments, commentResourceAssembler);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommentResource getCommentById(@PathVariable("id") final long id)
            throws CommentNotFoundException {
        Comment comment = commentService.getCommentById(id);
        return commentResourceAssembler.toResource(comment);
    }

    // UPDATE
    @PreAuthorize("hasRole('ROLE_ADMIN')") // TODO: add support for allowing the author to update his own comment
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Void> updateCommentById(@PathVariable("id") final long id,
            @RequestBody final Comment comment) throws CommentNotFoundException {
        commentService.updateCommentById(id, comment);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // DELETE
    @PreAuthorize("hasRole('ROLE_ADMIN')") // TODO: add support for allowing the author to update his own comment
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCommentById(@PathVariable("id") final long id) throws CommentNotFoundException {
        commentService.deleteCommentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
