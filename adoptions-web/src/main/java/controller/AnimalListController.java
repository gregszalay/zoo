package controller;

import hu.gergelyszalay.zoo.adoption.desktop.adopter.Adopter;
import hu.gergelyszalay.zoo.adoption.desktop.adopter.AdopterDAO;
import hu.gergelyszalay.zoo.adoption.desktop.adopter.impl.AdopterDAOImpl;
import hu.gergelyszalay.zoo.adoption.desktop.animal.Animal;
import hu.gergelyszalay.zoo.adoption.desktop.animal.AnimalDAO;
import hu.gergelyszalay.zoo.adoption.desktop.animal.impl.AnimalDAOImpl;

import javax.servlet.RequestDispatcher;
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
//        if (request.getParameter("searchedTerm") != null && !request.getParameter("searchedTerm").equals("")){
//            resultsList = animalDAO.findAnimal(request.getParameter("searchedTerm"));
////            request.setAttribute("animalList", resultsList);
//            System.out.println(resultsList.size());
//        } else {
//            resultsList = animalDAO.findAll();
////            request.setAttribute("animalList", resultsList);
//        }
//
//        request.setAttribute("animalList", resultsList);
      // response.sendRedirect("pages/list-contact.jsp");


    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        System.out.println(req.getCharacterEncoding());
//        resp.sendRedirect("pages/list-contact.jsp");
        if (req.getParameter("searchedTerm") != null && !req.getParameter("searchedTerm").equals("")){
            resultsList = animalDAO.findAnimal(req.getParameter("searchedTerm"));
//            request.setAttribute("animalList", resultsList);
            System.out.println(resultsList.size());
            System.out.println(req.getParameter("searchedTerm"));
        } else {
            resultsList = animalDAO.findAll();
//            request.setAttribute("animalList", resultsList);
        }


        req.setAttribute("animalList", resultsList);
       // resp.sendRedirect("pages/list-contact.jsp");

//        RequestDispatcher rd=request.getRequestDispatcher("/index.html");
//        rd.include(request, response);
    }

}
