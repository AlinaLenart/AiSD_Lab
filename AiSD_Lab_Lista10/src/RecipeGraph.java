import java.util.*;

public class RecipeGraph {
    private final List<Ingredient> vertices;
    private final double[][] adjacencyMatrix;
    private final int size;

    public RecipeGraph(int size) {
        this.size = size;
        this.vertices = new ArrayList<>();
        this.adjacencyMatrix = new double[size][size];
    }

    public void addNode(Ingredient node) {
        vertices.add(node);
    }

    public void addEdge(Ingredient from, Ingredient to) {
        int fromIndex = vertices.indexOf(from);
        int toIndex = vertices.indexOf(to);
        adjacencyMatrix[fromIndex][toIndex] = to.getAmount()/from.getAmount();
    }

    public void setLeftovers(Ingredient ingredient, double amount) {
        ingredient.setLeftover(amount);
    }

    public ArrayList<Ingredient> calculateUsedIngredients(Ingredient preparedDish) {
        ArrayList<Ingredient> usedIngredients = new ArrayList<>();

        String dish = preparedDish.getName();
        double quantity = preparedDish.getAmount();
        int dishIndex = getIndexByName(dish);

        calculateUsedIngredientsRecursively(dishIndex, quantity, usedIngredients);

        return usedIngredients;
    }

    private void calculateUsedIngredientsRecursively(int vertexIndex, double quantity, ArrayList<Ingredient> usedIngredients) {

        for (int i = 0; i < size; i++) {
            double proportion = adjacencyMatrix[vertexIndex][i];
            if (proportion > 0) {
                String ingredientName = vertices.get(i).getName();
                double leftover = vertices.get(i).getLeftover();
                double amount = (proportion * quantity) + leftover;
                if (isIngredient(ingredientName)) {
                    usedIngredients.add(new Ingredient(ingredientName, amount, leftover));
                } else {
                    calculateUsedIngredientsRecursively(i, amount, usedIngredients);
                }
            }
        }

    }
    private boolean isIngredient(String name) {
        for (Ingredient ingredient : vertices) {
            if (ingredient.getName().equals(name)) {
                return ingredient instanceof Ingredient && !(ingredient instanceof IntermediateProduct);
            }
        }
        return false;
    }

    private int getIndexByName(String name) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getName().equals(name)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Node not found: " + name);
    }

    public void printAdjacencyMatrix() {
        System.out.print("\t\t\t");
        for (int i = 0; i < vertices.size(); i++) {
            System.out.print(String.format("%-15s", vertices.get(i).getName()));
        }
        System.out.println();
        for (int i = 0; i < vertices.size(); i++) {
            System.out.print(String.format("%-15s", vertices.get(i).getName()));
            for (int j = 0; j < vertices.size(); j++) {
                System.out.print(String.format("%-15s", adjacencyMatrix[i][j] == 0 ? "0" : adjacencyMatrix[i][j]));
            }
            System.out.println();
        }
    }
}
