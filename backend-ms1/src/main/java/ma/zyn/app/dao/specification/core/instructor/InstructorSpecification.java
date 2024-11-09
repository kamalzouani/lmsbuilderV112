package  ma.zyn.app.dao.specification.core.instructor;

import ma.zyn.app.dao.criteria.core.instructor.InstructorCriteria;
import ma.zyn.app.bean.core.instructor.Instructor;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class InstructorSpecification extends  AbstractSpecification<InstructorCriteria, Instructor>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("expertise", criteria.getExpertise(),criteria.getExpertiseLike());
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
    }

    public InstructorSpecification(InstructorCriteria criteria) {
        super(criteria);
    }

    public InstructorSpecification(InstructorCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
