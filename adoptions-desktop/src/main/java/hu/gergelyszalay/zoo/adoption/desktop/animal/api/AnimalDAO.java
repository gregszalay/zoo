package hu.gergelyszalay.zoo.adoption.desktop.animal.api;

import hu.gergelyszalay.zoo.adoption.desktop.animal.Animal;

import java.util.List;

public interface AnimalDAO {

    List<Animal> findAll();
    Animal save(Animal contact);
    void delete(Animal contact);

}
