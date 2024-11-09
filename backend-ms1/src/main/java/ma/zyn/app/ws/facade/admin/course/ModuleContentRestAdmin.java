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

import ma.zyn.app.bean.core.course.ModuleContent;
import ma.zyn.app.dao.criteria.core.course.ModuleContentCriteria;
import ma.zyn.app.service.facade.admin.course.ModuleContentAdminService;
import ma.zyn.app.ws.converter.course.ModuleContentConverter;
import ma.zyn.app.ws.dto.course.ModuleContentDto;
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
@RequestMapping("/api/admin/moduleContent/")
public class ModuleContentRestAdmin {




    @Operation(summary = "Finds a list of all moduleContents")
    @GetMapping("")
    public ResponseEntity<List<ModuleContentDto>> findAll() throws Exception {
        ResponseEntity<List<ModuleContentDto>> res = null;
        List<ModuleContent> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<ModuleContentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a moduleContent by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ModuleContentDto> findById(@PathVariable Long id) {
        ModuleContent t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ModuleContentDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  moduleContent")
    @PostMapping("")
    public ResponseEntity<ModuleContentDto> save(@RequestBody ModuleContentDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            ModuleContent myT = converter.toItem(dto);
            ModuleContent t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ModuleContentDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  moduleContent")
    @PutMapping("")
    public ResponseEntity<ModuleContentDto> update(@RequestBody ModuleContentDto dto) throws Exception {
        ResponseEntity<ModuleContentDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ModuleContent t = service.findById(dto.getId());
            converter.copy(dto,t);
            ModuleContent updated = service.update(t);
            ModuleContentDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of moduleContent")
    @PostMapping("multiple")
    public ResponseEntity<List<ModuleContentDto>> delete(@RequestBody List<ModuleContentDto> dtos) throws Exception {
        ResponseEntity<List<ModuleContentDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<ModuleContent> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified moduleContent")
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

    @Operation(summary = "find by module id")
    @GetMapping("module/id/{id}")
    public List<ModuleContentDto> findByModuleId(@PathVariable Long id){
        return findDtos(service.findByModuleId(id));
    }
    @Operation(summary = "delete by module id")
    @DeleteMapping("module/id/{id}")
    public int deleteByModuleId(@PathVariable Long id){
        return service.deleteByModuleId(id);
    }

    @Operation(summary = "Finds a moduleContent and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ModuleContentDto> findWithAssociatedLists(@PathVariable Long id) {
        ModuleContent loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ModuleContentDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds moduleContents by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ModuleContentDto>> findByCriteria(@RequestBody ModuleContentCriteria criteria) throws Exception {
        ResponseEntity<List<ModuleContentDto>> res = null;
        List<ModuleContent> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ModuleContentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated moduleContents by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ModuleContentCriteria criteria) throws Exception {
        List<ModuleContent> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<ModuleContentDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets moduleContent data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ModuleContentCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ModuleContentDto> findDtos(List<ModuleContent> list){
        converter.initObject(true);
        List<ModuleContentDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ModuleContentDto> getDtoResponseEntity(ModuleContentDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public ModuleContentRestAdmin(ModuleContentAdminService service, ModuleContentConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ModuleContentAdminService service;
    private final ModuleContentConverter converter;





}
