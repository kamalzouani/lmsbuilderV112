package ma.zyn.app.unit.ws.facade.admin.course;

import ma.zyn.app.bean.core.course.CourseModule;
import ma.zyn.app.service.impl.admin.course.CourseModuleAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.course.CourseModuleRestAdmin;
import ma.zyn.app.ws.converter.course.CourseModuleConverter;
import ma.zyn.app.ws.dto.course.CourseModuleDto;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseModuleRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private CourseModuleAdminServiceImpl service;
    @Mock
    private CourseModuleConverter converter;

    @InjectMocks
    private CourseModuleRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllCourseModuleTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<CourseModuleDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<CourseModuleDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveCourseModuleTest() throws Exception {
        // Mock data
        CourseModuleDto requestDto = new CourseModuleDto();
        CourseModule entity = new CourseModule();
        CourseModule saved = new CourseModule();
        CourseModuleDto savedDto = new CourseModuleDto();

        // Mock the converter to return the courseModule object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved courseModule DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<CourseModuleDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        CourseModuleDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved courseModule DTO
        assertEquals(savedDto.getName(), responseBody.getName());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
        assertEquals(savedDto.getOrder(), responseBody.getOrder());
        assertEquals(savedDto.getDuration(), responseBody.getDuration());
    }

}
