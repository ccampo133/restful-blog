package ccampo133.blog.service;

import ccampo133.blog.domain.Comment;
import ccampo133.blog.domain.Post;
import ccampo133.blog.exception.CommentNotFoundException;
import ccampo133.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(final CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(final Comment comment, final long postId, final String username) {
        // Create the associated post object (we only need the ID to create the required relationship in the DB).
        Post post = new Post();
        post.setId(postId);
        comment.setPost(post);
        comment.setDate(new Date());
        comment.setAuthor(username);
        return commentRepository.save(comment);
    }

    public List<Comment> getAllComments(final long postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment getCommentById(final long id) throws CommentNotFoundException {
        final Comment comment = commentRepository.findOne(id);

        if (comment == null) {
            throw new CommentNotFoundException("The comment with ID = " + id + " does not exist!");
        }

        return comment;
    }

    public void updateCommentById(final Comment comment, final long id) throws CommentNotFoundException {
        final Comment oldComment = commentRepository.findOne(id);

        if (oldComment == null) {
            throw new CommentNotFoundException("The comment with ID = " + id + " does not exist!");
        }

        oldComment.setBody(comment.getBody());

        commentRepository.save(oldComment);
    }

    public void deleteCommentById(final long id) throws CommentNotFoundException {
        try {
            commentRepository.delete(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new CommentNotFoundException("The comment with ID = " + id + " does not exist!");
        }
    }
}
