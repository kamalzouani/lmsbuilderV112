import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { LayoutService } from './service/app.layout.service';
import {RoleService} from "../zynerator/security/shared/service/Role.service";
import {AppComponent} from "../app.component";
import {AuthService} from "../zynerator/security/shared/service/Auth.service";
import {Router} from "@angular/router";
import {AppLayoutComponent} from "./app.layout.component";

@Component({
  selector: 'app-menu',
  templateUrl: './app.menu.component.html'
})
export class AppMenuComponent implements OnInit {
  model: any[];
  modelanonymous: any[];
    modelAdmin: any[];
  modelInstructor: any[];
  modelStudent: any[];
constructor(public layoutService: LayoutService, public app: AppComponent, public appMain: AppLayoutComponent, private roleService: RoleService, private authService: AuthService, private router: Router) { }
  ngOnInit() {
    this.modelAdmin =
      [

				{
                    label: 'Pages',
                    icon: 'pi pi-fw pi-briefcase',
                    items: [
					  {
						label: 'Payment Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste payment',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/payment/payment/list']
								  },
						]
					  },
					  {
						label: 'Student Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste student',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/student/student/list']
								  },
								  {
									label: 'Liste enrollment',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/student/enrollment/list']
								  },
						]
					  },
					  {
						label: 'Course Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste category',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/course/category/list']
								  },
								  {
									label: 'Liste course module',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/course/course-module/list']
								  },
								  {
									label: 'Liste module content',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/course/module-content/list']
								  },
								  {
									label: 'Liste course',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/course/course/list']
								  },
						]
					  },
					  {
						label: 'Instructor Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste instructor',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/instructor/instructor/list']
								  },
						]
					  },

				   {
					  label: 'Security Management',
					  icon: 'pi pi-wallet',
					  items: [
						  {
							  label: 'List User',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/user/list']
						  },
						  {
							  label: 'List Model',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/model-permission/list']
						  },
						  {
							  label: 'List Action Permission',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/action-permission/list']
						  },
					  ]
				  }
			]
	    }
    ];
    this.modelInstructor =
      [

				{
                    label: 'Pages',
                    icon: 'pi pi-fw pi-briefcase',
                    items: [
					  {
						label: 'Instructor Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste instructor',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/instructor/instructor/instructor/list']
								  },
						]
					  },

				   {
					  label: 'Security Management',
					  icon: 'pi pi-wallet',
					  items: [
						  {
							  label: 'List User',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/user/list']
						  },
						  {
							  label: 'List Model',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/model-permission/list']
						  },
						  {
							  label: 'List Action Permission',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/action-permission/list']
						  },
					  ]
				  }
			]
	    }
    ];
    this.modelStudent =
      [

				{
                    label: 'Pages',
                    icon: 'pi pi-fw pi-briefcase',
                    items: [
					  {
						label: 'Student Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste student',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/student/student/student/list']
								  },
								  {
									label: 'Liste enrollment',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/student/student/enrollment/list']
								  },
						]
					  },

				   {
					  label: 'Security Management',
					  icon: 'pi pi-wallet',
					  items: [
						  {
							  label: 'List User',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/user/list']
						  },
						  {
							  label: 'List Model',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/model-permission/list']
						  },
						  {
							  label: 'List Action Permission',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/action-permission/list']
						  },
					  ]
				  }
			]
	    }
    ];

        if (this.authService.authenticated) {
            if (this.authService.authenticatedUser.roleUsers) {
              this.authService.authenticatedUser.roleUsers.forEach(role => {
                  const roleName: string = this.getRole(role.role.authority);
                  this.roleService._role.next(roleName.toUpperCase());
                  eval('this.model = this.model' + this.getRole(role.role.authority));
              })
            }
        }
  }

    getRole(text){
        const [role, ...rest] = text.split('_');
        return this.upperCaseFirstLetter(rest.join(''));
    }

    upperCaseFirstLetter(word: string) {
      if (!word) { return word; }
      return word[0].toUpperCase() + word.substr(1).toLowerCase();
    }

    onMenuClick(event) {
        this.appMain.onMenuClick(event);
    }
}
