import {CourseDto} from '../course/Course.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class StudentDto extends BaseDto{

    public firstName: string;

    public lastName: string;

    public phone: string;

   public credentialsNonExpired: null | boolean;

   public enabled: null | boolean;

    public email: string;

    public password: string;

   public accountNonLocked: null | boolean;

   public passwordChanged: null | boolean;

    public username: string;

   public accountNonExpired: null | boolean;

     public courses: Array<CourseDto>;


    constructor() {
        super();

        this.firstName = '';
        this.lastName = '';
        this.phone = '';
        this.credentialsNonExpired = null;
        this.enabled = null;
        this.email = '';
        this.password = '';
        this.accountNonLocked = null;
        this.passwordChanged = null;
        this.username = '';
        this.accountNonExpired = null;
        this.courses = new Array<CourseDto>();

        }

}
