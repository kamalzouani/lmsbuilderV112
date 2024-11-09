import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {ToastModule} from 'primeng/toast';
import {ToolbarModule} from 'primeng/toolbar';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {InputSwitchModule} from 'primeng/inputswitch';
import {InputTextareaModule} from 'primeng/inputtextarea';

import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DialogModule } from 'primeng/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {CalendarModule} from 'primeng/calendar';
import {PanelModule} from 'primeng/panel';
import {InputNumberModule} from 'primeng/inputnumber';
import {BadgeModule} from 'primeng/badge';
import { MultiSelectModule } from 'primeng/multiselect';

import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {TabViewModule} from 'primeng/tabview';
import { SplitButtonModule } from 'primeng/splitbutton';
import { MessageModule } from 'primeng/message';
import {MessagesModule} from 'primeng/messages';

import { LoginStudentComponent } from './login-student/login-student.component';
import { RegisterStudentComponent } from './register-student/register-student.component';
import { ChangePasswordStudentComponent } from './change-password-student/change-password-student.component';
import { ActivationStudentComponent } from './activation-student/activation-student.component';
import { ForgetPasswordStudentComponent } from './forget-password-student/forget-password-student.component';


import { StudentStudentModule } from './view/student/student-student.module';
import { StudentStudentRoutingModule } from './view/student/student-student-routing.module';

import {SecurityModule} from 'src/app/module/security/security.module';
import {SecurityRoutingModule} from 'src/app/module/security/security-routing.module';


@NgModule({
  declarations: [
   LoginStudentComponent,
   RegisterStudentComponent,
   ChangePasswordStudentComponent,
   ActivationStudentComponent,
   ForgetPasswordStudentComponent
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
  StudentStudentModule,
  StudentStudentRoutingModule,
   SecurityModule,
   SecurityRoutingModule
  ],
  exports: [
    LoginStudentComponent,
    RegisterStudentComponent,
    ChangePasswordStudentComponent,
    ActivationStudentComponent,
    ForgetPasswordStudentComponent,

    StudentStudentModule,
    SecurityModule
  ],

})
export class StudentModule { }
