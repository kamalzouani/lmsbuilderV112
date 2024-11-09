package  ma.zyn.app.dao.criteria.core.course;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class ModuleContentCriteria extends  BaseCriteria  {

    private String name;
    private String nameLike;
    private String description;
    private String descriptionLike;
    private String type;
    private String typeLike;
    private String url;
    private String urlLike;

    private CourseModuleCriteria module ;
    private List<CourseModuleCriteria> modules ;


    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getNameLike(){
        return this.nameLike;
    }
    public void setNameLike(String nameLike){
        this.nameLike = nameLike;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }

    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getTypeLike(){
        return this.typeLike;
    }
    public void setTypeLike(String typeLike){
        this.typeLike = typeLike;
    }

    public String getUrl(){
        return this.url;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrlLike(){
        return this.urlLike;
    }
    public void setUrlLike(String urlLike){
        this.urlLike = urlLike;
    }


    public CourseModuleCriteria getModule(){
        return this.module;
    }

    public void setModule(CourseModuleCriteria module){
        this.module = module;
    }
    public List<CourseModuleCriteria> getModules(){
        return this.modules;
    }

    public void setModules(List<CourseModuleCriteria> modules){
        this.modules = modules;
    }
}
