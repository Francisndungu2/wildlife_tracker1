package models;

import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Sighting {
    private String animal_location;
    private String ranger_name;
    private int id;

    public Sighting(String location, String rangerName){
        this.animal_location = location;
        this.ranger_name = rangerName;

    }
    public static Sighting setUpSighting() {
        return new Sighting( "Zone-A", "Cliff");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRanger_name() {
        return ranger_name;
    }

    public void setRanger_name(String ranger_name) {
        this.ranger_name = ranger_name;
    }

    public String getAnimal_location() {
        return animal_location;
    }

    public void setAnimal_location(String animal_location) {
        this.animal_location = animal_location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sighting)) return false;
        Sighting sighting = (Sighting) o;
        return id == sighting.id &&
                getId() == sighting.getId() &&
                animal_location.equals(sighting.animal_location) &&
                ranger_name.equals(sighting.ranger_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animal_location, ranger_name, id, getId());
    }

    public void saveSightedAnimal(Sighting sighting) {
        try (Connection conn = Database.sql2o.open()){
            String sql = "INSERT INTO  sightings( animal_location, ranger_name ) VALUES ( :animal_location, :ranger_name);";
            this.id = (int) conn.createQuery(sql, true)
                    .bind(sighting)

                    .addParameter("animal_location", this.animal_location)
                    .addParameter("ranger_name", this.ranger_name)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Sighting> allSightings() {
        try(Connection conn = Database.sql2o.open()){

            String sql = "SELECT * FROM sightings;";
            return conn.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Sighting.class);
        }
    }

    public Sighting findAnimalById(int id) {
        try (Connection conn = Database.sql2o.open()){
            String sql = "SELECT * FROM sightings WHERE id=:id;";
            Sighting sighting = conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        }catch (IndexOutOfBoundsException ex){
            System.out.println(ex);
            return null;
        }
    }
        public static void clearallSightings (){
            String sql="delete  from sightings *";
            try(Connection con= Database.sql2o.open()){
                con.createQuery(sql)
                        .executeUpdate();
        }
    }


}
