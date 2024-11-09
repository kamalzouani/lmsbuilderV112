package  ma.zyn.app.dao.criteria.core.course;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class CourseModuleCriteria extends  BaseCriteria  {

    private String name;
    private String nameLike;
    private String description;
    private String descriptionLike;
    private String order;
    private String orderMin;
    private String orderMax;
    private String duration;
    private String durationMin;
    private String durationMax;

    private CourseCriteria course ;
    private List<CourseCriteria> courses ;


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

    public String getOrder(){
        return this.order;
    }
    public void setOrder(String order){
        this.order = order;
    }   
    public String getOrderMin(){
        return this.orderMin;
    }
    public void setOrderMin(String orderMin){
        this.orderMin = orderMin;
    }
    public String getOrderMax(){
        return this.orderMax;
    }
    public void setOrderMax(String orderMax){
        this.orderMax = orderMax;
    }
      
    public String getDuration(){
        return this.duration;
    }
    public void setDuration(String duration){
        this.duration = duration;
    }   
    public String getDurationMin(){
        return this.durationMin;
    }
    public void setDurationMin(String durationMin){
        this.durationMin = durationMin;
    }
    public String getDurationMax(){
        return this.durationMax;
    }
    public void setDurationMax(String durationMax){
        this.durationMax = durationMax;
    }
      

    public CourseCriteria getCourse(){
        return this.course;
    }

    public void setCourse(CourseCriteria course){
        this.course = course;
    }
    public List<CourseCriteria> getCourses(){
        return this.courses;
    }

    public void setCourses(List<CourseCriteria> courses){
        this.courses = courses;
    }
}
