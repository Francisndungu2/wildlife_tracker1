package models;

import interfaces.AnimalInterface;
import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Animal implements AnimalInterface{



    private String animal_name;
    private String animal_health;
    private String animal_age;
    private String location;
    private int id;


    public Animal( String animal_name,String animal_health,String animal_age,String location){

        this.animal_name = animal_name;
        this.animal_health=animal_health;
        this.animal_age=animal_age;
        this.location=location;

    }


    public static Animal setUpNewAnimal() {
        return new Animal("Zebra","ill","34","river");
    }




    public String getAnimal_name() {
        return animal_name;
    }

    public void setAnimal_name(String animal_name) {
        this.animal_name = animal_name;
    }

    public String getAnimal_health() {
        return animal_health;
    }

    public void setAnimal_health(String animal_health) {
        this.animal_health = animal_health;
    }

    public String getAnimal_age() {
        return animal_age;
    }

    public void setAnimal_age(String animal_age) {
        this.animal_age = animal_age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id &&
                Objects.equals(animal_name, animal.animal_name) &&
                Objects.equals(animal_health, animal.animal_health) &&
                Objects.equals(animal_age, animal.animal_age) &&
                Objects.equals(location, animal.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animal_name, animal_health, animal_age, location, id);
    }

    @Override
    public void saveAnimal(Animal animal) {
        try (Connection conn = Database.sql2o.open()){
            String sql = "INSERT INTO  animals( animal_name,animal_health,animal_age,location ) VALUES ( :animal_name,:animal_health,:animal_age,:location)";
            this.id = (int) conn.createQuery(sql, true)
                    .addParameter("animal_name", this.animal_name)
                    .addParameter("animal_health", this.animal_health)
                    .addParameter("animal_age",this.animal_age)
                    .addParameter("location",this.location)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Animal> getAllAnimals() {
        try(Connection conn = Database.sql2o.open()){
            String sql = "SELECT * FROM animals ORDER BY id DESC;";
            return conn.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animal.class);
        }
    }

    @Override
    public Animal findById(int id) {
        String sql = "SELECT * FROM animals WHERE id=:id;";
        try (Connection conn = Database.sql2o.open()){
            Animal animal = conn.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Animal.class);
            return animal;
        }catch (IndexOutOfBoundsException ex){
            System.out.println(ex);
            return null;
        }
    }

}


