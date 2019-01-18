package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryIT {

    static final String DESCRIPTION = "TEST";

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    private Category category;

    @Before
    public void setUp() throws Exception {
        this.category = new Category();
        this.category.setDescription(DESCRIPTION);

    }

    @Test
    public void saveDataOnMongoDB() throws Exception {
        Category saved = this.categoryReactiveRepository.save(category).block();
        Assert.assertNotNull(saved.getId());
    }


    @Test
    public void foundObjectSaved() throws Exception {
        Category saved = this.categoryReactiveRepository.save(category).block();

        Category found = this.categoryReactiveRepository.findByDescription(DESCRIPTION).block();

        Assert.assertEquals(saved.getId(), found.getId());
    }
}
