import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {EnrollmentStudentService} from 'src/app/shared/service/student/student/EnrollmentStudent.service';
import {EnrollmentDto} from 'src/app/shared/model/student/Enrollment.model';
import {EnrollmentCriteria} from 'src/app/shared/criteria/student/EnrollmentCriteria.model';
import {StudentDto} from 'src/app/shared/model/student/Student.model';
import {StudentStudentService} from 'src/app/shared/service/student/student/StudentStudent.service';
import {CourseDto} from 'src/app/shared/model/course/Course.model';
import {CourseStudentService} from 'src/app/shared/service/student/course/CourseStudent.service';
@Component({
  selector: 'app-enrollment-create-student',
  templateUrl: './enrollment-create-student.component.html'
})
export class EnrollmentCreateStudentComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;




	constructor(private service: EnrollmentStudentService , private studentService: StudentStudentService, private courseService: CourseStudentService, @Inject(PLATFORM_ID) private platformId? ) {
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
                this.item = new EnrollmentDto();
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





    public  setValidation(value: boolean){
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
    }



    public async openCreateStudent(student: string) {
    const isPermistted = await this.roleService.isPermitted('Student', 'add');
    if(isPermistted) {
         this.student = new StudentDto();
         this.createStudentDialog = true;
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

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
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
