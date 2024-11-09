package  ma.zyn.app.ws.converter.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.converter.course.CategoryConverter;
import ma.zyn.app.bean.core.course.Category;
import ma.zyn.app.ws.converter.instructor.InstructorConverter;
import ma.zyn.app.bean.core.instructor.Instructor;
import ma.zyn.app.ws.converter.course.ModuleContentConverter;
import ma.zyn.app.bean.core.course.ModuleContent;
import ma.zyn.app.ws.converter.course.CourseModuleConverter;
import ma.zyn.app.bean.core.course.CourseModule;

import ma.zyn.app.bean.core.instructor.Instructor;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.course.Course;
import ma.zyn.app.ws.dto.course.CourseDto;

@Component
public class CourseConverter {

    @Autowired
    private CategoryConverter categoryConverter ;
    @Autowired
    private InstructorConverter instructorConverter ;
    @Autowired
    private ModuleContentConverter moduleContentConverter ;
    @Autowired
    private CourseModuleConverter courseModuleConverter ;
    private boolean instructor;
    private boolean category;
    private boolean courseModules;

    public  CourseConverter() {
        init(true);
    }

    public Course toItem(CourseDto dto) {
        if (dto == null) {
            return null;
        } else {
        Course item = new Course();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getStartDate()))
                item.setStartDate(DateUtil.stringEnToDate(dto.getStartDate()));
            if(StringUtil.isNotEmpty(dto.getEndDate()))
                item.setEndDate(DateUtil.stringEnToDate(dto.getEndDate()));
            if(StringUtil.isNotEmpty(dto.getDuration()))
                item.setDuration(dto.getDuration());
            if(StringUtil.isNotEmpty(dto.getLevel()))
                item.setLevel(dto.getLevel());
            if(StringUtil.isNotEmpty(dto.getPrice()))
                item.setPrice(dto.getPrice());
            if(dto.getInstructor() != null && dto.getInstructor().getId() != null){
                item.setInstructor(new Instructor());
                item.getInstructor().setId(dto.getInstructor().getId());
                item.getInstructor().setEmail(dto.getInstructor().getEmail());
            }

            if(this.category && dto.getCategory()!=null)
                item.setCategory(categoryConverter.toItem(dto.getCategory())) ;


            if(this.courseModules && ListUtil.isNotEmpty(dto.getCourseModules()))
                item.setCourseModules(courseModuleConverter.toItem(dto.getCourseModules()));


        return item;
        }
    }


    public CourseDto toDto(Course item) {
        if (item == null) {
            return null;
        } else {
            CourseDto dto = new CourseDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(item.getStartDate()!=null)
                dto.setStartDate(DateUtil.dateTimeToString(item.getStartDate()));
            if(item.getEndDate()!=null)
                dto.setEndDate(DateUtil.dateTimeToString(item.getEndDate()));
            if(StringUtil.isNotEmpty(item.getDuration()))
                dto.setDuration(item.getDuration());
            if(StringUtil.isNotEmpty(item.getLevel()))
                dto.setLevel(item.getLevel());
            if(StringUtil.isNotEmpty(item.getPrice()))
                dto.setPrice(item.getPrice());
            if(this.instructor && item.getInstructor()!=null) {
                dto.setInstructor(instructorConverter.toDto(item.getInstructor())) ;

            }
            if(this.category && item.getCategory()!=null) {
                dto.setCategory(categoryConverter.toDto(item.getCategory())) ;

            }
        if(this.courseModules && ListUtil.isNotEmpty(item.getCourseModules())){
            courseModuleConverter.init(true);
            courseModuleConverter.setCourse(false);
            dto.setCourseModules(courseModuleConverter.toDto(item.getCourseModules()));
            courseModuleConverter.setCourse(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.courseModules = value;
    }
    public void initObject(boolean value) {
        this.instructor = value;
        this.category = value;
    }
	
    public List<Course> toItem(List<CourseDto> dtos) {
        List<Course> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CourseDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CourseDto> toDto(List<Course> items) {
        List<CourseDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Course item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CourseDto dto, Course t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getInstructor() == null  && dto.getInstructor() != null){
            t.setInstructor(new Instructor());
        }else if (t.getInstructor() != null  && dto.getInstructor() != null){
            t.setInstructor(null);
            t.setInstructor(new Instructor());
        }
        if(t.getCategory() == null  && dto.getCategory() != null){
            t.setCategory(new Category());
        }else if (t.getCategory() != null  && dto.getCategory() != null){
            t.setCategory(null);
            t.setCategory(new Category());
        }
        if (dto.getInstructor() != null)
        instructorConverter.copy(dto.getInstructor(), t.getInstructor());
        if (dto.getCategory() != null)
        categoryConverter.copy(dto.getCategory(), t.getCategory());
        if (dto.getCourseModules() != null)
            t.setCourseModules(courseModuleConverter.copy(dto.getCourseModules()));
    }

    public List<Course> copy(List<CourseDto> dtos) {
        List<Course> result = new ArrayList<>();
        if (dtos != null) {
            for (CourseDto dto : dtos) {
                Course instance = new Course();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public CategoryConverter getCategoryConverter(){
        return this.categoryConverter;
    }
    public void setCategoryConverter(CategoryConverter categoryConverter ){
        this.categoryConverter = categoryConverter;
    }
    public InstructorConverter getInstructorConverter(){
        return this.instructorConverter;
    }
    public void setInstructorConverter(InstructorConverter instructorConverter ){
        this.instructorConverter = instructorConverter;
    }
    public ModuleContentConverter getModuleContentConverter(){
        return this.moduleContentConverter;
    }
    public void setModuleContentConverter(ModuleContentConverter moduleContentConverter ){
        this.moduleContentConverter = moduleContentConverter;
    }
    public CourseModuleConverter getCourseModuleConverter(){
        return this.courseModuleConverter;
    }
    public void setCourseModuleConverter(CourseModuleConverter courseModuleConverter ){
        this.courseModuleConverter = courseModuleConverter;
    }
    public boolean  isInstructor(){
        return this.instructor;
    }
    public void  setInstructor(boolean instructor){
        this.instructor = instructor;
    }
    public boolean  isCategory(){
        return this.category;
    }
    public void  setCategory(boolean category){
        this.category = category;
    }
    public boolean  isCourseModules(){
        return this.courseModules ;
    }
    public void  setCourseModules(boolean courseModules ){
        this.courseModules  = courseModules ;
    }
}
