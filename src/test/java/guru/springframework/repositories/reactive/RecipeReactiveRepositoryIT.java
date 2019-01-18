package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeReactiveRepositoryIT {

    static final String DESCRIPTION = "TEST";

    @Autowired
    RecipeReactiveRepository recipeReactiveRepository;

    private Recipe recipe;

    @Before
    public void setUp() throws Exception {
        this.recipe = new Recipe();
        this.recipe.setDescription(DESCRIPTION);

    }

    @Test
    public void saveDataOnMongoDB() throws Exception {
        Recipe saved = this.recipeReactiveRepository.save(recipe).block();
        Assert.assertNotNull(saved.getId());
    }


    @Test
    public void foundObjectSaved() throws Exception {
        Recipe saved = this.recipeReactiveRepository.save(recipe).block();

        Recipe found = this.recipeReactiveRepository.findById(saved.getId()).block();

        Assert.assertNotNull(found.getId());
    }
}
