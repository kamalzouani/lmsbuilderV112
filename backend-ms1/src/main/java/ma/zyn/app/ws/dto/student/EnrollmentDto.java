package  ma.zyn.app.ws.dto.student;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.ws.dto.course.CourseDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrollmentDto  extends AuditBaseDto {

    private String enrollmentDate ;
    private String startDate ;
    private String endDate ;
    private String status  ;

    private StudentDto student ;
    private CourseDto course ;



    public EnrollmentDto(){
        super();
    }




    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getEnrollmentDate(){
        return this.enrollmentDate;
    }
    public void setEnrollmentDate(String enrollmentDate){
        this.enrollmentDate = enrollmentDate;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getStartDate(){
        return this.startDate;
    }
    public void setStartDate(String startDate){
        this.startDate = startDate;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getEndDate(){
        return this.endDate;
    }
    public void setEndDate(String endDate){
        this.endDate = endDate;
    }


    public String getStatus(){
        return this.status;
    }
    public void setStatus(String status){
        this.status = status;
    }


    public StudentDto getStudent(){
        return this.student;
    }

    public void setStudent(StudentDto student){
        this.student = student;
    }
    public CourseDto getCourse(){
        return this.course;
    }

    public void setCourse(CourseDto course){
        this.course = course;
    }






}
