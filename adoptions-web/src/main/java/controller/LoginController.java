package controller;

import hu.gergelyszalay.zoo.adoption.desktop.adopter.Adopter;
import hu.gergelyszalay.zoo.adoption.desktop.adopter.AdopterDAO;
import hu.gergelyszalay.zoo.adoption.desktop.adopter.AdopterDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final AdopterDAO adopterDAO = new AdopterDAOImpl();
    private Adopter user;

    public LoginController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        List<Adopter> results = this.adopterDAO.findUser(request.getParameter("email"), request.getParameter("password"));
        if (!results.isEmpty()) {
            this.user = results.get(0);
        }
        if (this.user != null){
            response.addCookie(new Cookie("email", request.getParameter("email")));
            response.sendRedirect("pages/animal-list.jsp");
        }

    }

}
