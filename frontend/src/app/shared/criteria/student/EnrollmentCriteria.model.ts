import {StudentCriteria} from './StudentCriteria.model';
import {CourseCriteria} from '../course/CourseCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class EnrollmentCriteria extends BaseCriteria {

    public id: number;
    public enrollmentDate: Date;
    public enrollmentDateFrom: Date;
    public enrollmentDateTo: Date;
    public startDate: Date;
    public startDateFrom: Date;
    public startDateTo: Date;
    public endDate: Date;
    public endDateFrom: Date;
    public endDateTo: Date;
    public status: string;
    public statusLike: string;
  public student: StudentCriteria ;
  public students: Array<StudentCriteria> ;
  public course: CourseCriteria ;
  public courses: Array<CourseCriteria> ;

}
