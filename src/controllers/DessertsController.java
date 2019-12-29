package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import models.desserts.PancakeFactory;
import models.desserts.PancakeType;
import models.desserts.pancake_decorator.Caramel;
import models.desserts.pancake_decorator.Kiwi;
import models.desserts.pancake_decorator.Nutella;
import models.desserts.pancake_decorator.Strawberry;
import models.plats.Plat;
import services.items.DessertService;
import util.Item;
import util.Session;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DessertsController implements Initializable {

    @FXML
    AnchorPane root;
    @FXML
    Button btnNuttela;
    @FXML
    Button btnCaramel;
    @FXML
    Button btnKiwi;
    @FXML
    Button btnStrawberry;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void dessertAction(ActionEvent actionEvent) {
        Item item = new Item();
        item.setQuantity(1);

        if (actionEvent.getSource().equals(btnNuttela)) {
            item.setPlat(new Nutella(PancakeFactory.getInstance().makePancake(PancakeType.CIRCULAIRE)));
        } else if (actionEvent.getSource().equals(btnCaramel)) {
            item.setPlat(new Caramel(PancakeFactory.getInstance().makePancake(PancakeType.CIRCULAIRE)));
        } else if (actionEvent.getSource().equals(btnKiwi)) {
            item.setPlat(new Kiwi(PancakeFactory.getInstance().makePancake(PancakeType.CIRCULAIRE)));
        } else if (actionEvent.getSource().equals(btnStrawberry)) {
            item.setPlat(new Strawberry(PancakeFactory.getInstance().makePancake(PancakeType.CIRCULAIRE)));
        }

        Plat plat = DessertService.getInstance().filter("description = '" + item.getPlat().getDescription() + "'").get(0);
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

    public void logOutAction(ActionEvent actionEvent) {
        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().setAll(pane);
    }
}
