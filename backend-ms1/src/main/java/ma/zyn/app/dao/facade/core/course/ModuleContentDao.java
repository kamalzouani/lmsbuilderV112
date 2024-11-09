package ma.zyn.app.dao.facade.core.course;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.course.ModuleContent;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ModuleContentDao extends AbstractRepository<ModuleContent,Long>  {

    List<ModuleContent> findByModuleId(Long id);
    int deleteByModuleId(Long id);
    long countByModuleId(Long id);


}
