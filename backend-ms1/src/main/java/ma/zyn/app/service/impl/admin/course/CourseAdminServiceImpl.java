package ma.zyn.app.service.impl.admin.course;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.course.Course;
import ma.zyn.app.dao.criteria.core.course.CourseCriteria;
import ma.zyn.app.dao.facade.core.course.CourseDao;
import ma.zyn.app.dao.specification.core.course.CourseSpecification;
import ma.zyn.app.service.facade.admin.course.CourseAdminService;
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

import ma.zyn.app.service.facade.admin.course.CategoryAdminService ;
import ma.zyn.app.bean.core.course.Category ;
import ma.zyn.app.service.facade.admin.instructor.InstructorAdminService ;
import ma.zyn.app.bean.core.instructor.Instructor ;
import ma.zyn.app.service.facade.admin.course.CourseModuleAdminService ;
import ma.zyn.app.bean.core.course.CourseModule ;

import java.util.List;
@Service
public class CourseAdminServiceImpl implements CourseAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Course update(Course t) {
        Course loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Course.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Course findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Course findOrSave(Course t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Course result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Course> findAll() {
        return dao.findAll();
    }

    public List<Course> findByCriteria(CourseCriteria criteria) {
        List<Course> content = null;
        if (criteria != null) {
            CourseSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private CourseSpecification constructSpecification(CourseCriteria criteria) {
        CourseSpecification mySpecification =  (CourseSpecification) RefelexivityUtil.constructObjectUsingOneParam(CourseSpecification.class, criteria);
        return mySpecification;
    }

    public List<Course> findPaginatedByCriteria(CourseCriteria criteria, int page, int pageSize, String order, String sortField) {
        CourseSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CourseCriteria criteria) {
        CourseSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Course> findByInstructorId(Long id){
        return dao.findByInstructorId(id);
    }
    public int deleteByInstructorId(Long id){
        return dao.deleteByInstructorId(id);
    }
    public long countByInstructorEmail(String email){
        return dao.countByInstructorEmail(email);
    }
    public List<Course> findByCategoryId(Long id){
        return dao.findByCategoryId(id);
    }
    public int deleteByCategoryId(Long id){
        return dao.deleteByCategoryId(id);
    }
    public long countByCategoryId(Long id){
        return dao.countByCategoryId(id);
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
        courseModuleService.deleteByCourseId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Course> delete(List<Course> list) {
		List<Course> result = new ArrayList();
        if (list != null) {
            for (Course t : list) {
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
    public Course create(Course t) {
        Course loaded = findByReferenceEntity(t);
        Course saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getCourseModules() != null) {
                t.getCourseModules().forEach(element-> {
                    element.setCourse(saved);
                    courseModuleService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public Course findWithAssociatedLists(Long id){
        Course result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setCourseModules(courseModuleService.findByCourseId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Course> update(List<Course> ts, boolean createIfNotExist) {
        List<Course> result = new ArrayList<>();
        if (ts != null) {
            for (Course t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Course loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Course t, Course loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(Course course){
    if(course !=null && course.getId() != null){
        List<List<CourseModule>> resultCourseModules= courseModuleService.getToBeSavedAndToBeDeleted(courseModuleService.findByCourseId(course.getId()),course.getCourseModules());
            courseModuleService.delete(resultCourseModules.get(1));
        emptyIfNull(resultCourseModules.get(0)).forEach(e -> e.setCourse(course));
        courseModuleService.update(resultCourseModules.get(0),true);
        }
    }








    public Course findByReferenceEntity(Course t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Course t){
        if( t != null) {
            t.setInstructor(instructorService.findOrSave(t.getInstructor()));
            t.setCategory(categoryService.findOrSave(t.getCategory()));
        }
    }



    public List<Course> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Course>> getToBeSavedAndToBeDeleted(List<Course> oldList, List<Course> newList) {
        List<List<Course>> result = new ArrayList<>();
        List<Course> resultDelete = new ArrayList<>();
        List<Course> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Course> oldList, List<Course> newList, List<Course> resultUpdateOrSave, List<Course> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Course myOld = oldList.get(i);
                Course t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Course myNew = newList.get(i);
                Course t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private CategoryAdminService categoryService ;
    @Autowired
    private InstructorAdminService instructorService ;
    @Autowired
    private CourseModuleAdminService courseModuleService ;

    public CourseAdminServiceImpl(CourseDao dao) {
        this.dao = dao;
    }

    private CourseDao dao;
}
