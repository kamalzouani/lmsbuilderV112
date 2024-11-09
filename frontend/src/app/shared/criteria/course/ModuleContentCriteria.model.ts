import {CourseModuleCriteria} from './CourseModuleCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class ModuleContentCriteria extends BaseCriteria {

    public id: number;
    public name: string;
    public nameLike: string;
    public description: string;
    public descriptionLike: string;
    public type: string;
    public typeLike: string;
    public url: string;
    public urlLike: string;
  public module: CourseModuleCriteria ;
  public modules: Array<CourseModuleCriteria> ;

}
