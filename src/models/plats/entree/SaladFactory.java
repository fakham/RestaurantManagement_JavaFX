package models.plats.entree;

public class SaladFactory {

    private static SaladFactory instance;

    private SaladFactory() {

    }

    public static SaladFactory getInstance() {

        if (instance == null)
            instance = new SaladFactory();

        return instance;
    }

    public Salad makeSalad(SaladType SaladType) {

        switch (SaladType) {
            case CHEF_SALAD:
                return new ChefSalad();
            case GREEN_SALAD:
                return new GreenSalad();
            case PASTA_SALAD:
                return new PastaSalad();
        }

        return null;
    }

}


