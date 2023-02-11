package exercise.repository;

import exercise.model.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

    @Query(value = "SELECT * FROM COURSES WHERE id in :ids", nativeQuery = true)
    List<Course> findAllByIds(List<String> ids);
    Course findById(long id);
}
