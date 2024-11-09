package ma.zyn.app.unit.dao.facade.core.course;

import ma.zyn.app.bean.core.course.Course;
import ma.zyn.app.dao.facade.core.course.CourseDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;

import ma.zyn.app.bean.core.course.Category ;
import ma.zyn.app.bean.core.instructor.Instructor ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CourseDaoTest {

@Autowired
    private CourseDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        Course entity = new Course();
        entity.setId(id);
        underTest.save(entity);
        Course loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Course entity = new Course();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Course loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Course> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Course> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Course given = constructSample(1);
        Course saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Course constructSample(int i) {
		Course given = new Course();
        given.setName("name-"+i);
        given.setDescription("description-"+i);
        given.setStartDate(LocalDateTime.now());
        given.setEndDate(LocalDateTime.now());
        given.setDuration(BigDecimal.TEN);
        given.setLevel("level-"+i);
        given.setPrice(BigDecimal.TEN);
        given.setInstructor(new Instructor(1L));
        given.setCategory(new Category(1L));
        return given;
    }

}
