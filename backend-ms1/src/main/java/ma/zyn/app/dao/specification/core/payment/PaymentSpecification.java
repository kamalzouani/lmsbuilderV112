package  ma.zyn.app.dao.specification.core.payment;

import ma.zyn.app.dao.criteria.core.payment.PaymentCriteria;
import ma.zyn.app.bean.core.payment.Payment;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class PaymentSpecification extends  AbstractSpecification<PaymentCriteria, Payment>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBigDecimal("amount", criteria.getAmount(), criteria.getAmountMin(), criteria.getAmountMax());
        addPredicate("paymentDate", criteria.getPaymentDate(), criteria.getPaymentDateFrom(), criteria.getPaymentDateTo());
        addPredicate("status", criteria.getStatus(),criteria.getStatusLike());
        addPredicateFk("student","id", criteria.getStudent()==null?null:criteria.getStudent().getId());
        addPredicateFk("student","id", criteria.getStudents());
        addPredicateFk("student","email", criteria.getStudent()==null?null:criteria.getStudent().getEmail());
        addPredicateFk("course","id", criteria.getCourse()==null?null:criteria.getCourse().getId());
        addPredicateFk("course","id", criteria.getCourses());
    }

    public PaymentSpecification(PaymentCriteria criteria) {
        super(criteria);
    }

    public PaymentSpecification(PaymentCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
