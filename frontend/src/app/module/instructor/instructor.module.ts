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

import { LoginInstructorComponent } from './login-instructor/login-instructor.component';
import { RegisterInstructorComponent } from './register-instructor/register-instructor.component';
import { ChangePasswordInstructorComponent } from './change-password-instructor/change-password-instructor.component';
import { ActivationInstructorComponent } from './activation-instructor/activation-instructor.component';
import { ForgetPasswordInstructorComponent } from './forget-password-instructor/forget-password-instructor.component';


import { InstructorInstructorModule } from './view/instructor/instructor-instructor.module';
import { InstructorInstructorRoutingModule } from './view/instructor/instructor-instructor-routing.module';

import {SecurityModule} from 'src/app/module/security/security.module';
import {SecurityRoutingModule} from 'src/app/module/security/security-routing.module';


@NgModule({
  declarations: [
   LoginInstructorComponent,
   RegisterInstructorComponent,
   ChangePasswordInstructorComponent,
   ActivationInstructorComponent,
   ForgetPasswordInstructorComponent
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
  InstructorInstructorModule,
  InstructorInstructorRoutingModule,
   SecurityModule,
   SecurityRoutingModule
  ],
  exports: [
    LoginInstructorComponent,
    RegisterInstructorComponent,
    ChangePasswordInstructorComponent,
    ActivationInstructorComponent,
    ForgetPasswordInstructorComponent,

    InstructorInstructorModule,
    SecurityModule
  ],

})
export class InstructorModule { }
