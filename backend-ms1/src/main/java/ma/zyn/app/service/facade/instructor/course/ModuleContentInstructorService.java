package ma.zyn.app.service.facade.instructor.course;

import java.util.List;
import ma.zyn.app.bean.core.course.ModuleContent;
import ma.zyn.app.dao.criteria.core.course.ModuleContentCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface ModuleContentInstructorService {



    List<ModuleContent> findByModuleId(Long id);
    int deleteByModuleId(Long id);
    long countByModuleId(Long id);




	ModuleContent create(ModuleContent t);

    ModuleContent update(ModuleContent t);

    List<ModuleContent> update(List<ModuleContent> ts,boolean createIfNotExist);

    ModuleContent findById(Long id);

    ModuleContent findOrSave(ModuleContent t);

    ModuleContent findByReferenceEntity(ModuleContent t);

    ModuleContent findWithAssociatedLists(Long id);

    List<ModuleContent> findAllOptimized();

    List<ModuleContent> findAll();

    List<ModuleContent> findByCriteria(ModuleContentCriteria criteria);

    List<ModuleContent> findPaginatedByCriteria(ModuleContentCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ModuleContentCriteria criteria);

    List<ModuleContent> delete(List<ModuleContent> ts);

    boolean deleteById(Long id);

    List<List<ModuleContent>> getToBeSavedAndToBeDeleted(List<ModuleContent> oldList, List<ModuleContent> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
