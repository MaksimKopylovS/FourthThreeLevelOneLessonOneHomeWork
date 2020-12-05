package myPackage;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Market", urlPatterns = "/prod")
public class Market extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Market.class);

    private static List<Product> list = new ArrayList<>();
    public static List<Product> getList(){
        return list;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("text/html");

        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        int cost = Integer.parseInt(req.getParameter("cost"));

        LOGGER.log(Level.INFO, "Send to List: "
                + "id: " +id +"   "
                +"title: " + title+"   "
                +"cost: "+ cost);

        list.add(new Product(id, title, cost));
        for(Product product : list) {
            printWriter.print("id: " +product.getId() +"   "
                    +"title: " + product.getTitle()+"   "
                    +"cost: "+ product.getCost() + "<br/>");
        }
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
