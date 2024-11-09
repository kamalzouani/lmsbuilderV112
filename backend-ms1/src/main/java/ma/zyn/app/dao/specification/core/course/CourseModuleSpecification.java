package  ma.zyn.app.dao.specification.core.course;

import ma.zyn.app.dao.criteria.core.course.CourseModuleCriteria;
import ma.zyn.app.bean.core.course.CourseModule;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class CourseModuleSpecification extends  AbstractSpecification<CourseModuleCriteria, CourseModule>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("name", criteria.getName(),criteria.getNameLike());
        addPredicateInt("order", criteria.getOrder(), criteria.getOrderMin(), criteria.getOrderMax());
        addPredicateBigDecimal("duration", criteria.getDuration(), criteria.getDurationMin(), criteria.getDurationMax());
        addPredicateFk("course","id", criteria.getCourse()==null?null:criteria.getCourse().getId());
        addPredicateFk("course","id", criteria.getCourses());
    }

    public CourseModuleSpecification(CourseModuleCriteria criteria) {
        super(criteria);
    }

    public CourseModuleSpecification(CourseModuleCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
