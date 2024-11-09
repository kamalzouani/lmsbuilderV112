import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {InstructorAdminService} from 'src/app/shared/service/admin/instructor/InstructorAdmin.service';
import {InstructorDto} from 'src/app/shared/model/instructor/Instructor.model';
import {InstructorCriteria} from 'src/app/shared/criteria/instructor/InstructorCriteria.model';
import {CategoryDto} from 'src/app/shared/model/course/Category.model';
import {CategoryAdminService} from 'src/app/shared/service/admin/course/CategoryAdmin.service';
import {CourseModuleDto} from 'src/app/shared/model/course/CourseModule.model';
import {CourseModuleAdminService} from 'src/app/shared/service/admin/course/CourseModuleAdmin.service';
import {CourseDto} from 'src/app/shared/model/course/Course.model';
import {CourseAdminService} from 'src/app/shared/service/admin/course/CourseAdmin.service';
@Component({
  selector: 'app-instructor-create-admin',
  templateUrl: './instructor-create-admin.component.html'
})
export class InstructorCreateAdminComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;
    protected coursesIndex = -1;

    private _coursesElement = new CourseDto();



	constructor(private service: InstructorAdminService , private categoryService: CategoryAdminService, private courseService: CourseAdminService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.coursesElement.category = new CategoryDto();
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
                this.item = new InstructorDto();
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



    validateCourses(){
        this.errorMessages = new Array();
    }


    public  setValidation(value: boolean){
    }

    public addCourses() {
        if( this.item.courses == null )
            this.item.courses = new Array<CourseDto>();

       this.validateCourses();

       if (this.errorMessages.length === 0) {
            if (this.coursesIndex == -1){
                this.item.courses.push({... this.coursesElement});
            }else {
                this.item.courses[this.coursesIndex] =this.coursesElement;
            }
              this.coursesElement = new CourseDto();
              this.coursesIndex = -1;
       }else{
           this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
       }
    }

    public deleteCourses(p: CourseDto, index: number) {
        this.item.courses.splice(index, 1);
    }

    public editCourses(p: CourseDto, index: number) {
        this.coursesElement = {... p};
        this.coursesIndex = index;
        this.activeTab = 0;
    }


    public  validateForm(): void{
        this.errorMessages = new Array<string>();
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





    get coursesElement(): CourseDto {
        if( this._coursesElement == null )
            this._coursesElement = new CourseDto();
        return this._coursesElement;
    }

    set coursesElement(value: CourseDto) {
        this._coursesElement = value;
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

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): InstructorCriteria {
        return this.service.criteria;
    }

    set criteria(value: InstructorCriteria) {
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
