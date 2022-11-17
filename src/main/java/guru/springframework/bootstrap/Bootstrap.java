package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repository.CategoryRepository;
import guru.springframework.repository.RecipeRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class Bootstrap implements CommandLineRunner {

    private UnitOfMeasureRepository unitOfMeasureRepository;
    private  RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;

    public Bootstrap(UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository,
                     CategoryRepository categoryRepository ) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
        this.categoryRepository =categoryRepository;
    }

    public void loadBootstrap(){


        UnitOfMeasure teaspoon = unitOfMeasureRepository.findByUom("Teaspoon").get();
        UnitOfMeasure tablespoon =unitOfMeasureRepository.findByUom("Tablespoon").get();
        UnitOfMeasure unit = unitOfMeasureRepository.findByUom("Unit").get();

        Category mexican = categoryRepository.findByDescription("Mexican").get();
        Category fastFood = categoryRepository.findByDescription("Fast Food").get();

//Spicy Grilled Chicken Tacos

        Recipe spicyGrilled = new Recipe();
        spicyGrilled.setDescription(" We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled " +
                "jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot" +
                " pan on the stove comes wafting through the house. ");
        spicyGrilled.setPrepTime(20);
        spicyGrilled.setCookTime(15);
        spicyGrilled.setServing(6);
        spicyGrilled.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        spicyGrilled.setSource("Spicy Grilled Chicken Tacos");
        spicyGrilled.setDifficulty(Difficulty.HARD);
        Ingredient ingUno = new Ingredient();
        ingUno.setDescription("ancho chili powder");
        ingUno.setAmount(new BigDecimal(2));
        ingUno.setUom(tablespoon);
        spicyGrilled.AddIngredient(ingUno);
        Ingredient ingDos = new Ingredient();
        ingDos.setDescription("dried oregano");
        ingDos.setAmount(new BigDecimal(1));
        ingDos.setUom(teaspoon);
        spicyGrilled.AddIngredient(ingDos);
        spicyGrilled.AddIngredient(new Ingredient("dried cumin", new BigDecimal(1), teaspoon));
        spicyGrilled.AddIngredient(new Ingredient("sugar", new BigDecimal(1), teaspoon));
        spicyGrilled.AddIngredient(new Ingredient("kosher salt", new BigDecimal("0.5"), teaspoon));
        spicyGrilled.AddIngredient(new Ingredient("clove garlic, finely chopped", new BigDecimal(1), unit));

        Notes notesSpicy = new Notes();
        notesSpicy.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it" +
                " online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin" +
                " with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");
//        notesSpicy.setRecipe(spicyGrilled);
        spicyGrilled.setNotes(notesSpicy);

        spicyGrilled.getCategories().add(mexican);
        spicyGrilled.getCategories().add(fastFood);

        recipeRepository.save(spicyGrilled);

        // Perfect guacamole

        Recipe perfectGuacamole = new Recipe();
        perfectGuacamole.setDescription("Once you have basic guacamole down, feel free to experiment with variations by " +
                "adding strawberries, peaches, pineapple, mangoes, or even watermelon. One classic Mexican guacamole has " +
                "pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). You can get creative with" +
                " your homemade guacamole! ");
        perfectGuacamole.setPrepTime(10);
        perfectGuacamole.setCookTime(10);
        perfectGuacamole.setServing(4);
        perfectGuacamole.setSource("Guacamole");
        perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        perfectGuacamole.setDifficulty(Difficulty.HARD);
        perfectGuacamole.AddIngredient(new Ingredient("ripe avocados", new BigDecimal(2), unit));
        perfectGuacamole.AddIngredient(new Ingredient("kosher salt", new BigDecimal("0.25"), teaspoon));
        perfectGuacamole.AddIngredient(new Ingredient("fresh lime or lemon juice", new BigDecimal(1), tablespoon));
        perfectGuacamole.AddIngredient(new Ingredient("minced red onion or thinly sliced green onion",
                new BigDecimal(4), tablespoon));
        perfectGuacamole.AddIngredient(new Ingredient("serrano (or jalapeño) chilis", new BigDecimal(2), unit));
        perfectGuacamole.AddIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2),tablespoon));

        Notes notesGuac = new Notes();
        notesGuac.setRecipeNotes("Be careful handling chilis! If using, it's best to wear food-safe gloves. If no " +
                "gloves are available, wash your hands thoroughly after handling, and do not touch your eyes or the " +
                "area near your eyes for several hours afterwards.");
//        notesGuac.setRecipe(perfectGuacamole);
        perfectGuacamole.setNotes(notesGuac);

        recipeRepository.save(perfectGuacamole);

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("loading bootstrap data");
        loadBootstrap();
    }
}
