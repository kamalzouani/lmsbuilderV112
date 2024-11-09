
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class CategoryCriteria extends BaseCriteria {

    public id: number;
    public name: string;
    public nameLike: string;
    public description: string;
    public descriptionLike: string;

}
