package services.commands;

import models.commands.CommandSurplace;
import services.Service;
import services.reservations.TableService;
import services.users.ClientService;
import util.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandSurplaceService implements Service<CommandSurplace> {

    private DatabaseConnection connection;
    private static CommandSurplaceService instance;

    private CommandSurplaceService() {
        this.connection = DatabaseConnection.getInstance();
    }

    public static CommandSurplaceService getInstance() {
        if (instance == null)
            instance = new CommandSurplaceService();

        return instance;
    }

    @Override
    public CommandSurplace create(CommandSurplace object) {

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String query = String.format("INSERT INTO commands(id_client, date, livrable) VALUES(%d, '%s', %d)",
                object.getClient().getId(), sdf.format(object.getDate()), 0);

        connection.nonSelectQuery(query);

        query = "SELECT * FROM commands ORDER BY id DESC";

        ResultSet resultSet = connection.selectQuery(query);

        try {
            if (resultSet.next())
                object.setId(resultSet.getInt("id"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return object;
    }

    @Override
    public void update(CommandSurplace object) {
        String query = String.format("UPDATE commands SET id_client = %d, date = '%s' WHERE id = %d",
                object.getClient().getId(), object.getDate().toString(), object.getId());

        connection.nonSelectQuery(query);
    }

    @Override
    public void delete(int id) {
        String query = String.format("DELETE FROM commands WHERE id = %d", id);

        connection.nonSelectQuery(query);
    }

    @Override
    public CommandSurplace selectOne(int id) {
        String query = String.format("SELECT * FROM commands WHERE id = %d", id);

        ResultSet resultSet = connection.selectQuery(query);

        try {
            if (resultSet.next()) {

                CommandSurplace commandSurplace = new CommandSurplace(resultSet.getDate("date"));

                commandSurplace.setId(resultSet.getInt("id"));
                commandSurplace.setClient(ClientService.getInstance().selectOne(resultSet.getInt("id_client")));
                commandSurplace.setTable(TableService.getInstance().selectOne(resultSet.getInt("id_table")));

                return commandSurplace;

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<CommandSurplace> selectAll() {
        return filter("livrable = 0");
    }

    @Override
    public List<CommandSurplace> filter(String whereQuery) {
        List<CommandSurplace> commandSurplaces = new ArrayList<>();

        String query = "SELECT * FROM commands WHERE " + whereQuery;

        ResultSet resultSet = connection.selectQuery(query);

        try {
            while (resultSet.next()) {

                CommandSurplace commandSurplace = new CommandSurplace(resultSet.getDate("date"));

                commandSurplace.setId(resultSet.getInt("id"));
                commandSurplace.setClient(ClientService.getInstance().selectOne(resultSet.getInt("id_client")));
                commandSurplace.setTable(TableService.getInstance().selectOne(resultSet.getInt("id_table")));

                commandSurplaces.add(commandSurplace);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return commandSurplaces;
    }

}
