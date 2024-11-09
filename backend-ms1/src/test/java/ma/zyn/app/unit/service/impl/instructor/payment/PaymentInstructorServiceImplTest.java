package ma.zyn.app.unit.service.impl.admin.payment;

import ma.zyn.app.bean.core.payment.Payment;
import ma.zyn.app.dao.facade.core.payment.PaymentDao;
import ma.zyn.app.service.impl.admin.payment.PaymentAdminServiceImpl;

import ma.zyn.app.bean.core.student.Student ;
import ma.zyn.app.bean.core.course.Course ;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class PaymentInstructorServiceImplTest {

    @Mock
    private PaymentDao repository;
    private AutoCloseable autoCloseable;
    private PaymentInstructorServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new PaymentAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllPayment() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSavePayment() {
        // Given
        Payment toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeletePayment() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetPaymentById() {
        // Given
        Long idToRetrieve = 1L; // Example Payment ID to retrieve
        Payment expected = new Payment(); // You need to replace Payment with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Payment result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Payment constructSample(int i) {
		Payment given = new Payment();
        given.setAmount(BigDecimal.TEN);
        given.setPaymentDate(LocalDateTime.now());
        given.setStatus("status-"+i);
        given.setStudent(new Student(1L));
        given.setCourse(new Course(1L));
        return given;
    }

}
