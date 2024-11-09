import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {ToastModule} from 'primeng/toast';
import {ToolbarModule} from 'primeng/toolbar';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {InputSwitchModule} from 'primeng/inputswitch';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {EditorModule} from "primeng/editor";

import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DialogModule } from 'primeng/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {CalendarModule} from 'primeng/calendar';
import {PanelModule} from 'primeng/panel';
import {InputNumberModule} from 'primeng/inputnumber';
import {BadgeModule} from 'primeng/badge';
import { MultiSelectModule } from 'primeng/multiselect';
import {TranslateModule} from '@ngx-translate/core';
import {FileUploadModule} from 'primeng/fileupload';
import {FullCalendarModule} from '@fullcalendar/angular';
import {CardModule} from "primeng/card";
import {TagModule} from "primeng/tag";

import { StudentCreateStudentComponent } from './student/create/student-create-student.component';
import { StudentEditStudentComponent } from './student/edit/student-edit-student.component';
import { StudentViewStudentComponent } from './student/view/student-view-student.component';
import { StudentListStudentComponent } from './student/list/student-list-student.component';
import { EnrollmentCreateStudentComponent } from './enrollment/create/enrollment-create-student.component';
import { EnrollmentEditStudentComponent } from './enrollment/edit/enrollment-edit-student.component';
import { EnrollmentViewStudentComponent } from './enrollment/view/enrollment-view-student.component';
import { EnrollmentListStudentComponent } from './enrollment/list/enrollment-list-student.component';

import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {TabViewModule} from 'primeng/tabview';
import { SplitButtonModule } from 'primeng/splitbutton';
import { MessageModule } from 'primeng/message';
import {MessagesModule} from 'primeng/messages';
import {PaginatorModule} from 'primeng/paginator';



@NgModule({
  declarations: [

    StudentCreateStudentComponent,
    StudentListStudentComponent,
    StudentViewStudentComponent,
    StudentEditStudentComponent,
    EnrollmentCreateStudentComponent,
    EnrollmentListStudentComponent,
    EnrollmentViewStudentComponent,
    EnrollmentEditStudentComponent,
  ],
  imports: [
    CommonModule,
    ToastModule,
    ToolbarModule,
    TableModule,
    ConfirmDialogModule,
    DialogModule,
    PasswordModule,
    InputTextModule,
    ButtonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    SplitButtonModule,
    BrowserAnimationsModule,
    DropdownModule,
    TabViewModule,
    InputSwitchModule,
    InputTextareaModule,
    CalendarModule,
    PanelModule,
    MessageModule,
    MessagesModule,
    InputNumberModule,
    BadgeModule,
    MultiSelectModule,
    PaginatorModule,
    TranslateModule,
    FileUploadModule,
    FullCalendarModule,
    CardModule,
    EditorModule,
    TagModule,


  ],
  exports: [
  StudentCreateStudentComponent,
  StudentListStudentComponent,
  StudentViewStudentComponent,
  StudentEditStudentComponent,
  EnrollmentCreateStudentComponent,
  EnrollmentListStudentComponent,
  EnrollmentViewStudentComponent,
  EnrollmentEditStudentComponent,
  ],
})
export class StudentStudentModule { }
