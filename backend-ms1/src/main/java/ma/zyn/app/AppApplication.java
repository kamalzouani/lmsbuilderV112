package ma.zyn.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.openfeign.EnableFeignClients;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;


import ma.zyn.app.bean.core.instructor.Instructor;
import ma.zyn.app.service.facade.admin.instructor.InstructorAdminService;
import ma.zyn.app.bean.core.student.Student;
import ma.zyn.app.service.facade.admin.student.StudentAdminService;
import ma.zyn.app.zynerator.security.bean.*;
import ma.zyn.app.zynerator.security.common.AuthoritiesConstants;
import ma.zyn.app.zynerator.security.service.facade.*;


import ma.zyn.app.zynerator.security.bean.User;
import ma.zyn.app.zynerator.security.bean.Role;

@SpringBootApplication
//@EnableFeignClients
public class AppApplication {
    public static ConfigurableApplicationContext ctx;


    //state: primary success info secondary warning danger contrast
    //_STATE(Pending=warning,Rejeted=danger,Validated=success)
    public static void main(String[] args) {
        ctx=SpringApplication.run(AppApplication.class, args);
    }


    @Bean
    ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static ConfigurableApplicationContext getCtx() {
        return ctx;
    }

    @Bean
    public CommandLineRunner demo(UserService userService, RoleService roleService, ModelPermissionService modelPermissionService, ActionPermissionService actionPermissionService, ModelPermissionUserService modelPermissionUserService , InstructorAdminService instructorService, StudentAdminService studentService) {
    return (args) -> {
        if(true){


        // ModelPermissions
        List<ModelPermission> modelPermissions = new ArrayList<>();
        addPermission(modelPermissions);
        modelPermissions.forEach(e -> modelPermissionService.create(e));
        // ActionPermissions
        List<ActionPermission> actionPermissions = new ArrayList<>();
        addActionPermission(actionPermissions);
        actionPermissions.forEach(e -> actionPermissionService.create(e));

		// User Admin
        User userForAdmin = new User("admin");
		userForAdmin.setPassword("123");
		// Role Admin
		Role roleForAdmin = new Role();
		roleForAdmin.setAuthority(AuthoritiesConstants.ADMIN);
		roleForAdmin.setCreatedAt(LocalDateTime.now());
		Role roleForAdminSaved = roleService.create(roleForAdmin);
		RoleUser roleUserForAdmin = new RoleUser();
		roleUserForAdmin.setRole(roleForAdminSaved);
		if (userForAdmin.getRoleUsers() == null)
			userForAdmin.setRoleUsers(new ArrayList<>());

		userForAdmin.getRoleUsers().add(roleUserForAdmin);
		if (userForAdmin.getModelPermissionUsers() == null)
			userForAdmin.setModelPermissionUsers(new ArrayList<>());


        userForAdmin.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        userService.create(userForAdmin);

		// User Instructor
        Instructor userForInstructor = new Instructor("instructor");
		userForInstructor.setPassword("123");
		// Role Instructor
		Role roleForInstructor = new Role();
		roleForInstructor.setAuthority(AuthoritiesConstants.INSTRUCTOR);
		roleForInstructor.setCreatedAt(LocalDateTime.now());
		Role roleForInstructorSaved = roleService.create(roleForInstructor);
		RoleUser roleUserForInstructor = new RoleUser();
		roleUserForInstructor.setRole(roleForInstructorSaved);
		if (userForInstructor.getRoleUsers() == null)
			userForInstructor.setRoleUsers(new ArrayList<>());

		userForInstructor.getRoleUsers().add(roleUserForInstructor);
		if (userForInstructor.getModelPermissionUsers() == null)
			userForInstructor.setModelPermissionUsers(new ArrayList<>());


        userForInstructor.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        instructorService.create(userForInstructor);

		// User Student
        Student userForStudent = new Student("student");
		userForStudent.setPassword("123");
		// Role Student
		Role roleForStudent = new Role();
		roleForStudent.setAuthority(AuthoritiesConstants.STUDENT);
		roleForStudent.setCreatedAt(LocalDateTime.now());
		Role roleForStudentSaved = roleService.create(roleForStudent);
		RoleUser roleUserForStudent = new RoleUser();
		roleUserForStudent.setRole(roleForStudentSaved);
		if (userForStudent.getRoleUsers() == null)
			userForStudent.setRoleUsers(new ArrayList<>());

		userForStudent.getRoleUsers().add(roleUserForStudent);
		if (userForStudent.getModelPermissionUsers() == null)
			userForStudent.setModelPermissionUsers(new ArrayList<>());


        userForStudent.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        studentService.create(userForStudent);

            }
        };
    }




    private static String fakeString(String attributeName, int i) {
        return attributeName + i;
    }

    private static Long fakeLong(String attributeName, int i) {
        return  10L * i;
    }
    private static Integer fakeInteger(String attributeName, int i) {
        return  10 * i;
    }

    private static Double fakeDouble(String attributeName, int i) {
        return 10D * i;
    }

    private static BigDecimal fakeBigDecimal(String attributeName, int i) {
        return  BigDecimal.valueOf(i*1L*10);
    }

    private static Boolean fakeBoolean(String attributeName, int i) {
        return i % 2 == 0 ? true : false;
    }
    private static LocalDateTime fakeLocalDateTime(String attributeName, int i) {
        return LocalDateTime.now().plusDays(i);
    }


    private static void addPermission(List<ModelPermission> modelPermissions) {
        modelPermissions.add(new ModelPermission("Payment"));
        modelPermissions.add(new ModelPermission("Category"));
        modelPermissions.add(new ModelPermission("CourseModule"));
        modelPermissions.add(new ModelPermission("Instructor"));
        modelPermissions.add(new ModelPermission("Student"));
        modelPermissions.add(new ModelPermission("ModuleContent"));
        modelPermissions.add(new ModelPermission("Course"));
        modelPermissions.add(new ModelPermission("Enrollment"));
        modelPermissions.add(new ModelPermission("User"));
        modelPermissions.add(new ModelPermission("ModelPermission"));
        modelPermissions.add(new ModelPermission("ActionPermission"));
    }

    private static void addActionPermission(List<ActionPermission> actionPermissions) {
        actionPermissions.add(new ActionPermission("list"));
        actionPermissions.add(new ActionPermission("create"));
        actionPermissions.add(new ActionPermission("delete"));
        actionPermissions.add(new ActionPermission("edit"));
        actionPermissions.add(new ActionPermission("view"));
        actionPermissions.add(new ActionPermission("duplicate"));
    }


}


