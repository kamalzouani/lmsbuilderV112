package ma.zyn.app.unit.service.impl.admin.course;

import ma.zyn.app.bean.core.course.CourseModule;
import ma.zyn.app.dao.facade.core.course.CourseModuleDao;
import ma.zyn.app.service.impl.admin.course.CourseModuleAdminServiceImpl;

import ma.zyn.app.bean.core.course.ModuleContent ;
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
class CourseModuleStudentServiceImplTest {

    @Mock
    private CourseModuleDao repository;
    private AutoCloseable autoCloseable;
    private CourseModuleStudentServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CourseModuleAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllCourseModule() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveCourseModule() {
        // Given
        CourseModule toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteCourseModule() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetCourseModuleById() {
        // Given
        Long idToRetrieve = 1L; // Example CourseModule ID to retrieve
        CourseModule expected = new CourseModule(); // You need to replace CourseModule with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        CourseModule result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private CourseModule constructSample(int i) {
		CourseModule given = new CourseModule();
        given.setName("name-"+i);
        given.setDescription("description-"+i);
        given.setOrder(i);
        given.setDuration(BigDecimal.TEN);
        given.setCourse(new Course(1L));
        List<ModuleContent> moduleContents = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                ModuleContent element = new ModuleContent();
                                                element.setId((long)id);
                                                element.setName("name"+id);
                                                element.setDescription("description"+id);
                                                element.setType("type"+id);
                                                element.setUrl("url"+id);
                                                element.setModule(new CourseModule(Long.valueOf(5)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setModuleContents(moduleContents);
        return given;
    }

}
