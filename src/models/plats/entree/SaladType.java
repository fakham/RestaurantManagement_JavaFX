package models.plats.entree;

public enum SaladType {

    CHEF_SALAD("Chef Salad"), GREEN_SALAD("Green Salad"), PASTA_SALAD("Pasta Salad");

    private String name;

    private SaladType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
