package  ma.zyn.app.ws.dto.course;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModuleContentDto  extends AuditBaseDto {

    private String name  ;
    private String description  ;
    private String type  ;
    private String url  ;

    private CourseModuleDto module ;



    public ModuleContentDto(){
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


    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }


    public String getUrl(){
        return this.url;
    }
    public void setUrl(String url){
        this.url = url;
    }


    public CourseModuleDto getModule(){
        return this.module;
    }

    public void setModule(CourseModuleDto module){
        this.module = module;
    }






}
