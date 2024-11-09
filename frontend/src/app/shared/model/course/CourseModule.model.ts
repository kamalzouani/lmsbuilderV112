import {ModuleContentDto} from './ModuleContent.model';
import {CourseDto} from './Course.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class CourseModuleDto extends BaseDto{

    public name: string;

    public description: string;

    public order: null | number;

    public duration: null | number;

    public course: CourseDto ;
     public moduleContents: Array<ModuleContentDto>;


    constructor() {
        super();

        this.name = '';
        this.description = '';
        this.order = null;
        this.duration = null;
        this.moduleContents = new Array<ModuleContentDto>();

        }

}
