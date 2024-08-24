package me.nadetdev.rest.service;

import me.nadetdev.rest.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setUp(){
        productService = new ProductService();
    }

    @AfterEach
    void tearDown() {
        productService = null;
    }

    @Test
    void testGetProductList() {
        List<Product> products = productService.getProductList();
        assertNotNull(products);
        assertEquals(5, products.size());
    }
}
