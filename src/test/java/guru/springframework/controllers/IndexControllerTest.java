package guru.springframework.controllers;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import guru.springframework.services.RecipeService;
import guru.springframework.domain.Recipe;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;    
    
public class IndexControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    IndexController controller;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        controller = new  IndexController(recipeService);

    }
        
    @Test
    public void getIndexPage() {

        //Recipes
        Set<Recipe> recipes = new HashSet<Recipe>();
        
        Recipe r1 = new Recipe();
        Recipe r2 = new Recipe();
        r1.setId(1L);
        r2.setId(2L);
        recipes.add(r1);
        recipes.add(r2);
        

        //when
        when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        String viewName = controller.getIndexPage(model);

        //then
        assertEquals("index", viewName);
        verify(recipeService,times(1)).getRecipes();
        verify(model,times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }
}
    