package controller;

import hu.gergelyszalay.zoo.adoption.desktop.animal.Animal;
import hu.gergelyszalay.zoo.adoption.desktop.animal.AnimalDAO;
import hu.gergelyszalay.zoo.adoption.desktop.animal.AnimalDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AnimalListController")
public class AnimalListController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final AnimalDAO animalDAO = new AnimalDAOImpl();
    private List<Animal> resultsList;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("searchedTerm") != null && !req.getParameter("searchedTerm").equals("")) {
            resultsList = animalDAO.findAnimal(req.getParameter("searchedTerm"));
        } else {
            resultsList = animalDAO.findAll();
        }
        req.setAttribute("animalList", resultsList);
    }

}
