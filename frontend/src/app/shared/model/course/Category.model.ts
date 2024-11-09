
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class CategoryDto extends BaseDto{

    public name: string;

    public description: string;



    constructor() {
        super();

        this.name = '';
        this.description = '';

        }

}
