package hu.gergelyszalay.zoo.adoption.desktop.adoption.api;

import hu.gergelyszalay.zoo.adoption.desktop.adoption.Adoption;

import java.util.List;

public interface AdoptionDAO {

    List<Adoption> findAll();
    Adoption save(Adoption contact);
    void delete(Adoption contact);

}
