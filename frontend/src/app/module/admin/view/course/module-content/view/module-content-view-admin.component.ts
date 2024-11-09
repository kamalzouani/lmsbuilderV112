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


import {ModuleContentAdminService} from 'src/app/shared/service/admin/course/ModuleContentAdmin.service';
import {ModuleContentDto} from 'src/app/shared/model/course/ModuleContent.model';
import {ModuleContentCriteria} from 'src/app/shared/criteria/course/ModuleContentCriteria.model';

import {CourseModuleDto} from 'src/app/shared/model/course/CourseModule.model';
import {CourseModuleAdminService} from 'src/app/shared/service/admin/course/CourseModuleAdmin.service';
@Component({
  selector: 'app-module-content-view-admin',
  templateUrl: './module-content-view-admin.component.html'
})
export class ModuleContentViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: ModuleContentAdminService, private courseModuleService: CourseModuleAdminService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
    }


    get module(): CourseModuleDto {
        return this.courseModuleService.item;
    }
    set module(value: CourseModuleDto) {
        this.courseModuleService.item = value;
    }
    get modules(): Array<CourseModuleDto> {
        return this.courseModuleService.items;
    }
    set modules(value: Array<CourseModuleDto>) {
        this.courseModuleService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<ModuleContentDto> {
        return this.service.items;
    }

    set items(value: Array<ModuleContentDto>) {
        this.service.items = value;
    }

    get item(): ModuleContentDto {
        return this.service.item;
    }

    set item(value: ModuleContentDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): ModuleContentCriteria {
        return this.service.criteria;
    }

    set criteria(value: ModuleContentCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
