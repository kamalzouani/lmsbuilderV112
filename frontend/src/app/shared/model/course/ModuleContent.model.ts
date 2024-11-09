import {CourseModuleDto} from './CourseModule.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class ModuleContentDto extends BaseDto{

    public name: string;

    public description: string;

    public type: string;

    public url: string;

    public module: CourseModuleDto ;


    constructor() {
        super();

        this.name = '';
        this.description = '';
        this.type = '';
        this.url = '';
        this.module = new CourseModuleDto() ;

        }

}
