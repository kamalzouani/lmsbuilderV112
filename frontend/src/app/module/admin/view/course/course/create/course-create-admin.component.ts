import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




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
  selector: 'app-course-create-admin',
  templateUrl: './course-create-admin.component.html'
})
export class CourseCreateAdminComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;
    protected courseModulesIndex = -1;

    private _courseModulesElement = new CourseModuleDto();



	constructor(private service: CourseAdminService , private categoryService: CategoryAdminService, private instructorService: InstructorAdminService, private courseModuleService: CourseModuleAdminService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.instructorService.findAll().subscribe((data) => this.instructors = data);
        this.categoryService.findAll().subscribe((data) => this.categorys = data);
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
                this.item = new CourseDto();
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



    validateCourseModules(){
        this.errorMessages = new Array();
    }


    public  setValidation(value: boolean){
    }

    public addCourseModules() {
        if( this.item.courseModules == null )
            this.item.courseModules = new Array<CourseModuleDto>();

       this.validateCourseModules();

       if (this.errorMessages.length === 0) {
            if (this.courseModulesIndex == -1){
                this.item.courseModules.push({... this.courseModulesElement});
            }else {
                this.item.courseModules[this.courseModulesIndex] =this.courseModulesElement;
            }
              this.courseModulesElement = new CourseModuleDto();
              this.courseModulesIndex = -1;
       }else{
           this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
       }
    }

    public deleteCourseModules(p: CourseModuleDto, index: number) {
        this.item.courseModules.splice(index, 1);
    }

    public editCourseModules(p: CourseModuleDto, index: number) {
        this.courseModulesElement = {... p};
        this.courseModulesIndex = index;
        this.activeTab = 0;
    }


    public  validateForm(): void{
        this.errorMessages = new Array<string>();
    }



    public async openCreateCategory(category: string) {
    const isPermistted = await this.roleService.isPermitted('Category', 'add');
    if(isPermistted) {
         this.category = new CategoryDto();
         this.createCategoryDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
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
    get createCategoryDialog(): boolean {
        return this.categoryService.createDialog;
    }
    set createCategoryDialog(value: boolean) {
        this.categoryService.createDialog= value;
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
    get createInstructorDialog(): boolean {
        return this.instructorService.createDialog;
    }
    set createInstructorDialog(value: boolean) {
        this.instructorService.createDialog= value;
    }





    get courseModulesElement(): CourseModuleDto {
        if( this._courseModulesElement == null )
            this._courseModulesElement = new CourseModuleDto();
        return this._courseModulesElement;
    }

    set courseModulesElement(value: CourseModuleDto) {
        this._courseModulesElement = value;
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

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): CourseCriteria {
        return this.service.criteria;
    }

    set criteria(value: CourseCriteria) {
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
