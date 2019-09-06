package GuestBook.DAO.servlet;

import GuestBook.DAO.GuestBookDao;
import GuestBook.DAO.entity.GuestBook;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/test")
public class GuestBookServlet extends HttpServlet {

    GuestBookDao guestBookDao;

    @Override
    public void init(ServletConfig config) throws ServletException {

        String url = config.getServletContext().getInitParameter("db-url");
        String user =  config.getServletContext().getInitParameter("db-login");
        String passwd = config.getServletContext().getInitParameter("db-passwd");

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,user,passwd);
            guestBookDao = new GuestBookDao(url,user,passwd);
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        try {
            session.setAttribute("guestBookDao",guestBookDao.getGuestbookItems());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("WEB-INF/pages/chatter.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            guestBookDao.addGuestBookItem(new GuestBook().setDate(new Date()).setName(req.getParameter("name")).setMessage(req.getParameter("message")));
            resp.sendRedirect("test");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
