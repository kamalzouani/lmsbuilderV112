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




import {EnrollmentAdminService} from 'src/app/shared/service/admin/student/EnrollmentAdmin.service';
import {EnrollmentDto} from 'src/app/shared/model/student/Enrollment.model';
import {EnrollmentCriteria} from 'src/app/shared/criteria/student/EnrollmentCriteria.model';


import {StudentDto} from 'src/app/shared/model/student/Student.model';
import {StudentAdminService} from 'src/app/shared/service/admin/student/StudentAdmin.service';
import {CourseDto} from 'src/app/shared/model/course/Course.model';
import {CourseAdminService} from 'src/app/shared/service/admin/course/CourseAdmin.service';

@Component({
  selector: 'app-enrollment-edit-admin',
  templateUrl: './enrollment-edit-admin.component.html'
})
export class EnrollmentEditAdminComponent implements OnInit {

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







    constructor(private service: EnrollmentAdminService , private studentService: StudentAdminService, private courseService: CourseAdminService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.studentService.findAll().subscribe((data) => this.students = data);
        this.courseService.findAll().subscribe((data) => this.courses = data);
    }

    public prepareEdit() {
        this.item.enrollmentDate = this.service.format(this.item.enrollmentDate);
        this.item.startDate = this.service.format(this.item.startDate);
        this.item.endDate = this.service.format(this.item.endDate);
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
            this.item = new EnrollmentDto();
        } , error =>{
            console.log(error);
        });
    }

    public hideEditDialog() {
        this.editDialog = false;
        this.setValidation(true);
    }





    public setValidation(value: boolean){
    }


    public validateForm(): void{
        this.errorMessages = new Array<string>();
    }




   public async openCreateStudent(student: string) {
        const isPermistted = await this.roleService.isPermitted('Student', 'edit');
        if (isPermistted) {
             this.student = new StudentDto();
             this.createStudentDialog = true;
        }else {
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
    get createStudentDialog(): boolean {
        return this.studentService.createDialog;
    }
    set createStudentDialog(value: boolean) {
        this.studentService.createDialog= value;
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

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): EnrollmentCriteria {
        return this.service.criteria;
    }

    set criteria(value: EnrollmentCriteria) {
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
