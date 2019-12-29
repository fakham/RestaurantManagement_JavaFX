package models.plats.principal;

public class FrenchFactory implements PlatPrincipalFactory {

    private static FrenchFactory instance;

    private FrenchFactory() {

    }

    public static FrenchFactory getInstance() {

        if (instance == null)
            instance = new FrenchFactory();

        return instance;
    }

    @Override
    public Pizza createPizza(PizzaType type) {
        switch (type) {
            case CHEESE_PIZZA:
                return new CheesePizza();
            case PIZZA_FRUIT_DE_MER:
                return new FruitDeMerPizza();

        }

        return null;

    }

}








