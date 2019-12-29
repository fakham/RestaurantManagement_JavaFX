package models.boissons.hot;

public class The extends HotBoisson{

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public double cost() {
        return 20.5;
    }

    @Override
    public String getDescription() {
        return "The";
    }

    @Override
    public void brew() {
        System.out.println("Verser du thé");
    }

    @Override
    public void addCondiment() {
        System.out.println("Ajouter citron");
    }

    @Override
    public HotBoisson clone() {
        return new The() ;
    }

}
