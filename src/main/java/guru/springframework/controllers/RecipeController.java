package guru.springframework.controllers;

import guru.springframework.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping({"/recipe"})
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/show/{id}"})
    public String getRecipeDetail(@PathVariable String id, Model model){

        model.addAttribute("recipe", recipeService.getRecipeById(Long.valueOf(id)));

        return "recipeDetail";
    }


}
