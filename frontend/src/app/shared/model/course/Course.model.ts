import {CategoryDto} from './Category.model';
import {InstructorDto} from '../instructor/Instructor.model';
import {CourseModuleDto} from './CourseModule.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class CourseDto extends BaseDto{

    public name: string;

    public description: string;

   public startDate: Date;

   public endDate: Date;

    public duration: null | number;

    public level: string;

    public price: null | number;

    public instructor: InstructorDto ;
    public category: CategoryDto ;
     public courseModules: Array<CourseModuleDto>;


    constructor() {
        super();

        this.name = '';
        this.description = '';
        this.startDate = null;
        this.endDate = null;
        this.duration = null;
        this.level = '';
        this.price = null;
        this.instructor = new InstructorDto() ;
        this.category = new CategoryDto() ;
        this.courseModules = new Array<CourseModuleDto>();

        }

}
