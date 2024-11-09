package  ma.zyn.app.ws.converter.student;

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
import ma.zyn.app.ws.converter.course.CourseModuleConverter;
import ma.zyn.app.bean.core.course.CourseModule;
import ma.zyn.app.ws.converter.course.CourseConverter;
import ma.zyn.app.bean.core.course.Course;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.student.Student;
import ma.zyn.app.ws.dto.student.StudentDto;

@Component
public class StudentConverter {

    @Autowired
    private CategoryConverter categoryConverter ;
    @Autowired
    private InstructorConverter instructorConverter ;
    @Autowired
    private CourseModuleConverter courseModuleConverter ;
    @Autowired
    private CourseConverter courseConverter ;
    private boolean courses;

    public  StudentConverter() {
        initList(true);
    }

    public Student toItem(StudentDto dto) {
        if (dto == null) {
            return null;
        } else {
        Student item = new Student();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getFirstName()))
                item.setFirstName(dto.getFirstName());
            if(StringUtil.isNotEmpty(dto.getLastName()))
                item.setLastName(dto.getLastName());
            if(StringUtil.isNotEmpty(dto.getPhone()))
                item.setPhone(dto.getPhone());
            item.setCredentialsNonExpired(dto.getCredentialsNonExpired());
            item.setEnabled(dto.getEnabled());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            if(StringUtil.isNotEmpty(dto.getPassword()))
                item.setPassword(dto.getPassword());
            item.setAccountNonLocked(dto.getAccountNonLocked());
            item.setPasswordChanged(dto.getPasswordChanged());
            if(StringUtil.isNotEmpty(dto.getUsername()))
                item.setUsername(dto.getUsername());
            item.setAccountNonExpired(dto.getAccountNonExpired());

            if(this.courses && ListUtil.isNotEmpty(dto.getCourses()))
                item.setCourses(courseConverter.toItem(dto.getCourses()));

            //item.setRoles(dto.getRoles());

        return item;
        }
    }


    public StudentDto toDto(Student item) {
        if (item == null) {
            return null;
        } else {
            StudentDto dto = new StudentDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getFirstName()))
                dto.setFirstName(item.getFirstName());
            if(StringUtil.isNotEmpty(item.getLastName()))
                dto.setLastName(item.getLastName());
            if(StringUtil.isNotEmpty(item.getPhone()))
                dto.setPhone(item.getPhone());
            if(StringUtil.isNotEmpty(item.getCredentialsNonExpired()))
                dto.setCredentialsNonExpired(item.getCredentialsNonExpired());
            if(StringUtil.isNotEmpty(item.getEnabled()))
                dto.setEnabled(item.getEnabled());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
            if(StringUtil.isNotEmpty(item.getAccountNonLocked()))
                dto.setAccountNonLocked(item.getAccountNonLocked());
            if(StringUtil.isNotEmpty(item.getPasswordChanged()))
                dto.setPasswordChanged(item.getPasswordChanged());
            if(StringUtil.isNotEmpty(item.getUsername()))
                dto.setUsername(item.getUsername());
            if(StringUtil.isNotEmpty(item.getAccountNonExpired()))
                dto.setAccountNonExpired(item.getAccountNonExpired());
        if(this.courses && ListUtil.isNotEmpty(item.getCourses())){
            courseConverter.init(true);
            courseConverter.setStudent(false);
            dto.setCourses(courseConverter.toDto(item.getCourses()));
            courseConverter.setStudent(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.courses = value;
    }
	
    public List<Student> toItem(List<StudentDto> dtos) {
        List<Student> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (StudentDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<StudentDto> toDto(List<Student> items) {
        List<StudentDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Student item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(StudentDto dto, Student t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getCourses() != null)
            t.setCourses(courseConverter.copy(dto.getCourses()));
    }

    public List<Student> copy(List<StudentDto> dtos) {
        List<Student> result = new ArrayList<>();
        if (dtos != null) {
            for (StudentDto dto : dtos) {
                Student instance = new Student();
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
    public CourseModuleConverter getCourseModuleConverter(){
        return this.courseModuleConverter;
    }
    public void setCourseModuleConverter(CourseModuleConverter courseModuleConverter ){
        this.courseModuleConverter = courseModuleConverter;
    }
    public CourseConverter getCourseConverter(){
        return this.courseConverter;
    }
    public void setCourseConverter(CourseConverter courseConverter ){
        this.courseConverter = courseConverter;
    }
    public boolean  isCourses(){
        return this.courses ;
    }
    public void  setCourses(boolean courses ){
        this.courses  = courses ;
    }
}
