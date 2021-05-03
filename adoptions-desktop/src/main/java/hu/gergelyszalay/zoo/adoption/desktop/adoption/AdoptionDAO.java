package hu.gergelyszalay.zoo.adoption.desktop.adoption;

import hu.gergelyszalay.zoo.adoption.desktop.adoption.Adoption;

import java.util.List;

public interface AdoptionDAO {

    List<Adoption> findAll();
    Adoption save(Adoption contact);
    void delete(Adoption contact);
    List<Adoption> findByAdopterId(Integer adopterId);
    List<Adoption> findByAnimalId(Integer animalId);
    List<Adoption> findBySupportType(String supportType);
    List<Adoption> searchByYear(Integer year);

}
