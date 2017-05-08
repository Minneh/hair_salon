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

    get("/add-stylist", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/add-stylist.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/delete/:id", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        int stylist_id = Integer.parseInt(request.params(":id"));
        Stylist.deleteStylistById(stylist_id);
        model.put("stylists", Stylist.all());
        model.put("template", "templates/index.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/stylist/:id", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        int stylist_id = Integer.parseInt(request.params(":id"));
        model.put("clients", Client.getClientsByStylistId(stylist_id));
        model.put("stylist", Stylist.find(stylist_id));
        model.put("template", "templates/stylist.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/delete-client/:id/stylist/:stylist_id", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        int client_id = Integer.parseInt(request.params(":id"));
        Client.deleteClientById(client_id);
        Integer stylist_id = Integer.parseInt(request.params(":stylist_id"));
        model.put("stylist", Stylist.find(stylist_id));
        model.put("clients", Client.getClientsByStylistId(stylist_id));
        model.put("template", "templates/stylist.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      post("/stylists", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        String stylist = request.queryParams("stylist_name");
        Stylist newStylist = new Stylist(stylist);
        newStylist.save();
        model.put("stylists", Stylist.all());
        model.put("template", "templates/index.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      post("/client/:id", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        String client_name = request.queryParams("client_name");
        int stylist_id = Integer.parseInt(request.queryParams("stylistId"));
        Client newClient = new Client(client_name, stylist_id);
        if (client_name != null) {
          newClient.save();
        }
        model.put("stylist", Stylist.find(Integer.parseInt(request.params(":id"))));
        model.put("clients", Client.getClientsByStylistId(stylist_id));
        model.put("template", "templates/stylist.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

  }
}
