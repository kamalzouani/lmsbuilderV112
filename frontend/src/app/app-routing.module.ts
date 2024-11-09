import {RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';
import {AuthGuard} from 'src/app/zynerator/security/guards/auth.guard';
import {AppLayoutComponent} from 'src/app/layout/app.layout.component';

import {HomePublicComponent} from 'src/app/public/home/home-public.component';

import {LoginAdminComponent} from 'src/app/module/admin/login-admin/login-admin.component';
import {RegisterAdminComponent} from 'src/app/module/admin/register-admin/register-admin.component';
import {ChangePasswordAdminComponent} from 'src/app/module/admin/change-password-admin/change-password-admin.component';
import {LoginInstructorComponent} from 'src/app/module/instructor/login-instructor/login-instructor.component';
import {RegisterInstructorComponent} from 'src/app/module/instructor/register-instructor/register-instructor.component';
import {ChangePasswordInstructorComponent} from 'src/app/module/instructor/change-password-instructor/change-password-instructor.component';
import {LoginStudentComponent} from 'src/app/module/student/login-student/login-student.component';
import {RegisterStudentComponent} from 'src/app/module/student/register-student/register-student.component';
import {ChangePasswordStudentComponent} from 'src/app/module/student/change-password-student/change-password-student.component';

@NgModule({
    imports: [
        RouterModule.forRoot(
            [
                {path: '', component: HomePublicComponent},
                {path: 'admin/login', component: LoginAdminComponent },
                {path: 'admin/register', component: RegisterAdminComponent },
                {path: 'admin/changePassword', component: ChangePasswordAdminComponent },
                {path: 'instructor/login', component: LoginInstructorComponent },
                {path: 'instructor/register', component: RegisterInstructorComponent },
                {path: 'instructor/changePassword', component: ChangePasswordInstructorComponent },
                {path: 'student/login', component: LoginStudentComponent },
                {path: 'student/register', component: RegisterStudentComponent },
                {path: 'student/changePassword', component: ChangePasswordStudentComponent },
                {
                    path: 'app',
                    component: AppLayoutComponent,
                    children: [
                        {
                            path: 'admin',
                            loadChildren: () => import( './module/admin/admin-routing.module').then(x => x.AdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'instructor',
                            loadChildren: () => import( './module/instructor/instructor-routing.module').then(x => x.InstructorRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'student',
                            loadChildren: () => import( './module/student/student-routing.module').then(x => x.StudentRoutingModule),
                            canActivate: [AuthGuard],
                        },
                    ],
                    canActivate: [AuthGuard]
                },
            ],
                { scrollPositionRestoration: 'enabled' }
            ),
        ],
    exports: [RouterModule],
    })
export class AppRoutingModule { }
