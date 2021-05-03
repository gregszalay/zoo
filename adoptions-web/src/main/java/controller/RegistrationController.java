package controller;


import hu.gergelyszalay.zoo.adoption.desktop.adopter.Adopter;
import hu.gergelyszalay.zoo.adoption.desktop.adopter.AdopterDAO;
import hu.gergelyszalay.zoo.adoption.desktop.adopter.AdopterDAOImpl;
import hu.gergelyszalay.zoo.adoption.desktop.animal.Animal;
import hu.gergelyszalay.zoo.adoption.desktop.animal.AnimalDAO;
import hu.gergelyszalay.zoo.adoption.desktop.animal.AnimalDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/RegistrationController")
public class RegistrationController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final AdopterDAO adopterDAO = new AdopterDAOImpl();
    private final AnimalDAO animalDAO = new AnimalDAOImpl();
    private List<Animal> resultsList;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        Adopter adopter = new Adopter();
        try {
            adopter.setLastName(request.getParameter("lastName"));
            adopter.setFirstName(request.getParameter("firstName"));
            adopter.setEmail(request.getParameter("email"));
            adopter.setPassword(request.getParameter("password1"));
            adopter = adopterDAO.save(adopter);
            response.addCookie(new Cookie("email", request.getParameter("email")));
            response.sendRedirect("pages/animal-list.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
