package ccampo133.blog.service;

import ccampo133.blog.domain.Post;

import java.util.List;

/**
 * Created by chriscampo on 7/21/14.
 */
public interface PostService {

    List<Post> getAllPosts();

    Post createPost(Post post);
}
