package controller;

import hu.gergelyszalay.zoo.adoption.desktop.SupportFrequencyValues;
import hu.gergelyszalay.zoo.adoption.desktop.SupportTypeValues;
import hu.gergelyszalay.zoo.adoption.desktop.adopter.Adopter;
import hu.gergelyszalay.zoo.adoption.desktop.adopter.AdopterDAO;
import hu.gergelyszalay.zoo.adoption.desktop.adopter.AdopterDAOImpl;
import hu.gergelyszalay.zoo.adoption.desktop.adoption.Adoption;
import hu.gergelyszalay.zoo.adoption.desktop.adoption.AdoptionDAO;
import hu.gergelyszalay.zoo.adoption.desktop.adoption.AdoptionDAOImpl;
import hu.gergelyszalay.zoo.adoption.desktop.animal.Animal;
import hu.gergelyszalay.zoo.adoption.desktop.animal.AnimalDAO;
import hu.gergelyszalay.zoo.adoption.desktop.animal.AnimalDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/AdoptionController")
public class AdoptionController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final AdoptionDAO adoptionDAO = new AdoptionDAOImpl();
    private final AnimalDAO animalDAO = new AnimalDAOImpl();
    private final AdopterDAO adopterDAO = new AdopterDAOImpl();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Adoption adoption = new Adoption();
        try {
            try {
                adoption.setAnimalId(Integer.parseInt(request.getParameter("animalId")));
                String emailOfCurrentUser = request.getCookies()[1].getValue();
                List<Adopter> results = adopterDAO.findUser(emailOfCurrentUser);
                Adopter user = results.get(0);
                adoption.setAdopterId(user.getId());
                adoption.setAdoptionDate(LocalDate.now());
                adoption.setSupportType(request.getParameter("supportType"));
                adoption.setSupportAmount(Integer.parseInt(request.getParameter("supportAmount")));
                adoption.setSupportFrequency(request.getParameter("supportFrequency"));
            } catch (NumberFormatException ne) {
                System.err.println("AnimalId, adopterId, Support amount must be a valid number!");
            }
            adoption = adoptionDAO.save(adoption);
            response.sendRedirect("pages/animal-list.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String animalId = req.getParameter("animalId");
        List<Animal> results = animalDAO.findById(animalId);

        List<String> supportTypeList = SupportTypeValues.getValueList();
        List<String> supportFrequencyList = SupportFrequencyValues.getValueList();
        req.setAttribute("supportTypeList", supportTypeList);
        req.setAttribute("supportFrequencyList", supportFrequencyList);
        req.setAttribute("animalToBeAdopted", results.get(0));

    }


}
