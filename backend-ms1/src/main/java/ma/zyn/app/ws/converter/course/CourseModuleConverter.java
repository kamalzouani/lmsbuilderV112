package  ma.zyn.app.ws.converter.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.converter.course.ModuleContentConverter;
import ma.zyn.app.bean.core.course.ModuleContent;
import ma.zyn.app.ws.converter.course.CourseConverter;
import ma.zyn.app.bean.core.course.Course;

import ma.zyn.app.bean.core.course.Course;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.course.CourseModule;
import ma.zyn.app.ws.dto.course.CourseModuleDto;

@Component
public class CourseModuleConverter {

    @Autowired
    private ModuleContentConverter moduleContentConverter ;
    @Autowired
    private CourseConverter courseConverter ;
    private boolean course;
    private boolean moduleContents;

    public  CourseModuleConverter() {
        init(true);
    }

    public CourseModule toItem(CourseModuleDto dto) {
        if (dto == null) {
            return null;
        } else {
        CourseModule item = new CourseModule();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getOrder()))
                item.setOrder(dto.getOrder());
            if(StringUtil.isNotEmpty(dto.getDuration()))
                item.setDuration(dto.getDuration());
            if(dto.getCourse() != null && dto.getCourse().getId() != null){
                item.setCourse(new Course());
                item.getCourse().setId(dto.getCourse().getId());
                item.getCourse().setId(dto.getCourse().getId());
            }


            if(this.moduleContents && ListUtil.isNotEmpty(dto.getModuleContents()))
                item.setModuleContents(moduleContentConverter.toItem(dto.getModuleContents()));


        return item;
        }
    }


    public CourseModuleDto toDto(CourseModule item) {
        if (item == null) {
            return null;
        } else {
            CourseModuleDto dto = new CourseModuleDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getOrder()))
                dto.setOrder(item.getOrder());
            if(StringUtil.isNotEmpty(item.getDuration()))
                dto.setDuration(item.getDuration());
            if(this.course && item.getCourse()!=null) {
                dto.setCourse(courseConverter.toDto(item.getCourse())) ;

            }
        if(this.moduleContents && ListUtil.isNotEmpty(item.getModuleContents())){
            moduleContentConverter.init(true);
            moduleContentConverter.setCourseModule(false);
            dto.setModuleContents(moduleContentConverter.toDto(item.getModuleContents()));
            moduleContentConverter.setCourseModule(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.moduleContents = value;
    }
    public void initObject(boolean value) {
        this.course = value;
    }
	
    public List<CourseModule> toItem(List<CourseModuleDto> dtos) {
        List<CourseModule> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CourseModuleDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CourseModuleDto> toDto(List<CourseModule> items) {
        List<CourseModuleDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (CourseModule item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CourseModuleDto dto, CourseModule t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getCourse() == null  && dto.getCourse() != null){
            t.setCourse(new Course());
        }else if (t.getCourse() != null  && dto.getCourse() != null){
            t.setCourse(null);
            t.setCourse(new Course());
        }
        if (dto.getCourse() != null)
        courseConverter.copy(dto.getCourse(), t.getCourse());
        if (dto.getModuleContents() != null)
            t.setModuleContents(moduleContentConverter.copy(dto.getModuleContents()));
    }

    public List<CourseModule> copy(List<CourseModuleDto> dtos) {
        List<CourseModule> result = new ArrayList<>();
        if (dtos != null) {
            for (CourseModuleDto dto : dtos) {
                CourseModule instance = new CourseModule();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ModuleContentConverter getModuleContentConverter(){
        return this.moduleContentConverter;
    }
    public void setModuleContentConverter(ModuleContentConverter moduleContentConverter ){
        this.moduleContentConverter = moduleContentConverter;
    }
    public CourseConverter getCourseConverter(){
        return this.courseConverter;
    }
    public void setCourseConverter(CourseConverter courseConverter ){
        this.courseConverter = courseConverter;
    }
    public boolean  isCourse(){
        return this.course;
    }
    public void  setCourse(boolean course){
        this.course = course;
    }
    public boolean  isModuleContents(){
        return this.moduleContents ;
    }
    public void  setModuleContents(boolean moduleContents ){
        this.moduleContents  = moduleContents ;
    }
}
