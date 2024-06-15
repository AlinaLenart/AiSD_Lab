import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        testPancake();
        testPizza();



    }

    private static void testPancake(){
        RecipeGraph pancakeGraph = new RecipeGraph(8);

        Ingredient flour = new Ingredient("flour", 100);
        Ingredient milk = new Ingredient("milk", 100);
        Ingredient egg = new Ingredient("egg", 1);
        Ingredient apple = new Ingredient("apple", 250);
        Ingredient cinnamon = new Ingredient("cinnamon", 10);

        IntermediateProduct pancakeBatter = new IntermediateProduct("pancakeBatter", 250);
        IntermediateProduct appleJam = new IntermediateProduct("appleJam", 200);

        Dish applePancake = new Dish("applePancake", 5);

        pancakeGraph.addNode(flour);
        pancakeGraph.addNode(milk);
        pancakeGraph.addNode(egg);
        pancakeGraph.addNode(apple);
        pancakeGraph.addNode(cinnamon);
        pancakeGraph.addNode(pancakeBatter);
        pancakeGraph.addNode(appleJam);
        pancakeGraph.addNode(applePancake);

        //from ==> to (proportion: ile produktu na ile skladnika)
        pancakeGraph.addEdge(pancakeBatter, flour);
        pancakeGraph.addEdge(pancakeBatter, milk);
        pancakeGraph.addEdge(pancakeBatter, egg);
        pancakeGraph.addEdge(appleJam, apple);
        pancakeGraph.addEdge(appleJam, cinnamon);
        pancakeGraph.addEdge(applePancake, pancakeBatter);
        pancakeGraph.addEdge(applePancake, appleJam);

        pancakeGraph.setLeftovers(pancakeBatter, 100);
        pancakeGraph.setLeftovers(milk, 50);
        pancakeGraph.setLeftovers(apple, 200);

        Ingredient preparedDish = new Ingredient("applePancake", 15.0);
        getResults(preparedDish, pancakeGraph);
    }
    private static void testPizza(){
        RecipeGraph pizzaGraph = new RecipeGraph(14);

        Ingredient flour = new Ingredient("flour", 250);
        Ingredient water = new Ingredient("water", 150);
        Ingredient yeast = new Ingredient("yeast", 25);
        Ingredient salt = new Ingredient("salt", 5);
        Ingredient passata = new Ingredient("passata", 200);
        Ingredient oregano = new Ingredient("oregano", 10);
        Ingredient pineapple = new Ingredient("pineapple", 50);
        Ingredient ham = new Ingredient("ham", 50);
        Ingredient mozzarella = new Ingredient("mozzarella", 300);

        IntermediateProduct pizzaDough = new IntermediateProduct("pizzaDough", 400);
        IntermediateProduct tomatoSauce = new IntermediateProduct("tomatoSauce", 200);
        IntermediateProduct toppings = new IntermediateProduct("toppings", 400);

        Dish pizza = new Dish("pizza", 1);

        pizzaGraph.addNode(flour);
        pizzaGraph.addNode(water);
        pizzaGraph.addNode(yeast);
        pizzaGraph.addNode(salt);
        pizzaGraph.addNode(passata);
        pizzaGraph.addNode(oregano);
        pizzaGraph.addNode(pineapple);
        pizzaGraph.addNode(ham);
        pizzaGraph.addNode(mozzarella);
        pizzaGraph.addNode(pizzaDough);
        pizzaGraph.addNode(tomatoSauce);
        pizzaGraph.addNode(toppings);
        pizzaGraph.addNode(pizza);

        //from ==> to (proportion: ile produktu / ile skladnika)
        pizzaGraph.addEdge(tomatoSauce, passata);
        pizzaGraph.addEdge(tomatoSauce, oregano);
        pizzaGraph.addEdge(pizzaDough, flour);
        pizzaGraph.addEdge(pizzaDough, water);
        pizzaGraph.addEdge(pizzaDough, yeast);
        pizzaGraph.addEdge(pizzaDough, salt);
        pizzaGraph.addEdge(toppings, ham);
        pizzaGraph.addEdge(toppings, mozzarella);
        pizzaGraph.addEdge(toppings, pineapple);
        pizzaGraph.addEdge(pizza, toppings);
        pizzaGraph.addEdge(pizza, pizzaDough);
        pizzaGraph.addEdge(pizza, tomatoSauce);

        pizzaGraph.setLeftovers(pizzaDough, 250);
        pizzaGraph.setLeftovers(yeast, 50);
        pizzaGraph.setLeftovers(pineapple, 200);
        pizzaGraph.setLeftovers(tomatoSauce, 200);

        Ingredient preparedDish = new Ingredient("pizza", 20.0);
        getResults(preparedDish, pizzaGraph);
    }
    private static void getResults(Ingredient preparedDish, RecipeGraph graph){
        System.out.println("Macierz sąsiedztwa dla grafu:");
        graph.printAdjacencyMatrix();

        ArrayList<Ingredient> usedIngredients = graph.calculateUsedIngredients(preparedDish);
        System.out.println("- Kupiono: ");
        for (Ingredient ingredient : usedIngredients) {
            System.out.print(ingredient.getName() + ": " + ingredient.getAmount());
            if (ingredient.getLeftover() > 0) {
                System.out.print(" (w tym: " + ingredient.getLeftover() + " pozostalosci)");
            }
            System.out.println();
        }


    }
}


