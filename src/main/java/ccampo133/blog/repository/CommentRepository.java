package ccampo133.blog.repository;

import ccampo133.blog.domain.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findByPostId(final long postId);
}
