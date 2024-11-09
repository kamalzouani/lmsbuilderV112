package ma.zyn.app.service.facade.student.course;

import java.util.List;
import ma.zyn.app.bean.core.course.Course;
import ma.zyn.app.dao.criteria.core.course.CourseCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface CourseStudentService {



    List<Course> findByInstructorId(Long id);
    int deleteByInstructorId(Long id);
    long countByInstructorEmail(String email);
    List<Course> findByCategoryId(Long id);
    int deleteByCategoryId(Long id);
    long countByCategoryId(Long id);




	Course create(Course t);

    Course update(Course t);

    List<Course> update(List<Course> ts,boolean createIfNotExist);

    Course findById(Long id);

    Course findOrSave(Course t);

    Course findByReferenceEntity(Course t);

    Course findWithAssociatedLists(Long id);

    List<Course> findAllOptimized();

    List<Course> findAll();

    List<Course> findByCriteria(CourseCriteria criteria);

    List<Course> findPaginatedByCriteria(CourseCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CourseCriteria criteria);

    List<Course> delete(List<Course> ts);

    boolean deleteById(Long id);

    List<List<Course>> getToBeSavedAndToBeDeleted(List<Course> oldList, List<Course> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
