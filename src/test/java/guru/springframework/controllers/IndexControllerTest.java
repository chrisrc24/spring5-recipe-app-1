package guru.springframework.controllers;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import guru.springframework.services.RecipeService;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
        String viewName = controller.getIndexPage(model);

        assertEquals("index", viewName);
        verify(model,times(1)).addAttribute(eq("recipes"), anySet());
    }
}
    