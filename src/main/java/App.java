import models.Animal;
import models.Sighting;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.staticFileLocation;
import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");


        get("/",(request, response) -> {
            Map<String,Object>model =new HashMap<>();
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        get ("/form",(request, response) -> {
            Map<String,Object>model =new HashMap<>();
            return new ModelAndView(model,"forms.hbs");
        },new HandlebarsTemplateEngine());

        post("/view",(request,response)->{
            Map<String, Object> model = new HashMap<>();
            String location=request.queryParams("sightingLocation");
            String age = request.queryParams(" age");
            String animalName = request.queryParams("animalName");
            String health = request.queryParams("health");
            String species = request.queryParams("species");
            String ranger = request.queryParams("ranger");
            Sighting newSighting = new Sighting(location, ranger);
            newSighting.saveSightedAnimal(newSighting);
            Animal newAnimals = new Animal(animalName, age, health, species);
            newAnimals.saveAnimal(newAnimals);
            model.put("sightings", Sighting.allSightings());
            model.put("animals", Animal.getAllAnimals());
            return new ModelAndView(model,"myview.hbs");
        },new HandlebarsTemplateEngine());

        get ("/view",(request, response) -> {
            Map<String,Object>model =new HashMap<>();
            List <Sighting> allSightings = Sighting.allSightings();
            model.put("sightings",allSightings);
            return new ModelAndView(model,"myview.hbs");
        },new HandlebarsTemplateEngine());

        get("/clearAll",(request, response) -> {
         Sighting.clearallSightings();
         Map<String,Object>model =new HashMap<>();
            return new ModelAndView(model,"success.hbs");
        } ,new HandlebarsTemplateEngine());

    }
}