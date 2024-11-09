package ma.zyn.app.bean.core.course;

import java.util.List;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "course_module")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="course_module_seq",sequenceName="course_module_seq",allocationSize=1, initialValue = 1)
public class CourseModule  extends BaseEntity     {




    @Column(length = 500)
    private String name;

    private String description;

    private Integer order = 0;

    private BigDecimal duration = BigDecimal.ZERO;

    private Course course ;

    private List<ModuleContent> moduleContents ;

    public CourseModule(){
        super();
    }

    public CourseModule(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="course_module_seq")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course")
    public Course getCourse(){
        return this.course;
    }
    public void setCourse(Course course){
        this.course = course;
    }
    @OneToMany(mappedBy = "courseModule")
    public List<ModuleContent> getModuleContents(){
        return this.moduleContents;
    }

    public void setModuleContents(List<ModuleContent> moduleContents){
        this.moduleContents = moduleContents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseModule courseModule = (CourseModule) o;
        return id != null && id.equals(courseModule.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

