package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import models.plats.Plat;
import models.plats.principal.FrenchFactory;
import models.plats.principal.ItalianFactory;
import models.plats.principal.PizzaType;
import services.items.PlatPrincipalService;
import util.Item;
import util.Session;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlatPrincipalController implements Initializable {

    @FXML
    AnchorPane root;
    @FXML
    Button btnCheese;
    @FXML
    Button btnFrutti;
    @FXML
    Button btnFruit;
    @FXML
    Button btnFormaggio;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void logOutAction(ActionEvent actionEvent) {
        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().setAll(pane);
    }

    public void platPrincipalAction(ActionEvent actionEvent) {
        Item item = new Item();
        item.setQuantity(1);
        if (actionEvent.getSource().equals(btnCheese)) {
            item.setPlat(FrenchFactory.getInstance().createPizza(PizzaType.CHEESE_PIZZA));
        } else if (actionEvent.getSource().equals(btnFruit)) {
            item.setPlat(FrenchFactory.getInstance().createPizza(PizzaType.PIZZA_FRUIT_DE_MER));
        } else if (actionEvent.getSource().equals(btnFrutti)) {
            item.setPlat(ItalianFactory.getInstance().createPizza(PizzaType.FRUTTI_DI_MARE));
        } else if (actionEvent.getSource().equals(btnFormaggio)) {
            item.setPlat(ItalianFactory.getInstance().createPizza(PizzaType.FORMAGGIO_PIZZA));
        }

        Plat plat = PlatPrincipalService.getInstance().filter("description = '" + item.getPlat().getDescription() + "'").get(0);
        item.setPlat(plat);

        Session.getItems().add(item);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "The item has been added to your command", ButtonType.OK);
        alert.setTitle("The item has been added to your command!");
        alert.setHeaderText("The item has been added to your command!");
        alert.show();

        menuAction(actionEvent);
    }


    public void menuAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/Menu.fxml"));
            root.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
