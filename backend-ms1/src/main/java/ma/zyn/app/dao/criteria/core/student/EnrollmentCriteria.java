package  ma.zyn.app.dao.criteria.core.student;


import ma.zyn.app.dao.criteria.core.course.CourseCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class EnrollmentCriteria extends  BaseCriteria  {

    private LocalDateTime enrollmentDate;
    private LocalDateTime enrollmentDateFrom;
    private LocalDateTime enrollmentDateTo;
    private LocalDateTime startDate;
    private LocalDateTime startDateFrom;
    private LocalDateTime startDateTo;
    private LocalDateTime endDate;
    private LocalDateTime endDateFrom;
    private LocalDateTime endDateTo;
    private String status;
    private String statusLike;

    private StudentCriteria student ;
    private List<StudentCriteria> students ;
    private CourseCriteria course ;
    private List<CourseCriteria> courses ;


    public LocalDateTime getEnrollmentDate(){
        return this.enrollmentDate;
    }
    public void setEnrollmentDate(LocalDateTime enrollmentDate){
        this.enrollmentDate = enrollmentDate;
    }
    public LocalDateTime getEnrollmentDateFrom(){
        return this.enrollmentDateFrom;
    }
    public void setEnrollmentDateFrom(LocalDateTime enrollmentDateFrom){
        this.enrollmentDateFrom = enrollmentDateFrom;
    }
    public LocalDateTime getEnrollmentDateTo(){
        return this.enrollmentDateTo;
    }
    public void setEnrollmentDateTo(LocalDateTime enrollmentDateTo){
        this.enrollmentDateTo = enrollmentDateTo;
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
    public String getStatus(){
        return this.status;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatusLike(){
        return this.statusLike;
    }
    public void setStatusLike(String statusLike){
        this.statusLike = statusLike;
    }


    public StudentCriteria getStudent(){
        return this.student;
    }

    public void setStudent(StudentCriteria student){
        this.student = student;
    }
    public List<StudentCriteria> getStudents(){
        return this.students;
    }

    public void setStudents(List<StudentCriteria> students){
        this.students = students;
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
