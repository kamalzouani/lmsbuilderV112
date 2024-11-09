package ma.zyn.app.unit.dao.facade.core.course;

import ma.zyn.app.bean.core.course.ModuleContent;
import ma.zyn.app.dao.facade.core.course.ModuleContentDao;

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

import ma.zyn.app.bean.core.course.CourseModule ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ModuleContentDaoTest {

@Autowired
    private ModuleContentDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        ModuleContent entity = new ModuleContent();
        entity.setId(id);
        underTest.save(entity);
        ModuleContent loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        ModuleContent entity = new ModuleContent();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        ModuleContent loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<ModuleContent> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<ModuleContent> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        ModuleContent given = constructSample(1);
        ModuleContent saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private ModuleContent constructSample(int i) {
		ModuleContent given = new ModuleContent();
        given.setName("name-"+i);
        given.setDescription("description-"+i);
        given.setType("type-"+i);
        given.setUrl("url-"+i);
        given.setModule(new CourseModule(1L));
        return given;
    }

}
