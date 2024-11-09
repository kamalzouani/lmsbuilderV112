import {CategoryCriteria} from './CategoryCriteria.model';
import {InstructorCriteria} from '../instructor/InstructorCriteria.model';
import {CourseModuleCriteria} from './CourseModuleCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class CourseCriteria extends BaseCriteria {

    public id: number;
    public name: string;
    public nameLike: string;
    public description: string;
    public descriptionLike: string;
    public startDate: Date;
    public startDateFrom: Date;
    public startDateTo: Date;
    public endDate: Date;
    public endDateFrom: Date;
    public endDateTo: Date;
     public duration: number;
     public durationMin: number;
     public durationMax: number;
    public level: string;
    public levelLike: string;
     public price: number;
     public priceMin: number;
     public priceMax: number;
  public instructor: InstructorCriteria ;
  public instructors: Array<InstructorCriteria> ;
  public category: CategoryCriteria ;
  public categorys: Array<CategoryCriteria> ;
      public courseModules: Array<CourseModuleCriteria>;

}
