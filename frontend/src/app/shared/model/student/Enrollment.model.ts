import {StudentDto} from './Student.model';
import {CourseDto} from '../course/Course.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class EnrollmentDto extends BaseDto{

   public enrollmentDate: Date;

   public startDate: Date;

   public endDate: Date;

    public status: string;

    public student: StudentDto ;
    public course: CourseDto ;


    constructor() {
        super();

        this.enrollmentDate = null;
        this.startDate = null;
        this.endDate = null;
        this.status = '';
        this.student = new StudentDto() ;
        this.course = new CourseDto() ;

        }

}
