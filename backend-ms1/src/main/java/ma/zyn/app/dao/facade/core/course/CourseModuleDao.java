package ma.zyn.app.dao.facade.core.course;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.course.CourseModule;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CourseModuleDao extends AbstractRepository<CourseModule,Long>  {

    List<CourseModule> findByCourseId(Long id);
    int deleteByCourseId(Long id);
    long countByCourseId(Long id);


}
