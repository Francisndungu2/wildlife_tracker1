package DAO;

import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

public class Animal {
    public int id;
    public String name;
    public String type;

    //<....................this is my constant................................................................................>
    public static final String ANIMAL_TYPE = "Non-endangered";
    public Animal(String name) {


        this.name = name;
        this.type = ANIMAL_TYPE;
    }

    public int getId() {

        return id;
    }
    public String getName() {

        return name;
    }

    public  String getType() {

        return this.type;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id &&
                Objects.equals(name, animal.name) &&
                Objects.equals(type, animal.type);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }

    public void save () {
        if( this.name == null || this.name.trim().isEmpty()){
            throw new NullPointerException("name cannot be null");
        }
        String sql = "INSERT INTO animals (type, name) VALUES (:type, :name);";
        try(Connection con = DB.sql2o.open()){
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("type", this.type)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();
        }
        public static List<Animal> all() {
            String sql = "SELECT * FROM animals WHERE type = :type;";
            try(Connection con = DB.sql2o.open()) {
                return con.createQuery(sql)
                        .addParameter("type",ANIMAL_TYPE)
                        .throwOnMappingFailure(false)
                        .executeAndFetch(Animal.class);
            }
            public static Animal findById (int id) {
                String sql = "SELECT * FROM animals WHERE id = :id AND type = :type;";
                try (Connection con = DB.sql2o.open()){
                    return con.createQuery(sql)
                            .addParameter("id", id)
                            .addParameter("type", ANIMAL_TYPE)
                            .throwOnMappingFailure(false)
                            .executeAndFetchFirst(Animal.class);
                }
    }
}