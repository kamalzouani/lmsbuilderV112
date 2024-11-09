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


import {CourseModuleAdminService} from 'src/app/shared/service/admin/course/CourseModuleAdmin.service';
import {CourseModuleDto} from 'src/app/shared/model/course/CourseModule.model';
import {CourseModuleCriteria} from 'src/app/shared/criteria/course/CourseModuleCriteria.model';

import {ModuleContentDto} from 'src/app/shared/model/course/ModuleContent.model';
import {ModuleContentAdminService} from 'src/app/shared/service/admin/course/ModuleContentAdmin.service';
import {CourseDto} from 'src/app/shared/model/course/Course.model';
import {CourseAdminService} from 'src/app/shared/service/admin/course/CourseAdmin.service';
@Component({
  selector: 'app-course-module-view-admin',
  templateUrl: './course-module-view-admin.component.html'
})
export class CourseModuleViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;


    moduleContents = new ModuleContentDto();
    moduleContentss: Array<ModuleContentDto> = [];

    constructor(private service: CourseModuleAdminService, private moduleContentService: ModuleContentAdminService, private courseService: CourseAdminService){
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

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<CourseModuleDto> {
        return this.service.items;
    }

    set items(value: Array<CourseModuleDto>) {
        this.service.items = value;
    }

    get item(): CourseModuleDto {
        return this.service.item;
    }

    set item(value: CourseModuleDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): CourseModuleCriteria {
        return this.service.criteria;
    }

    set criteria(value: CourseModuleCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
