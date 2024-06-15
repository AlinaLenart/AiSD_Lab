class Ingredient {
    private final String name;
    private final double amount;
    private double leftover;

    public Ingredient(String name, double amount) {
        this.name = name;
        this.amount = amount;
        this.leftover = 0;
    }
    public Ingredient(String name, double amount, double leftover) {
        this.name = name;
        this.amount = amount;
        this.leftover = leftover;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public double getLeftover() {
        return leftover;
    }

    public void setLeftover(double leftover) {
        this.leftover = leftover;
    }
}

class IntermediateProduct extends Ingredient {
    public IntermediateProduct(String name, double amount) {
        super(name, amount);
    }
}

class Dish extends IntermediateProduct {
    public Dish(String name, double amount) {
        super(name, amount);
    }
}
