package it.redhat.osd.swarm;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/api/products")
public class ProductsEndPoint {

    public ProductsEndPoint() {

        Product p1 = new Product();
        p1.setId("1");
        p1.setDescription("Description 1");
        p1.setPrice(100);

        Product p2 = new Product();
        p2.setId("1");
        p2.setDescription("Description 2");
        p2.setPrice(200);

        Product p3 = new Product();
        p3.setId("1");
        p3.setDescription("Description 3");
        p3.setPrice(300);

        products = new ArrayList<>();
        products.add(p1);
        products.add(p2);
        products.add(p3);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Product> doGet() {
        return products;
    }

    private ArrayList<Product> products;

}
