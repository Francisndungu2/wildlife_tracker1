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
}