package ma.zyn.app.dao.facade.core.course;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.course.Course;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CourseDao extends AbstractRepository<Course,Long>  {

    List<Course> findByInstructorId(Long id);
    int deleteByInstructorId(Long id);
    long countByInstructorEmail(String email);
    List<Course> findByCategoryId(Long id);
    int deleteByCategoryId(Long id);
    long countByCategoryId(Long id);


}
