package ma.zyn.app.dao.facade.core.student;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.student.Enrollment;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface EnrollmentDao extends AbstractRepository<Enrollment,Long>  {

    List<Enrollment> findByStudentId(Long id);
    int deleteByStudentId(Long id);
    long countByStudentEmail(String email);
    List<Enrollment> findByCourseId(Long id);
    int deleteByCourseId(Long id);
    long countByCourseId(Long id);


}
