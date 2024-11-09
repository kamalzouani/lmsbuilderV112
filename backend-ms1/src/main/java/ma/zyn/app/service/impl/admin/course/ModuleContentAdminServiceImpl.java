package ma.zyn.app.service.impl.admin.course;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.course.ModuleContent;
import ma.zyn.app.dao.criteria.core.course.ModuleContentCriteria;
import ma.zyn.app.dao.facade.core.course.ModuleContentDao;
import ma.zyn.app.dao.specification.core.course.ModuleContentSpecification;
import ma.zyn.app.service.facade.admin.course.ModuleContentAdminService;
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

import ma.zyn.app.service.facade.admin.course.CourseModuleAdminService ;
import ma.zyn.app.bean.core.course.CourseModule ;

import java.util.List;
@Service
public class ModuleContentAdminServiceImpl implements ModuleContentAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ModuleContent update(ModuleContent t) {
        ModuleContent loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ModuleContent.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ModuleContent findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ModuleContent findOrSave(ModuleContent t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            ModuleContent result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ModuleContent> findAll() {
        return dao.findAll();
    }

    public List<ModuleContent> findByCriteria(ModuleContentCriteria criteria) {
        List<ModuleContent> content = null;
        if (criteria != null) {
            ModuleContentSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private ModuleContentSpecification constructSpecification(ModuleContentCriteria criteria) {
        ModuleContentSpecification mySpecification =  (ModuleContentSpecification) RefelexivityUtil.constructObjectUsingOneParam(ModuleContentSpecification.class, criteria);
        return mySpecification;
    }

    public List<ModuleContent> findPaginatedByCriteria(ModuleContentCriteria criteria, int page, int pageSize, String order, String sortField) {
        ModuleContentSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ModuleContentCriteria criteria) {
        ModuleContentSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<ModuleContent> findByModuleId(Long id){
        return dao.findByModuleId(id);
    }
    public int deleteByModuleId(Long id){
        return dao.deleteByModuleId(id);
    }
    public long countByModuleId(Long id){
        return dao.countByModuleId(id);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ModuleContent> delete(List<ModuleContent> list) {
		List<ModuleContent> result = new ArrayList();
        if (list != null) {
            for (ModuleContent t : list) {
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
    public ModuleContent create(ModuleContent t) {
        ModuleContent loaded = findByReferenceEntity(t);
        ModuleContent saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ModuleContent findWithAssociatedLists(Long id){
        ModuleContent result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ModuleContent> update(List<ModuleContent> ts, boolean createIfNotExist) {
        List<ModuleContent> result = new ArrayList<>();
        if (ts != null) {
            for (ModuleContent t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ModuleContent loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ModuleContent t, ModuleContent loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ModuleContent findByReferenceEntity(ModuleContent t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(ModuleContent t){
        if( t != null) {
            t.setModule(courseModuleService.findOrSave(t.getModule()));
        }
    }



    public List<ModuleContent> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<ModuleContent>> getToBeSavedAndToBeDeleted(List<ModuleContent> oldList, List<ModuleContent> newList) {
        List<List<ModuleContent>> result = new ArrayList<>();
        List<ModuleContent> resultDelete = new ArrayList<>();
        List<ModuleContent> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ModuleContent> oldList, List<ModuleContent> newList, List<ModuleContent> resultUpdateOrSave, List<ModuleContent> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ModuleContent myOld = oldList.get(i);
                ModuleContent t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ModuleContent myNew = newList.get(i);
                ModuleContent t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private CourseModuleAdminService courseModuleService ;

    public ModuleContentAdminServiceImpl(ModuleContentDao dao) {
        this.dao = dao;
    }

    private ModuleContentDao dao;
}
