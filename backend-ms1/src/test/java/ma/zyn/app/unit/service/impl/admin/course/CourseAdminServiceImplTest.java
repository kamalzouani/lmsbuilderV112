package ma.zyn.app.unit.service.impl.admin.course;

import ma.zyn.app.bean.core.course.Course;
import ma.zyn.app.dao.facade.core.course.CourseDao;
import ma.zyn.app.service.impl.admin.course.CourseAdminServiceImpl;

import ma.zyn.app.bean.core.course.Category ;
import ma.zyn.app.bean.core.instructor.Instructor ;
import ma.zyn.app.bean.core.course.CourseModule ;
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
class CourseAdminServiceImplTest {

    @Mock
    private CourseDao repository;
    private AutoCloseable autoCloseable;
    private CourseAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CourseAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllCourse() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveCourse() {
        // Given
        Course toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteCourse() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetCourseById() {
        // Given
        Long idToRetrieve = 1L; // Example Course ID to retrieve
        Course expected = new Course(); // You need to replace Course with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Course result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Course constructSample(int i) {
		Course given = new Course();
        given.setName("name-"+i);
        given.setDescription("description-"+i);
        given.setStartDate(LocalDateTime.now());
        given.setEndDate(LocalDateTime.now());
        given.setDuration(BigDecimal.TEN);
        given.setLevel("level-"+i);
        given.setPrice(BigDecimal.TEN);
        given.setInstructor(new Instructor(1L));
        given.setCategory(new Category(1L));
        List<CourseModule> courseModules = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                CourseModule element = new CourseModule();
                                                element.setId((long)id);
                                                element.setName("name"+id);
                                                element.setDescription("description"+id);
                                                element.setOrder(3);
                                                element.setDuration(new BigDecimal(4*10));
                                                element.setCourse(new Course(Long.valueOf(5)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setCourseModules(courseModules);
        return given;
    }

}
