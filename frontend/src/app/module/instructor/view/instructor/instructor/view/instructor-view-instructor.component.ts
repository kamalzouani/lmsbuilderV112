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


import {InstructorInstructorService} from 'src/app/shared/service/instructor/instructor/InstructorInstructor.service';
import {InstructorDto} from 'src/app/shared/model/instructor/Instructor.model';
import {InstructorCriteria} from 'src/app/shared/criteria/instructor/InstructorCriteria.model';

import {CategoryDto} from 'src/app/shared/model/course/Category.model';
import {CategoryInstructorService} from 'src/app/shared/service/instructor/course/CategoryInstructor.service';
import {CourseModuleDto} from 'src/app/shared/model/course/CourseModule.model';
import {CourseModuleInstructorService} from 'src/app/shared/service/instructor/course/CourseModuleInstructor.service';
import {CourseDto} from 'src/app/shared/model/course/Course.model';
import {CourseInstructorService} from 'src/app/shared/service/instructor/course/CourseInstructor.service';
@Component({
  selector: 'app-instructor-view-instructor',
  templateUrl: './instructor-view-instructor.component.html'
})
export class InstructorViewInstructorComponent implements OnInit {


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

    constructor(private service: InstructorInstructorService, private categoryService: CategoryInstructorService, private courseService: CourseInstructorService){
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

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<InstructorDto> {
        return this.service.items;
    }

    set items(value: Array<InstructorDto>) {
        this.service.items = value;
    }

    get item(): InstructorDto {
        return this.service.item;
    }

    set item(value: InstructorDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): InstructorCriteria {
        return this.service.criteria;
    }

    set criteria(value: InstructorCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}