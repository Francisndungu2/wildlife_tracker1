package models;


import org.sql2o.Connection;


public class Endanger {
    private String animal_name;
    private String animal_age;
    private String animal_health;
    private String location;
    private int animal_id;


    public Endanger(String animal_name, String animal_age, String animal_health, String location) {
        this.animal_name = animal_name;
        this.animal_age = animal_age;
        this.animal_health = animal_health;
        this.location = location;
    }


    public String getAnimal_name() {
        return animal_name;
    }

    public String getAnimal_age() {
        return animal_age;
    }

    public String getAnimal_health() {
        return animal_health;
    }

    public String getLocation() {
        return location;
    }




    public void saveSightedAnimal(Sighting sighting) {
        try (Connection conn = Database.sql2o.open()) {
            String sql = "INSERT INTO  sightings(animal_id, animal_location, ranger_name ) VALUES (:animal_id, :animal_location, :ranger_name);";
            this.animal_id = (int) conn.createQuery(sql, true)
                    .addParameter("animal_location", this.location)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }
    }
}

