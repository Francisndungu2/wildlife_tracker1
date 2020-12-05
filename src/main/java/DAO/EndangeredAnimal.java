package DAO;

import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

public class EndangeredAnimal extends Animal{
    private String health;
    private String age;

    private static final String DATABASE_TYPE = "Endangered Animals";
    public EndangeredAnimal(String name, String health, String age) {
        super(name);
        this.health = health;
        this.age = age;
        this.type = DATABASE_TYPE;
    } public String getHealth()
    {
        return health;
    }

    public String getAge() {

        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EndangeredAnimal that = (EndangeredAnimal) o;
        return Objects.equals(health, that.health) &&
                Objects.equals(age, that.age);
    }

