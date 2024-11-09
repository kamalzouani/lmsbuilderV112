import {StudentCriteria} from '../student/StudentCriteria.model';
import {CourseCriteria} from '../course/CourseCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class PaymentCriteria extends BaseCriteria {

    public id: number;
     public amount: number;
     public amountMin: number;
     public amountMax: number;
    public paymentDate: Date;
    public paymentDateFrom: Date;
    public paymentDateTo: Date;
    public status: string;
    public statusLike: string;

}
