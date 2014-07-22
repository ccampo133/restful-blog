package ccampo133.blog.service;

import ccampo133.blog.domain.Post;
import ccampo133.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Post createPost(final Post post) {
        postRepository.add(post);
        return post;
    }

    public List<Post> getAllPosts() {
        return postRepository.getAll();
    }

    public Post getPostById(final long id) {
        return postRepository.getById(id);
    }

    public void updatePost(final long id, final Post post) {
        postRepository.updateById(id, post);
    }

    public void deletePostById(final long id) {
        postRepository.remove(id);
    }
}
