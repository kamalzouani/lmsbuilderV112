package ma.zyn.app.unit.service.impl.admin.course;

import ma.zyn.app.bean.core.course.ModuleContent;
import ma.zyn.app.dao.facade.core.course.ModuleContentDao;
import ma.zyn.app.service.impl.admin.course.ModuleContentAdminServiceImpl;

import ma.zyn.app.bean.core.course.CourseModule ;
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
class ModuleContentStudentServiceImplTest {

    @Mock
    private ModuleContentDao repository;
    private AutoCloseable autoCloseable;
    private ModuleContentStudentServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ModuleContentAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllModuleContent() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveModuleContent() {
        // Given
        ModuleContent toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteModuleContent() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetModuleContentById() {
        // Given
        Long idToRetrieve = 1L; // Example ModuleContent ID to retrieve
        ModuleContent expected = new ModuleContent(); // You need to replace ModuleContent with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        ModuleContent result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private ModuleContent constructSample(int i) {
		ModuleContent given = new ModuleContent();
        given.setName("name-"+i);
        given.setDescription("description-"+i);
        given.setType("type-"+i);
        given.setUrl("url-"+i);
        given.setModule(new CourseModule(1L));
        return given;
    }

}
