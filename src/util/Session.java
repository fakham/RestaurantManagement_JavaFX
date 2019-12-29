package util;

import models.users.User;

import java.util.ArrayList;
import java.util.List;

public class Session {

    private static User connectedClient;
    private static List<Item> items = new ArrayList<>();

    public static List<Item> getItems() {
        return items;
    }

    public static void setItems(List<Item> items) {
        Session.items = items;
    }

    public static User getConnectedClient() {
        return connectedClient;
    }

    public static void setConnectedClient(User connectedClient) {
        Session.connectedClient = connectedClient;
    }

}
