import {CourseCriteria} from '../course/CourseCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class InstructorCriteria extends BaseCriteria {

    public bio: string;
    public bioLike: string;
    public expertise: string;
    public expertiseLike: string;
    public credentialsNonExpired: null | boolean;
    public enabled: null | boolean;
    public email: string;
    public emailLike: string;
    public password: string;
    public passwordLike: string;
    public accountNonLocked: null | boolean;
    public passwordChanged: null | boolean;
    public username: string;
    public usernameLike: string;
    public accountNonExpired: null | boolean;
      public courses: Array<CourseCriteria>;

}
