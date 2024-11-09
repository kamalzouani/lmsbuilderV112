package ma.zyn.app.unit.ws.facade.admin.course;

import ma.zyn.app.bean.core.course.ModuleContent;
import ma.zyn.app.service.impl.admin.course.ModuleContentAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.course.ModuleContentRestAdmin;
import ma.zyn.app.ws.converter.course.ModuleContentConverter;
import ma.zyn.app.ws.dto.course.ModuleContentDto;
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
public class ModuleContentRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private ModuleContentAdminServiceImpl service;
    @Mock
    private ModuleContentConverter converter;

    @InjectMocks
    private ModuleContentRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllModuleContentTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<ModuleContentDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<ModuleContentDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveModuleContentTest() throws Exception {
        // Mock data
        ModuleContentDto requestDto = new ModuleContentDto();
        ModuleContent entity = new ModuleContent();
        ModuleContent saved = new ModuleContent();
        ModuleContentDto savedDto = new ModuleContentDto();

        // Mock the converter to return the moduleContent object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved moduleContent DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<ModuleContentDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        ModuleContentDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved moduleContent DTO
        assertEquals(savedDto.getName(), responseBody.getName());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
        assertEquals(savedDto.getType(), responseBody.getType());
        assertEquals(savedDto.getUrl(), responseBody.getUrl());
    }

}
