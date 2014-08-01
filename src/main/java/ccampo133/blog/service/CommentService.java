package ccampo133.blog.service;

import ccampo133.blog.domain.Comment;
import ccampo133.blog.domain.Post;
import ccampo133.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        // Create the post
        Post post = new Post();
        post.setId(postId);
        comment.setPost(post);
        comment.setDate(new Date());
        comment.setAuthor(username);
        return commentRepository.save(comment);
    }

    public List<Comment> getAllComments() {
        return (List<Comment>) commentRepository.findAll();
    }
}
