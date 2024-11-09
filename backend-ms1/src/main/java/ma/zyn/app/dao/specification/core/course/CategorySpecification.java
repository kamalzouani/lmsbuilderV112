package  ma.zyn.app.dao.specification.core.course;

import ma.zyn.app.dao.criteria.core.course.CategoryCriteria;
import ma.zyn.app.bean.core.course.Category;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class CategorySpecification extends  AbstractSpecification<CategoryCriteria, Category>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("name", criteria.getName(),criteria.getNameLike());
    }

    public CategorySpecification(CategoryCriteria criteria) {
        super(criteria);
    }

    public CategorySpecification(CategoryCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
