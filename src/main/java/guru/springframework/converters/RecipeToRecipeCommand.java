package guru.springframework.converters;

import guru.springframework.command.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private  final NotesToNotesCommand notesCovert;
    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;

    public RecipeToRecipeCommand(NotesToNotesCommand notesCommand, CategoryToCategoryCommand categoryConverter, IngredientToIngredientCommand ingredientConverter) {
        this.notesCovert = notesCommand;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {

        if(source != null){
            RecipeCommand recipeCommand = new RecipeCommand();
            recipeCommand.setId(source.getId());
            recipeCommand.setDescription(source.getDescription());
            recipeCommand.setPrepTime(source.getPrepTime());
            recipeCommand.setCookTime(source.getCookTime());
            recipeCommand.setServing(source.getServing());
            recipeCommand.setSource(source.getSource());
            recipeCommand.setUrl(source.getUrl());
            recipeCommand.setDirection(source.getDirection());
            recipeCommand.setDifficulty(source.getDifficulty());
            recipeCommand.setNotes(notesCovert.convert(source.getNotes()));

            if(source.getCategories() != null && source.getCategories().size() > 0  ){
                source.getCategories()
                        .forEach(category -> recipeCommand.getCategories().add(categoryConverter.convert(category)));
            }

            if(source.getIngredient() != null && source.getIngredient().size() > 0){
                source.getIngredient()
                        .forEach(ingredient -> recipeCommand.getIngredient().add(ingredientConverter.convert(ingredient)));

            }

            return recipeCommand;
        }
        return null;
    }
}
