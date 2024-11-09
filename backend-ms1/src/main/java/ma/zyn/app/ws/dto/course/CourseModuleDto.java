package  ma.zyn.app.ws.dto.course;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.math.BigDecimal;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseModuleDto  extends AuditBaseDto {

    private String name  ;
    private String description  ;
    private Integer order  = 0 ;
    private BigDecimal duration  ;

    private CourseDto course ;

    private List<ModuleContentDto> moduleContents ;


    public CourseModuleDto(){
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


    public Integer getOrder(){
        return this.order;
    }
    public void setOrder(Integer order){
        this.order = order;
    }


    public BigDecimal getDuration(){
        return this.duration;
    }
    public void setDuration(BigDecimal duration){
        this.duration = duration;
    }


    public CourseDto getCourse(){
        return this.course;
    }

    public void setCourse(CourseDto course){
        this.course = course;
    }



    public List<ModuleContentDto> getModuleContents(){
        return this.moduleContents;
    }

    public void setModuleContents(List<ModuleContentDto> moduleContents){
        this.moduleContents = moduleContents;
    }



}
