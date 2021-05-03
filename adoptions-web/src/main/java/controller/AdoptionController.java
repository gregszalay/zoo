package controller;

import hu.gergelyszalay.zoo.adoption.desktop.adoption.Adoption;
import hu.gergelyszalay.zoo.adoption.desktop.adoption.AdoptionDAO;
import hu.gergelyszalay.zoo.adoption.desktop.adoption.impl.AdoptionDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

@WebServlet("/AdoptionController")
public class AdoptionController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final AdoptionDAO adoptionDAO = new AdoptionDAOImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        Adoption adoption = new Adoption();

        try {

            try{
                adoption.setAnimalId(Integer.parseInt(request.getParameter("animalId")));
                adoption.setAdopterId(Integer.parseInt(request.getParameter("adopterId")));
                adoption.setAdoptionDate(LocalDate.now());
                adoption.setSupportType(request.getParameter("supportType"));
                adoption.setSupportAmount(Integer.parseInt(request.getParameter("supportAmount")));
            } catch (NumberFormatException ne){
                System.err.println("AnimalId, adopterId, Support amount must be a valid number!");
            }

            adoption = adoptionDAO.save(adoption);
            response.sendRedirect("pages/list-contact.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (req.getParameter("searchedTerm") != null && !req.getParameter("searchedTerm").equals("")){
//            resultsList = animalDAO.findAnimal(req.getParameter("searchedTerm"));
//            req.setAttribute("animalList", resultsList);
//            System.out.println(resultsList.size());
//            resp.sendRedirect("pages/list-contact.jsp");
//        } else {
//            resultsList = animalDAO.findAll();
//            req.setAttribute("animalList", resultsList);
//        }

    }


}
