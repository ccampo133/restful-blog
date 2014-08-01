package ccampo133.blog.service;

import ccampo133.blog.domain.Post;
import ccampo133.blog.exception.PostNotFoundException;
import ccampo133.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(final Post post, final String username) {
        post.setDate(new Date());
        post.setAuthor(username);
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return (List<Post>) postRepository.findAll();
    }

    public Post getPostById(final long id) throws PostNotFoundException {
        final Post post = postRepository.findOne(id);

        // Couldn't find this post in the DB - 404
        if (post == null) {
            throw new PostNotFoundException("The post with ID " + id + " does not exist!");
        }

        return post;
    }

    public void updatePostById(final Post post, final long id) throws PostNotFoundException {
        final Post oldPost = postRepository.findOne(id);

        if (oldPost == null) {
            throw new PostNotFoundException("The post with ID " + id + " does not exist!");
        }

        // Allow partial updates; ignore author, date, and id fields
        if (post.getBody() != null) {
            oldPost.setBody(post.getBody());
        }

        if (post.getTitle() != null) {
            oldPost.setTitle(post.getTitle());
        }

        postRepository.save(oldPost);
    }

    public void deletePostById(final long id) throws PostNotFoundException {
        try {
            postRepository.delete(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new PostNotFoundException("The post with ID " + id + " does not exist!");
        }
    }
}
