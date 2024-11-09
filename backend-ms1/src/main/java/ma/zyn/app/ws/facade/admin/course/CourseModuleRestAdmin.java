package  ma.zyn.app.ws.facade.admin.course;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.course.CourseModule;
import ma.zyn.app.dao.criteria.core.course.CourseModuleCriteria;
import ma.zyn.app.service.facade.admin.course.CourseModuleAdminService;
import ma.zyn.app.ws.converter.course.CourseModuleConverter;
import ma.zyn.app.ws.dto.course.CourseModuleDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/courseModule/")
public class CourseModuleRestAdmin {




    @Operation(summary = "Finds a list of all courseModules")
    @GetMapping("")
    public ResponseEntity<List<CourseModuleDto>> findAll() throws Exception {
        ResponseEntity<List<CourseModuleDto>> res = null;
        List<CourseModule> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<CourseModuleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a courseModule by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CourseModuleDto> findById(@PathVariable Long id) {
        CourseModule t = service.findById(id);
        if (t != null) {
            converter.init(true);
            CourseModuleDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  courseModule")
    @PostMapping("")
    public ResponseEntity<CourseModuleDto> save(@RequestBody CourseModuleDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            CourseModule myT = converter.toItem(dto);
            CourseModule t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CourseModuleDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  courseModule")
    @PutMapping("")
    public ResponseEntity<CourseModuleDto> update(@RequestBody CourseModuleDto dto) throws Exception {
        ResponseEntity<CourseModuleDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CourseModule t = service.findById(dto.getId());
            converter.copy(dto,t);
            CourseModule updated = service.update(t);
            CourseModuleDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of courseModule")
    @PostMapping("multiple")
    public ResponseEntity<List<CourseModuleDto>> delete(@RequestBody List<CourseModuleDto> dtos) throws Exception {
        ResponseEntity<List<CourseModuleDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<CourseModule> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified courseModule")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }


    @Operation(summary = "Finds a courseModule and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CourseModuleDto> findWithAssociatedLists(@PathVariable Long id) {
        CourseModule loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        CourseModuleDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds courseModules by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CourseModuleDto>> findByCriteria(@RequestBody CourseModuleCriteria criteria) throws Exception {
        ResponseEntity<List<CourseModuleDto>> res = null;
        List<CourseModule> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<CourseModuleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated courseModules by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CourseModuleCriteria criteria) throws Exception {
        List<CourseModule> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<CourseModuleDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets courseModule data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CourseModuleCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CourseModuleDto> findDtos(List<CourseModule> list){
        converter.initList(false);
        converter.initObject(true);
        List<CourseModuleDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CourseModuleDto> getDtoResponseEntity(CourseModuleDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public CourseModuleRestAdmin(CourseModuleAdminService service, CourseModuleConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final CourseModuleAdminService service;
    private final CourseModuleConverter converter;





}
