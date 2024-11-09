package  ma.zyn.app.ws.dto.instructor;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import ma.zyn.app.zynerator.security.bean.Role;
import java.util.Collection;
import ma.zyn.app.zynerator.security.ws.dto.UserDto;
import java.util.List;


import ma.zyn.app.ws.dto.course.CategoryDto;
import ma.zyn.app.ws.dto.course.CourseModuleDto;
import ma.zyn.app.ws.dto.course.CourseDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstructorDto  extends UserDto {

    private String bio  ;
    private String expertise  ;


    private List<CourseDto> courses ;


    private Collection<Role> roles;
    public InstructorDto(){
        super();
    }




    public String getBio(){
        return this.bio;
    }
    public void setBio(String bio){
        this.bio = bio;
    }


    public String getExpertise(){
        return this.expertise;
    }
    public void setExpertise(String expertise){
        this.expertise = expertise;
    }













    public List<CourseDto> getCourses(){
        return this.courses;
    }

    public void setCourses(List<CourseDto> courses){
        this.courses = courses;
    }




    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
