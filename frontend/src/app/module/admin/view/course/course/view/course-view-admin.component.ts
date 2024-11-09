import {Component, OnInit} from '@angular/core';


import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {AbstractService} from 'src/app/zynerator/service/AbstractService';
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';
import {ConfirmationService, MessageService,MenuItem} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';


import {CourseAdminService} from 'src/app/shared/service/admin/course/CourseAdmin.service';
import {CourseDto} from 'src/app/shared/model/course/Course.model';
import {CourseCriteria} from 'src/app/shared/criteria/course/CourseCriteria.model';

import {CategoryDto} from 'src/app/shared/model/course/Category.model';
import {CategoryAdminService} from 'src/app/shared/service/admin/course/CategoryAdmin.service';
import {InstructorDto} from 'src/app/shared/model/instructor/Instructor.model';
import {InstructorAdminService} from 'src/app/shared/service/admin/instructor/InstructorAdmin.service';
import {ModuleContentDto} from 'src/app/shared/model/course/ModuleContent.model';
import {ModuleContentAdminService} from 'src/app/shared/service/admin/course/ModuleContentAdmin.service';
import {CourseModuleDto} from 'src/app/shared/model/course/CourseModule.model';
import {CourseModuleAdminService} from 'src/app/shared/service/admin/course/CourseModuleAdmin.service';
@Component({
  selector: 'app-course-view-admin',
  templateUrl: './course-view-admin.component.html'
})
export class CourseViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;


    courseModules = new CourseModuleDto();
    courseModuless: Array<CourseModuleDto> = [];

    constructor(private service: CourseAdminService, private categoryService: CategoryAdminService, private instructorService: InstructorAdminService, private courseModuleService: CourseModuleAdminService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
    }


    get category(): CategoryDto {
        return this.categoryService.item;
    }
    set category(value: CategoryDto) {
        this.categoryService.item = value;
    }
    get categorys(): Array<CategoryDto> {
        return this.categoryService.items;
    }
    set categorys(value: Array<CategoryDto>) {
        this.categoryService.items = value;
    }
    get instructor(): InstructorDto {
        return this.instructorService.item;
    }
    set instructor(value: InstructorDto) {
        this.instructorService.item = value;
    }
    get instructors(): Array<InstructorDto> {
        return this.instructorService.items;
    }
    set instructors(value: Array<InstructorDto>) {
        this.instructorService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<CourseDto> {
        return this.service.items;
    }

    set items(value: Array<CourseDto>) {
        this.service.items = value;
    }

    get item(): CourseDto {
        return this.service.item;
    }

    set item(value: CourseDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): CourseCriteria {
        return this.service.criteria;
    }

    set criteria(value: CourseCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
