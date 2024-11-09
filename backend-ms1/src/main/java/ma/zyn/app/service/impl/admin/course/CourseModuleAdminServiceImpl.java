package ma.zyn.app.service.impl.admin.course;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.course.CourseModule;
import ma.zyn.app.dao.criteria.core.course.CourseModuleCriteria;
import ma.zyn.app.dao.facade.core.course.CourseModuleDao;
import ma.zyn.app.dao.specification.core.course.CourseModuleSpecification;
import ma.zyn.app.service.facade.admin.course.CourseModuleAdminService;
import ma.zyn.app.zynerator.service.AbstractServiceImpl;
import static ma.zyn.app.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zyn.app.service.facade.admin.course.ModuleContentAdminService ;
import ma.zyn.app.bean.core.course.ModuleContent ;
import ma.zyn.app.service.facade.admin.course.CourseAdminService ;
import ma.zyn.app.bean.core.course.Course ;

import java.util.List;
@Service
public class CourseModuleAdminServiceImpl implements CourseModuleAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CourseModule update(CourseModule t) {
        CourseModule loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{CourseModule.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public CourseModule findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public CourseModule findOrSave(CourseModule t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            CourseModule result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<CourseModule> findAll() {
        return dao.findAll();
    }

    public List<CourseModule> findByCriteria(CourseModuleCriteria criteria) {
        List<CourseModule> content = null;
        if (criteria != null) {
            CourseModuleSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private CourseModuleSpecification constructSpecification(CourseModuleCriteria criteria) {
        CourseModuleSpecification mySpecification =  (CourseModuleSpecification) RefelexivityUtil.constructObjectUsingOneParam(CourseModuleSpecification.class, criteria);
        return mySpecification;
    }

    public List<CourseModule> findPaginatedByCriteria(CourseModuleCriteria criteria, int page, int pageSize, String order, String sortField) {
        CourseModuleSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CourseModuleCriteria criteria) {
        CourseModuleSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<CourseModule> findByCourseId(Long id){
        return dao.findByCourseId(id);
    }
    public int deleteByCourseId(Long id){
        return dao.deleteByCourseId(id);
    }
    public long countByCourseId(Long id){
        return dao.countByCourseId(id);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            deleteAssociatedLists(id);
            dao.deleteById(id);
        }
        return condition;
    }

    public void deleteAssociatedLists(Long id) {
        moduleContentService.deleteByCourseModuleId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CourseModule> delete(List<CourseModule> list) {
		List<CourseModule> result = new ArrayList();
        if (list != null) {
            for (CourseModule t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}else{
                    dao.deleteById(t.getId());
                }
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CourseModule create(CourseModule t) {
        CourseModule loaded = findByReferenceEntity(t);
        CourseModule saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getModuleContents() != null) {
                t.getModuleContents().forEach(element-> {
                    element.setCourseModule(saved);
                    moduleContentService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public CourseModule findWithAssociatedLists(Long id){
        CourseModule result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setModuleContents(moduleContentService.findByCourseModuleId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CourseModule> update(List<CourseModule> ts, boolean createIfNotExist) {
        List<CourseModule> result = new ArrayList<>();
        if (ts != null) {
            for (CourseModule t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    CourseModule loadedItem = dao.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, CourseModule t, CourseModule loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(CourseModule courseModule){
    if(courseModule !=null && courseModule.getId() != null){
        List<List<ModuleContent>> resultModuleContents= moduleContentService.getToBeSavedAndToBeDeleted(moduleContentService.findByCourseModuleId(courseModule.getId()),courseModule.getModuleContents());
            moduleContentService.delete(resultModuleContents.get(1));
        emptyIfNull(resultModuleContents.get(0)).forEach(e -> e.setCourseModule(courseModule));
        moduleContentService.update(resultModuleContents.get(0),true);
        }
    }








    public CourseModule findByReferenceEntity(CourseModule t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(CourseModule t){
        if( t != null) {
            t.setCourse(courseService.findOrSave(t.getCourse()));
        }
    }



    public List<CourseModule> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<CourseModule>> getToBeSavedAndToBeDeleted(List<CourseModule> oldList, List<CourseModule> newList) {
        List<List<CourseModule>> result = new ArrayList<>();
        List<CourseModule> resultDelete = new ArrayList<>();
        List<CourseModule> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
			extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<CourseModule> oldList, List<CourseModule> newList, List<CourseModule> resultUpdateOrSave, List<CourseModule> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                CourseModule myOld = oldList.get(i);
                CourseModule t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                CourseModule myNew = newList.get(i);
                CourseModule t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }







    @Autowired
    private ModuleContentAdminService moduleContentService ;
    @Autowired
    private CourseAdminService courseService ;

    public CourseModuleAdminServiceImpl(CourseModuleDao dao) {
        this.dao = dao;
    }

    private CourseModuleDao dao;
}
