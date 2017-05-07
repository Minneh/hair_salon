import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  //code for Heroku deploment
  static int getHerokuAssignedPort() {
    ProcessBuilder processBuilder = new ProcessBuilder();
    if (processBuilder.environment().get("PORT") != null) {
      return Integer.parseInt(processBuilder.environment().get("PORT"));
    }
    return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
  }

  public static void main(String[] args) {
    port(getHerokuAssignedPort());//Line added for Heroku deployment

    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    //home
    get( "/",(request,response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template","templates/index.vtl");
      return new ModelAndView(model,layout);
    },new VelocityTemplateEngine());

    get("clients/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/client-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //create new stylist
    get("/stylists/new", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/stylist-form.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //list stylists
    get("/stylists", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("stylists", Stylist.all());
    model.put("template", "templates/stylists.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    String name = request.queryParams("name");
    Stylist newStylist = new Stylist(name);
    newStylist.save();
    model.put("template", "templates/stylist-success.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/clients", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
    String name = request.queryParams("name");
    Client newClient = new Client(name, stylist.getId());
    newClient.save();
    model.put("stylist", stylist);
    model.put("template", "templates/stylist-clients-success.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

    post("/categories/:stylistId/clients/:id", (request, response) -> {
    Map <String, Object> model = new HashMap<String, Object>();
    Client client = Client.find(Integer.parseInt(request.params("id")));
    String name = request.queryParams("name");
    Stylist stylist = Stylist.find(client.getStylistId());
    client.update(name);
    String url = String.format("/categories/%d/clients/%d", stylist.getId(), client.getId());
    response.redirect(url);
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

    post("/categories/:stylist_id/clients/:id/delete", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Client client = Client.find(Integer.parseInt(request.params("id")));
    Stylist stylist = Stylist.find(client.getStylistId());
    client.delete();
    model.put("stylist", stylist);
    model.put("template", "templates/stylist.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  }
}
