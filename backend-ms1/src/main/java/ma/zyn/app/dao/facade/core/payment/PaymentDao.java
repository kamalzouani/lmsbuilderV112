package ma.zyn.app.dao.facade.core.payment;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.payment.Payment;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PaymentDao extends AbstractRepository<Payment,Long>  {

    List<Payment> findByStudentId(Long id);
    int deleteByStudentId(Long id);
    long countByStudentEmail(String email);
    List<Payment> findByCourseId(Long id);
    int deleteByCourseId(Long id);
    long countByCourseId(Long id);


}
