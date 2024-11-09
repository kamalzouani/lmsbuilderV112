package  ma.zyn.app.dao.specification.core.course;

import ma.zyn.app.dao.criteria.core.course.ModuleContentCriteria;
import ma.zyn.app.bean.core.course.ModuleContent;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ModuleContentSpecification extends  AbstractSpecification<ModuleContentCriteria, ModuleContent>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("name", criteria.getName(),criteria.getNameLike());
        addPredicate("type", criteria.getType(),criteria.getTypeLike());
        addPredicate("url", criteria.getUrl(),criteria.getUrlLike());
        addPredicateFk("module","id", criteria.getModule()==null?null:criteria.getModule().getId());
        addPredicateFk("module","id", criteria.getModules());
    }

    public ModuleContentSpecification(ModuleContentCriteria criteria) {
        super(criteria);
    }

    public ModuleContentSpecification(ModuleContentCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
