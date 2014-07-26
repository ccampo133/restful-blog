package ccampo133.blog.repository;

import ccampo133.blog.domain.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// More JPA magic... the CRUD queries are all generated by the methods in
// the CrudRepository class. Talk about abstraction...

@Repository
public interface PostRepository extends CrudRepository<Post, Long> { }
