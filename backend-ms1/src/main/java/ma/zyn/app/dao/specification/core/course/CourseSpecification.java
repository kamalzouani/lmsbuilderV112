package  ma.zyn.app.dao.specification.core.course;

import ma.zyn.app.dao.criteria.core.course.CourseCriteria;
import ma.zyn.app.bean.core.course.Course;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class CourseSpecification extends  AbstractSpecification<CourseCriteria, Course>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("name", criteria.getName(),criteria.getNameLike());
        addPredicate("startDate", criteria.getStartDate(), criteria.getStartDateFrom(), criteria.getStartDateTo());
        addPredicate("endDate", criteria.getEndDate(), criteria.getEndDateFrom(), criteria.getEndDateTo());
        addPredicateBigDecimal("duration", criteria.getDuration(), criteria.getDurationMin(), criteria.getDurationMax());
        addPredicate("level", criteria.getLevel(),criteria.getLevelLike());
        addPredicateBigDecimal("price", criteria.getPrice(), criteria.getPriceMin(), criteria.getPriceMax());
        addPredicateFk("instructor","id", criteria.getInstructor()==null?null:criteria.getInstructor().getId());
        addPredicateFk("instructor","id", criteria.getInstructors());
        addPredicateFk("instructor","email", criteria.getInstructor()==null?null:criteria.getInstructor().getEmail());
        addPredicateFk("category","id", criteria.getCategory()==null?null:criteria.getCategory().getId());
        addPredicateFk("category","id", criteria.getCategorys());
    }

    public CourseSpecification(CourseCriteria criteria) {
        super(criteria);
    }

    public CourseSpecification(CourseCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
