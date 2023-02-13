package exercise.repository;

import exercise.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    // BEGIN
    Iterable<Comment> findAllByPostId(long id);

    //@Query(value = "SELECT * FROM COMMENT WHERE ID :id AND POST_ID :postId", nativeQuery = true)
    Optional<Comment> findByIdAndPostId(long id, long postId);

    boolean existsByIdAndPostId(long id, long postId);

//    @Override
//    void deleteById(Long id);

    // END
}
