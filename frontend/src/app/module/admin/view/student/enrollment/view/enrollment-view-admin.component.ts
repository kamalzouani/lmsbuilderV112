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


import {EnrollmentAdminService} from 'src/app/shared/service/admin/student/EnrollmentAdmin.service';
import {EnrollmentDto} from 'src/app/shared/model/student/Enrollment.model';
import {EnrollmentCriteria} from 'src/app/shared/criteria/student/EnrollmentCriteria.model';

import {StudentDto} from 'src/app/shared/model/student/Student.model';
import {StudentAdminService} from 'src/app/shared/service/admin/student/StudentAdmin.service';
import {CourseDto} from 'src/app/shared/model/course/Course.model';
import {CourseAdminService} from 'src/app/shared/service/admin/course/CourseAdmin.service';
@Component({
  selector: 'app-enrollment-view-admin',
  templateUrl: './enrollment-view-admin.component.html'
})
export class EnrollmentViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: EnrollmentAdminService, private studentService: StudentAdminService, private courseService: CourseAdminService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
    }


    get course(): CourseDto {
        return this.courseService.item;
    }
    set course(value: CourseDto) {
        this.courseService.item = value;
    }
    get courses(): Array<CourseDto> {
        return this.courseService.items;
    }
    set courses(value: Array<CourseDto>) {
        this.courseService.items = value;
    }
    get student(): StudentDto {
        return this.studentService.item;
    }
    set student(value: StudentDto) {
        this.studentService.item = value;
    }
    get students(): Array<StudentDto> {
        return this.studentService.items;
    }
    set students(value: Array<StudentDto>) {
        this.studentService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<EnrollmentDto> {
        return this.service.items;
    }

    set items(value: Array<EnrollmentDto>) {
        this.service.items = value;
    }

    get item(): EnrollmentDto {
        return this.service.item;
    }

    set item(value: EnrollmentDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): EnrollmentCriteria {
        return this.service.criteria;
    }

    set criteria(value: EnrollmentCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
