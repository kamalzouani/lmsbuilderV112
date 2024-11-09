package ma.zyn.app.unit.service.impl.admin.instructor;

import ma.zyn.app.bean.core.instructor.Instructor;
import ma.zyn.app.dao.facade.core.instructor.InstructorDao;
import ma.zyn.app.service.impl.admin.instructor.InstructorAdminServiceImpl;

import ma.zyn.app.bean.core.course.Category ;
import ma.zyn.app.bean.core.instructor.Instructor ;
import ma.zyn.app.bean.core.course.Course ;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class InstructorStudentServiceImplTest {

    @Mock
    private InstructorDao repository;
    private AutoCloseable autoCloseable;
    private InstructorStudentServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new InstructorAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllInstructor() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveInstructor() {
        // Given
        Instructor toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteInstructor() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetInstructorById() {
        // Given
        Long idToRetrieve = 1L; // Example Instructor ID to retrieve
        Instructor expected = new Instructor(); // You need to replace Instructor with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Instructor result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Instructor constructSample(int i) {
		Instructor given = new Instructor();
        given.setBio("bio-"+i);
        given.setExpertise("expertise-"+i);
        List<Course> courses = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                Course element = new Course();
                                                element.setId((long)id);
                                                element.setName("name"+id);
                                                element.setDescription("description"+id);
                                                element.setStartDate(LocalDateTime.now());
                                                element.setEndDate(LocalDateTime.now());
                                                element.setDuration(new BigDecimal(5*10));
                                                element.setLevel("level"+id);
                                                element.setPrice(new BigDecimal(7*10));
                                                element.setInstructor(new Instructor(Long.valueOf(8)));
                                                element.setCategory(new Category(Long.valueOf(9)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setCourses(courses);
        given.setCredentialsNonExpired(false);
        given.setEnabled(false);
        given.setEmail("email-"+i);
        given.setPassword("password-"+i);
        given.setAccountNonLocked(false);
        given.setPasswordChanged(false);
        given.setUsername("username-"+i);
        given.setAccountNonExpired(false);
        return given;
    }

}
