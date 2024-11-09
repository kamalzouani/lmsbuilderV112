package  ma.zyn.app.ws.converter.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.course.CourseModuleConverter;
import ma.zyn.app.bean.core.course.CourseModule;

import ma.zyn.app.bean.core.course.CourseModule;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.course.ModuleContent;
import ma.zyn.app.ws.dto.course.ModuleContentDto;

@Component
public class ModuleContentConverter {

    @Autowired
    private CourseModuleConverter courseModuleConverter ;
    private boolean module;

    public  ModuleContentConverter() {
        initObject(true);
    }

    public ModuleContent toItem(ModuleContentDto dto) {
        if (dto == null) {
            return null;
        } else {
        ModuleContent item = new ModuleContent();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getType()))
                item.setType(dto.getType());
            if(StringUtil.isNotEmpty(dto.getUrl()))
                item.setUrl(dto.getUrl());
            if(dto.getModule() != null && dto.getModule().getId() != null){
                item.setModule(new CourseModule());
                item.getModule().setId(dto.getModule().getId());
                item.getModule().setId(dto.getModule().getId());
            }




        return item;
        }
    }


    public ModuleContentDto toDto(ModuleContent item) {
        if (item == null) {
            return null;
        } else {
            ModuleContentDto dto = new ModuleContentDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getType()))
                dto.setType(item.getType());
            if(StringUtil.isNotEmpty(item.getUrl()))
                dto.setUrl(item.getUrl());
            if(this.module && item.getModule()!=null) {
                dto.setModule(courseModuleConverter.toDto(item.getModule())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.module = value;
    }
	
    public List<ModuleContent> toItem(List<ModuleContentDto> dtos) {
        List<ModuleContent> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ModuleContentDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ModuleContentDto> toDto(List<ModuleContent> items) {
        List<ModuleContentDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ModuleContent item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ModuleContentDto dto, ModuleContent t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getModule() == null  && dto.getModule() != null){
            t.setModule(new CourseModule());
        }else if (t.getModule() != null  && dto.getModule() != null){
            t.setModule(null);
            t.setModule(new CourseModule());
        }
        if (dto.getModule() != null)
        courseModuleConverter.copy(dto.getModule(), t.getModule());
    }

    public List<ModuleContent> copy(List<ModuleContentDto> dtos) {
        List<ModuleContent> result = new ArrayList<>();
        if (dtos != null) {
            for (ModuleContentDto dto : dtos) {
                ModuleContent instance = new ModuleContent();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public CourseModuleConverter getCourseModuleConverter(){
        return this.courseModuleConverter;
    }
    public void setCourseModuleConverter(CourseModuleConverter courseModuleConverter ){
        this.courseModuleConverter = courseModuleConverter;
    }
    public boolean  isModule(){
        return this.module;
    }
    public void  setModule(boolean module){
        this.module = module;
    }
}
