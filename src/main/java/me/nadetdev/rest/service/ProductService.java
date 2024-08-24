package me.nadetdev.rest.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.nadetdev.rest.models.Product;

import java.util.ArrayList;
import java.util.List;


@Path("/products")
@ApplicationScoped
public class ProductService {

    private List<Product> productList;

    public ProductService(){
        productList = new ArrayList<>();
        productList.add(new Product(Integer.parseInt("1"), "Product #1","Description of product 1", 100, 10));
        productList.add(new Product(Integer.parseInt("2"), "Product #2","Description of product 2", 200, 20));
        productList.add(new Product(Integer.parseInt("3"), "Product #3","Description of product 3", 300, 30));
        productList.add(new Product(Integer.parseInt("4"), "Product #4","Description of product 4", 400, 40));
        productList.add(new Product(Integer.parseInt("5"), "Product #5","Description of product 5", 500, 50));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProductList(){
        return productList;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(Product product) {
        System.out.println("Product created");
        productList.add(product);
        return Response.status(Response.Status.CREATED)
                .entity("New product created").build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(Product product) {
        Response response;

        System.out.println("Updating product");

        for (Product p : productList) {
            if (product.getId().equals(p.getId())) {

                p.setName(product.getName());
                p.setDescription(product.getDescription());
                p.setPrice(product.getPrice());
                p.setQuantity(product.getQuantity());

                response = Response.status(Response.Status.NO_CONTENT)
                        .entity("An existing product updated").build();
                return response;
            }
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("Product with id->" + product.getId() + " does not exist")
                                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") Integer id) {
        // Delete a product
        Response response;
        System.out.println("Deleting product with id: " + id);
        for (int i=0; i < productList.size(); i++) {
            if (id.equals(productList.get(i).getId())) {
                productList.remove(i);
                response = Response.status(Response.Status.NO_CONTENT)
                        .entity("A product deleted").build();
                return response;
            }
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("Product with id->" + id + " does not exist")
                .build();
    }

}
