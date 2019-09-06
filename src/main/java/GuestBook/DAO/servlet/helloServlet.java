package GuestBook.DAO.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hey")
public class helloServlet extends HttpServlet {

    String getName = "";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getName= req.getParameter("name");
        System.out.println(getName);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        try (PrintWriter out = resp.getWriter()){

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>helloWorld</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>"+getName+"</p>");
            out.println("<form method='POST' action=''>");
            out.println("name: <input type='text' name='name'>");
            out.println("message: <input type='text' name='message'>");
            out.println("<input type='submit' value='add Student'>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");

        }catch (Exception ex){

        }
    }
}
