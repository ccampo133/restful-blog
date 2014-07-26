package ccampo133.blog.service;

import ccampo133.blog.domain.Post;
import ccampo133.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by chriscampo on 7/21/14.
 */
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

    public Post getPostById(final long id) {
        return postRepository.findOne(id);
    }

    public void updatePost(final Post post, final long id, final String username) {
        post.setId(id);
        post.setAuthor(username);
        post.setDate(new Date());
        postRepository.save(post);
    }

    public void deletePostById(final long id) {
        postRepository.delete(id);
    }
}
