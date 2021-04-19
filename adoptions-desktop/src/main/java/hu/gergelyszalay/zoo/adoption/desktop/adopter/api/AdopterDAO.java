package hu.gergelyszalay.zoo.adoption.desktop.adopter.api;

import hu.gergelyszalay.zoo.adoption.desktop.adopter.Adopter;

import java.util.List;

public interface AdopterDAO {

    List<Adopter> findAll();
    Adopter save(Adopter contact);
    void delete(Adopter contact);

}
