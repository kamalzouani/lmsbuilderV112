package  ma.zyn.app.dao.criteria.core.course;


import ma.zyn.app.dao.criteria.core.instructor.InstructorCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class CourseCriteria extends  BaseCriteria  {

    private String name;
    private String nameLike;
    private String description;
    private String descriptionLike;
    private LocalDateTime startDate;
    private LocalDateTime startDateFrom;
    private LocalDateTime startDateTo;
    private LocalDateTime endDate;
    private LocalDateTime endDateFrom;
    private LocalDateTime endDateTo;
    private String duration;
    private String durationMin;
    private String durationMax;
    private String level;
    private String levelLike;
    private String price;
    private String priceMin;
    private String priceMax;

    private InstructorCriteria instructor ;
    private List<InstructorCriteria> instructors ;
    private CategoryCriteria category ;
    private List<CategoryCriteria> categorys ;


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

    public LocalDateTime getStartDate(){
        return this.startDate;
    }
    public void setStartDate(LocalDateTime startDate){
        this.startDate = startDate;
    }
    public LocalDateTime getStartDateFrom(){
        return this.startDateFrom;
    }
    public void setStartDateFrom(LocalDateTime startDateFrom){
        this.startDateFrom = startDateFrom;
    }
    public LocalDateTime getStartDateTo(){
        return this.startDateTo;
    }
    public void setStartDateTo(LocalDateTime startDateTo){
        this.startDateTo = startDateTo;
    }
    public LocalDateTime getEndDate(){
        return this.endDate;
    }
    public void setEndDate(LocalDateTime endDate){
        this.endDate = endDate;
    }
    public LocalDateTime getEndDateFrom(){
        return this.endDateFrom;
    }
    public void setEndDateFrom(LocalDateTime endDateFrom){
        this.endDateFrom = endDateFrom;
    }
    public LocalDateTime getEndDateTo(){
        return this.endDateTo;
    }
    public void setEndDateTo(LocalDateTime endDateTo){
        this.endDateTo = endDateTo;
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
      
    public String getLevel(){
        return this.level;
    }
    public void setLevel(String level){
        this.level = level;
    }
    public String getLevelLike(){
        return this.levelLike;
    }
    public void setLevelLike(String levelLike){
        this.levelLike = levelLike;
    }

    public String getPrice(){
        return this.price;
    }
    public void setPrice(String price){
        this.price = price;
    }   
    public String getPriceMin(){
        return this.priceMin;
    }
    public void setPriceMin(String priceMin){
        this.priceMin = priceMin;
    }
    public String getPriceMax(){
        return this.priceMax;
    }
    public void setPriceMax(String priceMax){
        this.priceMax = priceMax;
    }
      

    public InstructorCriteria getInstructor(){
        return this.instructor;
    }

    public void setInstructor(InstructorCriteria instructor){
        this.instructor = instructor;
    }
    public List<InstructorCriteria> getInstructors(){
        return this.instructors;
    }

    public void setInstructors(List<InstructorCriteria> instructors){
        this.instructors = instructors;
    }
    public CategoryCriteria getCategory(){
        return this.category;
    }

    public void setCategory(CategoryCriteria category){
        this.category = category;
    }
    public List<CategoryCriteria> getCategorys(){
        return this.categorys;
    }

    public void setCategorys(List<CategoryCriteria> categorys){
        this.categorys = categorys;
    }
}
