package ru.mcs.mvc.app.dao;

import org.springframework.stereotype.Component;
import ru.mcs.mvc.app.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/simple_db";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "postgres";

    public static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Person> index() {
        List<Person> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                result.add(person);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public Person show(int id) {
        Person person = new Person();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Person WHERE id = ?");

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return person;
    }

    public void save(Person person) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Person VALUES(1, ?, ?, ?)");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(int id, Person updatedPerson) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Person SET name=?, age=?, email=? WHERE id=?");
            preparedStatement.setString(1, updatedPerson.getName());
            preparedStatement.setInt(2, updatedPerson.getAge());
            preparedStatement.setString(3, updatedPerson.getEmail());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Person WHERE id=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
