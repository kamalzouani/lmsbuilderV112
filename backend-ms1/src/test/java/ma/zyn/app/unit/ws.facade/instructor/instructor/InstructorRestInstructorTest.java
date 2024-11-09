package ma.zyn.app.unit.ws.facade.instructor.instructor;

import ma.zyn.app.bean.core.instructor.Instructor;
import ma.zyn.app.service.impl.instructor.instructor.InstructorInstructorServiceImpl;
import ma.zyn.app.ws.facade.instructor.instructor.InstructorRestInstructor;
import ma.zyn.app.ws.converter.instructor.InstructorConverter;
import ma.zyn.app.ws.dto.instructor.InstructorDto;
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
public class InstructorRestInstructorTest {

    private MockMvc mockMvc;

    @Mock
    private InstructorInstructorServiceImpl service;
    @Mock
    private InstructorConverter converter;

    @InjectMocks
    private InstructorRestInstructor controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllInstructorTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<InstructorDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<InstructorDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveInstructorTest() throws Exception {
        // Mock data
        InstructorDto requestDto = new InstructorDto();
        Instructor entity = new Instructor();
        Instructor saved = new Instructor();
        InstructorDto savedDto = new InstructorDto();

        // Mock the converter to return the instructor object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved instructor DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<InstructorDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        InstructorDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved instructor DTO
        assertEquals(savedDto.getBio(), responseBody.getBio());
        assertEquals(savedDto.getExpertise(), responseBody.getExpertise());
        assertEquals(savedDto.getCredentialsNonExpired(), responseBody.getCredentialsNonExpired());
        assertEquals(savedDto.getEnabled(), responseBody.getEnabled());
        assertEquals(savedDto.getEmail(), responseBody.getEmail());
        assertEquals(savedDto.getPassword(), responseBody.getPassword());
        assertEquals(savedDto.getAccountNonLocked(), responseBody.getAccountNonLocked());
        assertEquals(savedDto.getPasswordChanged(), responseBody.getPasswordChanged());
        assertEquals(savedDto.getUsername(), responseBody.getUsername());
        assertEquals(savedDto.getAccountNonExpired(), responseBody.getAccountNonExpired());
    }

}