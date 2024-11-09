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


import {StudentAdminService} from 'src/app/shared/service/admin/student/StudentAdmin.service';
import {StudentDto} from 'src/app/shared/model/student/Student.model';
import {StudentCriteria} from 'src/app/shared/criteria/student/StudentCriteria.model';

import {CategoryDto} from 'src/app/shared/model/course/Category.model';
import {CategoryAdminService} from 'src/app/shared/service/admin/course/CategoryAdmin.service';
import {InstructorDto} from 'src/app/shared/model/instructor/Instructor.model';
import {InstructorAdminService} from 'src/app/shared/service/admin/instructor/InstructorAdmin.service';
import {CourseModuleDto} from 'src/app/shared/model/course/CourseModule.model';
import {CourseModuleAdminService} from 'src/app/shared/service/admin/course/CourseModuleAdmin.service';
import {CourseDto} from 'src/app/shared/model/course/Course.model';
import {CourseAdminService} from 'src/app/shared/service/admin/course/CourseAdmin.service';
@Component({
  selector: 'app-student-view-admin',
  templateUrl: './student-view-admin.component.html'
})
export class StudentViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;


    courses = new CourseDto();
    coursess: Array<CourseDto> = [];

    constructor(private service: StudentAdminService, private categoryService: CategoryAdminService, private instructorService: InstructorAdminService, private courseService: CourseAdminService){
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

    get items(): Array<StudentDto> {
        return this.service.items;
    }

    set items(value: Array<StudentDto>) {
        this.service.items = value;
    }

    get item(): StudentDto {
        return this.service.item;
    }

    set item(value: StudentDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): StudentCriteria {
        return this.service.criteria;
    }

    set criteria(value: StudentCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
