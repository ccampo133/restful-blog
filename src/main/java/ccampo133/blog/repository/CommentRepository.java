package ccampo133.blog.repository;

import ccampo133.blog.domain.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {

    // A little bit of magic here - the proper query is created by Spring Data JPA so that the list of comments are
    // queried by the comment's post.id property. So really it is doing 'find all comments with comment.post.getId() ==
    // postId.
    List<Comment> findByPostId(final long postId);
}
