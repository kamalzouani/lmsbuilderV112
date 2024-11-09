package ma.zyn.app.bean.core.course;

import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.instructor.Instructor;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "course")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="course_seq",sequenceName="course_seq",allocationSize=1, initialValue = 1)
public class Course  extends BaseEntity     {




    @Column(length = 500)
    private String name;

    private String description;

    private LocalDateTime startDate ;

    private LocalDateTime endDate ;

    private BigDecimal duration = BigDecimal.ZERO;

    @Column(length = 500)
    private String level;

    private BigDecimal price = BigDecimal.ZERO;

    private Instructor instructor ;
    private Category category ;

    private List<CourseModule> courseModules ;

    public Course(){
        super();
    }

    public Course(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="course_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
      @Column(columnDefinition="TEXT")
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public LocalDateTime getStartDate(){
        return this.startDate;
    }
    public void setStartDate(LocalDateTime startDate){
        this.startDate = startDate;
    }
    public LocalDateTime getEndDate(){
        return this.endDate;
    }
    public void setEndDate(LocalDateTime endDate){
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor")
    public Instructor getInstructor(){
        return this.instructor;
    }
    public void setInstructor(Instructor instructor){
        this.instructor = instructor;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    public Category getCategory(){
        return this.category;
    }
    public void setCategory(Category category){
        this.category = category;
    }
    @OneToMany(mappedBy = "course")
    public List<CourseModule> getCourseModules(){
        return this.courseModules;
    }

    public void setCourseModules(List<CourseModule> courseModules){
        this.courseModules = courseModules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id != null && id.equals(course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

