package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.boissons.Boisson;
import models.commands.Command;
import models.commands.CommandLivrable;
import models.commands.CommandSurplace;
import models.desserts.Dessert;
import models.plats.entree.PlatEnt;
import models.plats.principal.PlatPrincipal;
import models.users.Client;
import services.commands.CommandDetailsService;
import services.commands.CommandLivrableService;
import services.commands.CommandSurplaceService;
import util.Item;
import util.Session;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    AnchorPane root;
    @FXML
    Button btnDessert;
    @FXML
    Button btnPlatEntree;
    @FXML
    Button btnPlatPrincipal;
    @FXML
    Button btnBoisson;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Item i : Session.getItems()) {
            if (i.getPlat() instanceof PlatEnt) {
                btnPlatEntree.setDisable(true);
            } else if (i.getPlat() instanceof PlatPrincipal) {
                btnPlatPrincipal.setDisable(true);
            } else if (i.getPlat() instanceof Dessert) {
                btnDessert.setDisable(true);
            } else if (i.getPlat() instanceof Boisson) {
                btnBoisson.setDisable(true);
            }
        }
    }

    public void makeReservationAction(MouseEvent mouseEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/ReserverTable.fxml"));
            root.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logOutAction(MouseEvent mouseEvent) {
        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().setAll(pane);
    }

    public void factureAction(MouseEvent mouseEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/Facture.fxml"));
            root.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void boissonAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/Boissons.fxml"));
            root.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void platPrincipalAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/PlatPrincipal.fxml"));
            root.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void platEntreeAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/PlatEntree.fxml"));
            root.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dessertAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/Desserts.fxml"));
            root.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deliverFoodAction(MouseEvent mouseEvent) {
        TextInputDialog dialog = new TextInputDialog("walter");
        dialog.setTitle("Deliver Food");
        dialog.setHeaderText("We need your Address");
        dialog.setContentText("Please enter your home address:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){

            Command command = new CommandLivrable(new Date(), result.get());
            command.setClient((Client) Session.getConnectedClient());

            command = CommandLivrableService.getInstance().create((CommandLivrable) command);

            CommandDetailsService.getInstance().create(command, Session.getItems());

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deliver food", ButtonType.OK);
            alert.setTitle("Everything is done, thanks!");
            alert.setHeaderText("Your command will be send to you ASAP!");
            alert.show();

            Session.setItems(new ArrayList<>());
            logOutAction(mouseEvent);
        }
    }

    public void validateAction(MouseEvent mouseEvent) {

        Command command = new CommandSurplace(new Date());
        command.setClient((Client) Session.getConnectedClient());

        command = CommandSurplaceService.getInstance().create((CommandSurplace) command);

        CommandDetailsService.getInstance().create(command, Session.getItems());

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Order confirmation", ButtonType.OK);
        alert.setTitle("Everything is done, thanks!");
        alert.setHeaderText("We're waiting for you, come & enjoy!");
        alert.show();

        Session.setItems(new ArrayList<>());
        logOutAction(mouseEvent);
    }
}
