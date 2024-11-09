package ma.zyn.app.service.facade.student.student;

import java.util.List;
import ma.zyn.app.bean.core.student.Enrollment;
import ma.zyn.app.dao.criteria.core.student.EnrollmentCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface EnrollmentStudentService {



    List<Enrollment> findByStudentId(Long id);
    int deleteByStudentId(Long id);
    long countByStudentEmail(String email);
    List<Enrollment> findByCourseId(Long id);
    int deleteByCourseId(Long id);
    long countByCourseId(Long id);




	Enrollment create(Enrollment t);

    Enrollment update(Enrollment t);

    List<Enrollment> update(List<Enrollment> ts,boolean createIfNotExist);

    Enrollment findById(Long id);

    Enrollment findOrSave(Enrollment t);

    Enrollment findByReferenceEntity(Enrollment t);

    Enrollment findWithAssociatedLists(Long id);

    List<Enrollment> findAllOptimized();

    List<Enrollment> findAll();

    List<Enrollment> findByCriteria(EnrollmentCriteria criteria);

    List<Enrollment> findPaginatedByCriteria(EnrollmentCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EnrollmentCriteria criteria);

    List<Enrollment> delete(List<Enrollment> ts);

    boolean deleteById(Long id);

    List<List<Enrollment>> getToBeSavedAndToBeDeleted(List<Enrollment> oldList, List<Enrollment> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
