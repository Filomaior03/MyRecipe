package it.uniroma3.MyRecipe.repository;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.MyRecipe.model.Recipe;

//classe per le operazioni della persistenza (CRUD: CREATE, READ, UPDATE, DELETE)
public interface RecipeRepository extends CrudRepository<Recipe, Long>{

}