package hu.gergelyszalay.zoo.adoption.desktop.animal;

import hu.gergelyszalay.zoo.adoption.desktop.animal.Animal;

import java.util.List;

public interface AnimalDAO {

    List<Animal> findAll();
    public List<Animal> findById(String animalId);
    List<Animal> findAnimal(String searchedTerm);
    Animal save(Animal contact);
    void delete(Animal contact);

}
