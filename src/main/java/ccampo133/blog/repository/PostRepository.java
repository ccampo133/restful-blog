package ccampo133.blog.repository;

import ccampo133.blog.domain.Post;

import java.util.List;

/**
 * Created by chriscampo on 7/21/14.
 */
public interface PostRepository {

    void add(Post post);

    List<Post> getAll();

    Post getById(long id);

    void updateById(long id, Post post);

    void remove(long id);
}
