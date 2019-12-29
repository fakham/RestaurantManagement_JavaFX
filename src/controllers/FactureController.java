package controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.boissons.Boisson;
import models.plats.entree.PlatEnt;
import models.plats.principal.PlatPrincipal;
import util.FactureView;
import util.Item;
import util.Session;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FactureController implements Initializable {

    @FXML
    AnchorPane root;
    @FXML
    TableView tableFactures;
    @FXML
    TableColumn colItem;
    @FXML
    TableColumn colType;
    @FXML
    TableColumn colPrice;
    @FXML
    Label txtTotal;

    private ObservableList<FactureView> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colItem.setCellValueFactory(new PropertyValueFactory<FactureView, String>("item"));
        colType.setCellValueFactory(new PropertyValueFactory<FactureView, String>("type"));
        colPrice.setCellValueFactory(new PropertyValueFactory<FactureView, Double>("price"));

        double total = 0;

        List<Item> items = Session.getItems();
        for (Item i : items) {
            FactureView row = new FactureView(new SimpleStringProperty(i.getPlat().getDescription()),
                                                new SimpleStringProperty("type"),
                                                new SimpleDoubleProperty(i.getPlat().cost()));

            if (i.getPlat() instanceof PlatPrincipal)
                row.setType("Plat Principal");
            else if (i.getPlat() instanceof PlatEnt)
                row.setType("Plat Entree");
            else if (i.getPlat() instanceof Boisson)
                row.setType("Boisson");
            else
                row.setType("Dessert");

            data.add(row);
            total += row.getPrice();
        }

        tableFactures.setItems(data);

        txtTotal.setText("Total : " + total + " DH");
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
