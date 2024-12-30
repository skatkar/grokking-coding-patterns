package org.grokking.graph.topological;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Leetcode2115 {
    Map<String, Set<String>> ingredientsToRecipe;
    Map<String, Integer> recipeInDegrees;

    public static void main(String[] args) {
        Leetcode2115 question = new Leetcode2115();
        List<String> allRecipes = question.findAllRecipes(new String[]{"bread", "sandwich", "burger"},
                List.of(Arrays.asList("yeast", "flour"),
                        Arrays.asList("bread", "meat"),
                        Arrays.asList("sandwich", "meat", "bread")),
                new String[]{"yeast", "flour", "meat"});

        System.out.println("allRecipes = " + allRecipes);
    }

    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {

        ingredientsToRecipe = new HashMap<>();
        recipeInDegrees = new HashMap<>();

        List<String> result = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();

        buildGraph(recipes, ingredients, supplies);

        for(Map.Entry<String, Integer> entry : recipeInDegrees.entrySet()){
            if(entry.getValue() == 0){
                queue.add(entry.getKey());
                result.add(entry.getKey());
            }
        }

        while(!queue.isEmpty()){
            String resolvedDependency = queue.poll();
            if(ingredientsToRecipe.containsKey(resolvedDependency)){
                for(String recipe : ingredientsToRecipe.get(resolvedDependency)){
                    recipeInDegrees.put(recipe, recipeInDegrees.get(recipe) - 1);
                    if(recipeInDegrees.get(recipe) == 0){
                        recipeInDegrees.remove(recipe);
                        queue.add(recipe);
                        result.add(recipe);
                    }
                }
            }
        }

        return result;
    }

    // We're building a dependency graph of ingredients to recipes where the ingredient is not yet available / not in the supplies.
    // inDegree map will hold the recipe, and its dependency counts
    // If the ingredient has no dependency, then its dependency count will be 0.
    private void buildGraph(String[] recipes, List<List<String>> ingredients, String[] supplies){
        Set<String> availableSupply = Stream.of(supplies).collect(Collectors.toSet());
        for(int recipe=0; recipe < recipes.length; recipe++){
            List<String> ingredient = ingredients.get(recipe);
            int nonAvailable = 0;
            for (String ing : ingredient) {
                if (!availableSupply.contains(ing)) {
                    ingredientsToRecipe.putIfAbsent(ing, new HashSet<>());
                    ingredientsToRecipe.get(ing).add(recipes[recipe]);
                    nonAvailable++;
                }
            }
            recipeInDegrees.put(recipes[recipe], nonAvailable);
        }
    }
}
