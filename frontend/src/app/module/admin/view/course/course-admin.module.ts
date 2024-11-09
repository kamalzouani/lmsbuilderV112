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

import { CategoryCreateAdminComponent } from './category/create/category-create-admin.component';
import { CategoryEditAdminComponent } from './category/edit/category-edit-admin.component';
import { CategoryViewAdminComponent } from './category/view/category-view-admin.component';
import { CategoryListAdminComponent } from './category/list/category-list-admin.component';
import { CourseModuleCreateAdminComponent } from './course-module/create/course-module-create-admin.component';
import { CourseModuleEditAdminComponent } from './course-module/edit/course-module-edit-admin.component';
import { CourseModuleViewAdminComponent } from './course-module/view/course-module-view-admin.component';
import { CourseModuleListAdminComponent } from './course-module/list/course-module-list-admin.component';
import { ModuleContentCreateAdminComponent } from './module-content/create/module-content-create-admin.component';
import { ModuleContentEditAdminComponent } from './module-content/edit/module-content-edit-admin.component';
import { ModuleContentViewAdminComponent } from './module-content/view/module-content-view-admin.component';
import { ModuleContentListAdminComponent } from './module-content/list/module-content-list-admin.component';
import { CourseCreateAdminComponent } from './course/create/course-create-admin.component';
import { CourseEditAdminComponent } from './course/edit/course-edit-admin.component';
import { CourseViewAdminComponent } from './course/view/course-view-admin.component';
import { CourseListAdminComponent } from './course/list/course-list-admin.component';

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

    CategoryCreateAdminComponent,
    CategoryListAdminComponent,
    CategoryViewAdminComponent,
    CategoryEditAdminComponent,
    CourseModuleCreateAdminComponent,
    CourseModuleListAdminComponent,
    CourseModuleViewAdminComponent,
    CourseModuleEditAdminComponent,
    ModuleContentCreateAdminComponent,
    ModuleContentListAdminComponent,
    ModuleContentViewAdminComponent,
    ModuleContentEditAdminComponent,
    CourseCreateAdminComponent,
    CourseListAdminComponent,
    CourseViewAdminComponent,
    CourseEditAdminComponent,
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
  CategoryCreateAdminComponent,
  CategoryListAdminComponent,
  CategoryViewAdminComponent,
  CategoryEditAdminComponent,
  CourseModuleCreateAdminComponent,
  CourseModuleListAdminComponent,
  CourseModuleViewAdminComponent,
  CourseModuleEditAdminComponent,
  ModuleContentCreateAdminComponent,
  ModuleContentListAdminComponent,
  ModuleContentViewAdminComponent,
  ModuleContentEditAdminComponent,
  CourseCreateAdminComponent,
  CourseListAdminComponent,
  CourseViewAdminComponent,
  CourseEditAdminComponent,
  ],
})
export class CourseAdminModule { }
