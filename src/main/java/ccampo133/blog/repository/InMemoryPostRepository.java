package ccampo133.blog.repository;

import ccampo133.blog.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chriscampo on 7/21/14.
 */
@Repository
public class InMemoryPostRepository implements PostRepository {

    private final List<Post> posts;

    public InMemoryPostRepository() {
        this.posts = new ArrayList<Post>();
    }

    @Override
    public List<Post> getAll() {
        return new ArrayList<Post>(posts);
    }

    @Override
    public void add(Post post) {
        posts.add(post);
    }

    @Override
    public void remove(Post post) {
        posts.remove(post);
    }
}
