package ma.zyn.app.service.facade.student.course;

import java.util.List;
import ma.zyn.app.bean.core.course.CourseModule;
import ma.zyn.app.dao.criteria.core.course.CourseModuleCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface CourseModuleStudentService {



    List<CourseModule> findByCourseId(Long id);
    int deleteByCourseId(Long id);
    long countByCourseId(Long id);




	CourseModule create(CourseModule t);

    CourseModule update(CourseModule t);

    List<CourseModule> update(List<CourseModule> ts,boolean createIfNotExist);

    CourseModule findById(Long id);

    CourseModule findOrSave(CourseModule t);

    CourseModule findByReferenceEntity(CourseModule t);

    CourseModule findWithAssociatedLists(Long id);

    List<CourseModule> findAllOptimized();

    List<CourseModule> findAll();

    List<CourseModule> findByCriteria(CourseModuleCriteria criteria);

    List<CourseModule> findPaginatedByCriteria(CourseModuleCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CourseModuleCriteria criteria);

    List<CourseModule> delete(List<CourseModule> ts);

    boolean deleteById(Long id);

    List<List<CourseModule>> getToBeSavedAndToBeDeleted(List<CourseModule> oldList, List<CourseModule> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
