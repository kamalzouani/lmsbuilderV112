package ma.zyn.app.unit.dao.facade.core.course;

import ma.zyn.app.bean.core.course.CourseModule;
import ma.zyn.app.dao.facade.core.course.CourseModuleDao;

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

import ma.zyn.app.bean.core.course.Course ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CourseModuleDaoTest {

@Autowired
    private CourseModuleDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        CourseModule entity = new CourseModule();
        entity.setId(id);
        underTest.save(entity);
        CourseModule loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        CourseModule entity = new CourseModule();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        CourseModule loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<CourseModule> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<CourseModule> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        CourseModule given = constructSample(1);
        CourseModule saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private CourseModule constructSample(int i) {
		CourseModule given = new CourseModule();
        given.setName("name-"+i);
        given.setDescription("description-"+i);
        given.setOrder(i);
        given.setDuration(BigDecimal.TEN);
        given.setCourse(new Course(1L));
        return given;
    }

}
