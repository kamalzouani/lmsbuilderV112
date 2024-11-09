import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {CourseModuleAdminService} from 'src/app/shared/service/admin/course/CourseModuleAdmin.service';
import {CourseModuleDto} from 'src/app/shared/model/course/CourseModule.model';
import {CourseModuleCriteria} from 'src/app/shared/criteria/course/CourseModuleCriteria.model';
import {ModuleContentDto} from 'src/app/shared/model/course/ModuleContent.model';
import {ModuleContentAdminService} from 'src/app/shared/service/admin/course/ModuleContentAdmin.service';
import {CourseDto} from 'src/app/shared/model/course/Course.model';
import {CourseAdminService} from 'src/app/shared/service/admin/course/CourseAdmin.service';
@Component({
  selector: 'app-course-module-create-admin',
  templateUrl: './course-module-create-admin.component.html'
})
export class CourseModuleCreateAdminComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;
    protected moduleContentsIndex = -1;

    private _moduleContentsElement = new ModuleContentDto();



	constructor(private service: CourseModuleAdminService , private moduleContentService: ModuleContentAdminService, private courseService: CourseAdminService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.courseService.findAll().subscribe((data) => this.courses = data);
    }



    public save(): void {
        this.submitted = true;
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.saveWithShowOption(false);
        } else {
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs sur le formulaire'});
        }
    }

    public saveWithShowOption(showList: boolean) {
        this.service.save().subscribe(item => {
            if (item != null) {
                this.items.push({...item});
                this.createDialog = false;
                this.submitted = false;
                this.item = new CourseModuleDto();
            } else {
                this.messageService.add({severity: 'error', summary: 'Erreurs', detail: 'Element existant'});
            }

        }, error => {
            console.log(error);
        });
    }


    public hideCreateDialog() {
        this.createDialog = false;
        this.setValidation(true);
    }



    validateModuleContents(){
        this.errorMessages = new Array();
    }


    public  setValidation(value: boolean){
    }

    public addModuleContents() {
        if( this.item.moduleContents == null )
            this.item.moduleContents = new Array<ModuleContentDto>();

       this.validateModuleContents();

       if (this.errorMessages.length === 0) {
            if (this.moduleContentsIndex == -1){
                this.item.moduleContents.push({... this.moduleContentsElement});
            }else {
                this.item.moduleContents[this.moduleContentsIndex] =this.moduleContentsElement;
            }
              this.moduleContentsElement = new ModuleContentDto();
              this.moduleContentsIndex = -1;
       }else{
           this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
       }
    }

    public deleteModuleContents(p: ModuleContentDto, index: number) {
        this.item.moduleContents.splice(index, 1);
    }

    public editModuleContents(p: ModuleContentDto, index: number) {
        this.moduleContentsElement = {... p};
        this.moduleContentsIndex = index;
        this.activeTab = 0;
    }


    public  validateForm(): void{
        this.errorMessages = new Array<string>();
    }



    public async openCreateCourse(course: string) {
    const isPermistted = await this.roleService.isPermitted('Course', 'add');
    if(isPermistted) {
         this.course = new CourseDto();
         this.createCourseDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
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
    get createCourseDialog(): boolean {
        return this.courseService.createDialog;
    }
    set createCourseDialog(value: boolean) {
        this.courseService.createDialog= value;
    }





    get moduleContentsElement(): ModuleContentDto {
        if( this._moduleContentsElement == null )
            this._moduleContentsElement = new ModuleContentDto();
        return this._moduleContentsElement;
    }

    set moduleContentsElement(value: ModuleContentDto) {
        this._moduleContentsElement = value;
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

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): CourseModuleCriteria {
        return this.service.criteria;
    }

    set criteria(value: CourseModuleCriteria) {
        this.service.criteria = value;
    }

    get dateFormat() {
        return environment.dateFormatCreate;
    }

    get dateFormatColumn() {
        return environment.dateFormatCreate;
    }

    get submitted(): boolean {
        return this._submitted;
    }

    set submitted(value: boolean) {
        this._submitted = value;
    }

    get errorMessages(): string[] {
        if (this._errorMessages == null) {
            this._errorMessages = new Array<string>();
        }
        return this._errorMessages;
    }

    set errorMessages(value: string[]) {
        this._errorMessages = value;
    }

    get validate(): boolean {
        return this.service.validate;
    }

    set validate(value: boolean) {
        this.service.validate = value;
    }


    get activeTab(): number {
        return this._activeTab;
    }

    set activeTab(value: number) {
        this._activeTab = value;
    }

}
