package ma.zyn.app.bean.core.course;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "module_content")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="module_content_seq",sequenceName="module_content_seq",allocationSize=1, initialValue = 1)
public class ModuleContent  extends BaseEntity     {




    @Column(length = 500)
    private String name;

    private String description;

    @Column(length = 500)
    private String type;

    @Column(length = 500)
    private String url;

    private CourseModule module ;


    public ModuleContent(){
        super();
    }

    public ModuleContent(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="module_content_seq")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module")
    public CourseModule getModule(){
        return this.module;
    }
    public void setModule(CourseModule module){
        this.module = module;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModuleContent moduleContent = (ModuleContent) o;
        return id != null && id.equals(moduleContent.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

