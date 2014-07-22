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
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.getAll();
    }

    @Override
    public Post createPost(final Post post) {
        postRepository.add(post);
        return post;
    }

}
