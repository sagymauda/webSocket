
import spark.ModelAndView;
import  spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {

        Spark.webSocket("/websocket", EchoWebSocket.class);
        Spark.init();

        Spark.get("/",((request, response) -> {
            return "hi there man";
        }));

        Spark.get("/bla",((request, response) -> {
            return "bla bla bla bla ";
        }));

        Spark.get("/echo",((request, response) -> {
            HashMap<String,Object> model = new HashMap<>();
            return new ThymeleafTemplateEngine().render(new ModelAndView(model,"echoview"));
        }));
    }
}