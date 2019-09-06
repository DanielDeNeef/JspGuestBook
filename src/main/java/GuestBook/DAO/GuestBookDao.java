package GuestBook.DAO;

import GuestBook.DAO.entity.GuestBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GuestBookDao {

    Date date;
    String name;
    String message;
    int counter=0;
    Connection connection;

    public GuestBookDao(String url, String user, String passwd) throws SQLException {
        this.connection = DriverManager.getConnection(url,user,passwd);
    }

    public List<GuestBook> getGuestbookItems() throws SQLException {
        List<GuestBook> guestBooks= new  ArrayList<>();

        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT * FROM GuestBook");
        counter=0;
        while (resultSet.next()){
            date = resultSet.getDate("Date");
            name = resultSet.getString("Name");
            message = resultSet.getString("Message");

            guestBooks.add(new GuestBook().setDate(date).setName(name).setMessage(message));

            ++counter;
        }

        System.out.println("counter: "+counter);

        return guestBooks;
    }

    public void addGuestBookItem(GuestBook item) throws SQLException {
        PreparedStatement statement= connection.prepareStatement(
                "insert into GuestBook (name,message) value (?,?)"
        );
        statement.setString(1,item.getName());
        statement.setString(2,item.getMessage());
        statement.execute();
        }



}
