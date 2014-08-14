package ccampo133.blog.repository;

import ccampo133.blog.domain.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
