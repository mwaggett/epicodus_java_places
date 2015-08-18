import java.util.Random;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.*;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.Map;

public class App {

  public static void main(String[] args) {

    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap model = new HashMap();
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/locations", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      ArrayList<Place> locationsList = request.session().attribute("locationsList");

      if (locationsList == null) {
        locationsList = new ArrayList<Place>();
        request.session().attribute("locationsList", locationsList);
      }

      String location = request.queryParams("location");
      Place newPlace = new Place(location);
      locationsList.add(newPlace);

      model.put("locationsList", request.session().attribute("locationsList"));

      model.put("template", "templates/locationslist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
