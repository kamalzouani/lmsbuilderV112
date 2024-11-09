package ma.zyn.app.dao.facade.core.course;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.course.Category;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CategoryDao extends AbstractRepository<Category,Long>  {



}
