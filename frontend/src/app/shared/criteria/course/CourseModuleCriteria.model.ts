import {ModuleContentCriteria} from './ModuleContentCriteria.model';
import {CourseCriteria} from './CourseCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class CourseModuleCriteria extends BaseCriteria {

    public id: number;
    public name: string;
    public nameLike: string;
    public description: string;
    public descriptionLike: string;
     public order: number;
     public orderMin: number;
     public orderMax: number;
     public duration: number;
     public durationMin: number;
     public durationMax: number;
      public moduleContents: Array<ModuleContentCriteria>;

}
