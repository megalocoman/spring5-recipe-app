package guru.springframework.converters;

import guru.springframework.command.RecipeCommand;
import guru.springframework.domain.Recipe;
import org.springframework.core.convert.converter.Converter;

public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final NotesCommandToNotes notesCommandToNotes;
    private final CategoryCommandToCategory categoryCoverter;
    private final IngredientCommandToIngredient categoryConverter;

    public RecipeCommandToRecipe(NotesCommandToNotes notesCommandToNotes, CategoryCommandToCategory categoryCoverter, IngredientCommandToIngredient categoryConverter) {

        this.notesCommandToNotes = notesCommandToNotes;
        this.categoryCoverter = categoryCoverter;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public Recipe convert(RecipeCommand source) {

        if(source != null){
            Recipe recipe= new Recipe();
            recipe.setId(source.getId());
            recipe.setDescription(source.getDescription());
            recipe.setPrepTime(source.getPrepTime());
            recipe.setCookTime(source.getCookTime());
            recipe.setServing(source.getServing());
            recipe.setSource(source.getSource());
            recipe.setUrl(source.getUrl());
            recipe.setDirection(source.getDirection());
            recipe.setNotes(notesCommandToNotes.convert(source.getNotes()));

            if(source.getCategories() != null && source.getCategories().size()>0 ){
                source.getCategories()
                        .forEach(category -> recipe.getCategories().add(categoryCoverter.convert(category)));
            }

            if(source.getIngredient() != null && source.getIngredient().size() > 0){
                source.getIngredient()
                        .forEach(ingredient -> recipe.getIngredient().add(categoryConverter.convert(ingredient)));
            }

            return recipe;
        }

        return null;
    }
}
