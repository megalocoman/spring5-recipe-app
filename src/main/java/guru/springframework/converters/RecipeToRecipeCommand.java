package guru.springframework.converters;

import guru.springframework.command.RecipeCommand;
import guru.springframework.domain.Recipe;
import org.springframework.core.convert.converter.Converter;

public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    @Override
    public RecipeCommand convert(Recipe source) {

        if(source != null){
            RecipeCommand recipeCommand = new RecipeCommand();
            recipeCommand.setId(source.getId());
            recipeCommand.setDescription(source.getDescription());
            recipeCommand.setPrepTime(source.getPrepTime());
        }
        return null;
    }
}
