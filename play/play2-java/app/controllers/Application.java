package controllers;

import models.MyObject;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import play.*;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {
  
    public static Result index() throws Exception {
        String json = "[{\"id\": 1, \"name\": \"Alice\"},{\"id\": 2, \"name\": \"Bob\"},{\"id\": 3, \"name\": \"Charlie\"}]";
        JsonNode root = Json.parse(json);

        List<MyObject> objects = new ObjectMapper().readValue(root, new TypeReference<List<MyObject>>(){});
        for(MyObject obj : objects){
            System.out.println(obj.toString());
        }

        return ok(index.render("Your new application is ready."));
    }
  
}
