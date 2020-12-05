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
    }
