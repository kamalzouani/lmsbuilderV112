package ma.zyn.app.service.facade.student.payment;

import java.util.List;
import ma.zyn.app.bean.core.payment.Payment;
import ma.zyn.app.dao.criteria.core.payment.PaymentCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface PaymentStudentService {



    List<Payment> findByStudentId(Long id);
    int deleteByStudentId(Long id);
    long countByStudentEmail(String email);
    List<Payment> findByCourseId(Long id);
    int deleteByCourseId(Long id);
    long countByCourseId(Long id);




	Payment create(Payment t);

    Payment update(Payment t);

    List<Payment> update(List<Payment> ts,boolean createIfNotExist);

    Payment findById(Long id);

    Payment findOrSave(Payment t);

    Payment findByReferenceEntity(Payment t);

    Payment findWithAssociatedLists(Long id);

    List<Payment> findAllOptimized();

    List<Payment> findAll();

    List<Payment> findByCriteria(PaymentCriteria criteria);

    List<Payment> findPaginatedByCriteria(PaymentCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PaymentCriteria criteria);

    List<Payment> delete(List<Payment> ts);

    boolean deleteById(Long id);

    List<List<Payment>> getToBeSavedAndToBeDeleted(List<Payment> oldList, List<Payment> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
