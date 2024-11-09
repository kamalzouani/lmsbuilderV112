import {StudentDto} from '../student/Student.model';
import {CourseDto} from '../course/Course.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class PaymentDto extends BaseDto{

    public amount: null | number;

   public paymentDate: Date;

    public status: string;

    public student: StudentDto ;
    public course: CourseDto ;


    constructor() {
        super();

        this.amount = null;
        this.paymentDate = null;
        this.status = '';

        }

}
