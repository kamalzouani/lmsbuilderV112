package  ma.zyn.app.ws.dto.course;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zyn.app.ws.dto.instructor.InstructorDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDto  extends AuditBaseDto {

    private String name  ;
    private String description  ;
    private String startDate ;
    private String endDate ;
    private BigDecimal duration  ;
    private String level  ;
    private BigDecimal price  ;

    private InstructorDto instructor ;
    private CategoryDto category ;

    private List<CourseModuleDto> courseModules ;


    public CourseDto(){
        super();
    }




    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getStartDate(){
        return this.startDate;
    }
    public void setStartDate(String startDate){
        this.startDate = startDate;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getEndDate(){
        return this.endDate;
    }
    public void setEndDate(String endDate){
        this.endDate = endDate;
    }


    public BigDecimal getDuration(){
        return this.duration;
    }
    public void setDuration(BigDecimal duration){
        this.duration = duration;
    }


    public String getLevel(){
        return this.level;
    }
    public void setLevel(String level){
        this.level = level;
    }


    public BigDecimal getPrice(){
        return this.price;
    }
    public void setPrice(BigDecimal price){
        this.price = price;
    }


    public InstructorDto getInstructor(){
        return this.instructor;
    }

    public void setInstructor(InstructorDto instructor){
        this.instructor = instructor;
    }
    public CategoryDto getCategory(){
        return this.category;
    }

    public void setCategory(CategoryDto category){
        this.category = category;
    }



    public List<CourseModuleDto> getCourseModules(){
        return this.courseModules;
    }

    public void setCourseModules(List<CourseModuleDto> courseModules){
        this.courseModules = courseModules;
    }



}
