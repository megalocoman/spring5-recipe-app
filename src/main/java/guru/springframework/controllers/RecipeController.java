package guru.springframework.controllers;

import guru.springframework.command.RecipeCommand;
import guru.springframework.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping({"/recipe"})
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/{id}/show"})
    public String getRecipeDetail(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.getRecipeById(Long.valueOf(id)));
        return "recipeDetail";
    }

    @RequestMapping("/new")
    public String newRecipe(Model model){

        model.addAttribute("recipe", new RecipeCommand());
        return "recipeForm";
    }

    @PostMapping
    @RequestMapping("")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand recipeCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/"+recipeCommand.getId()+"/show" ;
    }

    @RequestMapping("{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipeForm";
    }

    @RequestMapping("{id}/delete")
    public String deleteRecipe(@PathVariable String id, Model model){
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/recipeList";
    }
}
