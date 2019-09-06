package GuestBook.DAO.entity;

import java.util.Date;


public class GuestBook {

    private Date date;
    private String name;
    private String message;

    public GuestBook() {
    }

    public Date getDate() {
        return date;
    }

    public GuestBook setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getName() {
        return name;
    }

    public GuestBook setName(String name) {
        this.name = name;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public GuestBook setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "<p>date= " + date +", name= " + name + ", message= " + message + " </p>";
    }
}
