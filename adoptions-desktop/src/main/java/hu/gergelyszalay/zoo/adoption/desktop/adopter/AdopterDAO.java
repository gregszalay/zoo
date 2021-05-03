package hu.gergelyszalay.zoo.adoption.desktop.adopter;

import hu.gergelyszalay.zoo.adoption.desktop.adopter.Adopter;

import java.util.List;

public interface AdopterDAO {

    List<Adopter> findAll();
    List<Adopter> findUser(String email, String password);
    List<Adopter> findUser(String email);
    Adopter save(Adopter contact);
    void delete(Adopter contact);


}
