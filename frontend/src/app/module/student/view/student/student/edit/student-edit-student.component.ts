import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';
import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';

import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {StudentStudentService} from 'src/app/shared/service/student/student/StudentStudent.service';
import {StudentDto} from 'src/app/shared/model/student/Student.model';
import {StudentCriteria} from 'src/app/shared/criteria/student/StudentCriteria.model';


import {CategoryDto} from 'src/app/shared/model/course/Category.model';
import {CategoryStudentService} from 'src/app/shared/service/student/course/CategoryStudent.service';
import {InstructorDto} from 'src/app/shared/model/instructor/Instructor.model';
import {InstructorStudentService} from 'src/app/shared/service/student/instructor/InstructorStudent.service';
import {CourseModuleDto} from 'src/app/shared/model/course/CourseModule.model';
import {CourseModuleStudentService} from 'src/app/shared/service/student/course/CourseModuleStudent.service';
import {CourseDto} from 'src/app/shared/model/course/Course.model';
import {CourseStudentService} from 'src/app/shared/service/student/course/CourseStudent.service';

@Component({
  selector: 'app-student-edit-student',
  templateUrl: './student-edit-student.component.html'
})
export class StudentEditStudentComponent implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();


    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;
    private _file: any;
    private _files: any;

    protected coursesIndex = -1;

    private _coursesElement = new CourseDto();





    constructor(private service: StudentStudentService , private categoryService: CategoryStudentService, private instructorService: InstructorStudentService, private courseService: CourseStudentService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.coursesElement.instructor = new InstructorDto();
        this.instructorService.findAll().subscribe((data) => this.instructors = data);
        this.coursesElement.category = new CategoryDto();
        this.categoryService.findAll().subscribe((data) => this.categorys = data);

    }

    public prepareEdit() {
    }



 public edit(): void {
        this.submitted = true;
        this.prepareEdit();
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.editWithShowOption(false);
        } else {
            this.messageService.add({
                severity: 'error',
                summary: 'Erreurs',
                detail: 'Merci de corrigé les erreurs sur le formulaire'
            });
        }
    }

    public editWithShowOption(showList: boolean) {
        this.service.edit().subscribe(religion=>{
            const myIndex = this.items.findIndex(e => e.id === this.item.id);
            this.items[myIndex] = religion;
            this.editDialog = false;
            this.submitted = false;
            this.item = new StudentDto();
        } , error =>{
            console.log(error);
        });
    }

    public hideEditDialog() {
        this.editDialog = false;
        this.setValidation(true);
    }





    public validateCourses(){
        this.errorMessages = new Array();
    }

    public setValidation(value: boolean){
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

    public validateForm(): void{
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

    get coursesElement(): CourseDto {
        if( this._coursesElement == null )
            this._coursesElement = new CourseDto();
         return this._coursesElement;
    }

    set coursesElement(value: CourseDto) {
        this._coursesElement = value;
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

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): StudentCriteria {
        return this.service.criteria;
    }

    set criteria(value: StudentCriteria) {
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
