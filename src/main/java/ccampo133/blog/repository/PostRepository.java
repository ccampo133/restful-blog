package ccampo133.blog.repository;

import ccampo133.blog.domain.Post;

import java.util.List;

/**
 * Created by chriscampo on 7/21/14.
 */
public interface PostRepository {
    List<Post> getAll();
    void add(Post post);
    void remove(Post post);
}
