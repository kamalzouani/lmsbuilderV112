package  ma.zyn.app.ws.dto.student;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import ma.zyn.app.zynerator.security.bean.Role;
import java.util.Collection;
import ma.zyn.app.zynerator.security.ws.dto.UserDto;
import java.util.List;


import ma.zyn.app.ws.dto.course.CategoryDto;
import ma.zyn.app.ws.dto.instructor.InstructorDto;
import ma.zyn.app.ws.dto.course.CourseModuleDto;
import ma.zyn.app.ws.dto.course.CourseDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto  extends UserDto {

    private String firstName  ;
    private String lastName  ;
    private String phone  ;


    private List<CourseDto> courses ;


    private Collection<Role> roles;
    public StudentDto(){
        super();
    }




    public String getFirstName(){
        return this.firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }


    public String getLastName(){
        return this.lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }


    public String getPhone(){
        return this.phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
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
