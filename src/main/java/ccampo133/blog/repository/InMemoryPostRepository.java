package ccampo133.blog.repository;

import ccampo133.blog.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by chriscampo on 7/21/14.
 */
@Repository
public class InMemoryPostRepository implements PostRepository {

    private final Map<Long, Post> posts;

    public InMemoryPostRepository() {
        posts = new HashMap<Long, Post>();
    }

    @Override public void add(final Post post) {
        posts.put(post.getId(), post);
    }

    @Override public List<Post> getAll() {
        return new ArrayList<Post>(posts.values());
    }

    @Override public Post getById(final long id) {
        return posts.get(id);
    }

    @Override public void updateById(final long id, final Post post) {
        posts.put(id, post);
    }

    @Override public void remove(final long id) {
        posts.remove(id);
    }
}
