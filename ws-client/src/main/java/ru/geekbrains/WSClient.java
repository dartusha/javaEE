package ru.geekbrains;

import ru.geekbrains.persist.ProductRepositoryWS;
import ru.geekbrains.persist.ProductService;

import java.net.MalformedURLException;
import java.net.URL;

public class WSClient {
    public static void main(String[] args) throws MalformedURLException {
        URL url=new URL("http://localhost:8080/base-jsf-webapp/ProductService/ProductRepositoryImpl?wsdl");
        ProductService productService=new ProductService(url);
        ProductRepositoryWS productRepositoryImplPort=productService.getProductRepositoryImplPort();
        productRepositoryImplPort.findAll()
                .forEach(p->System.out.println(p.getName()));
        System.out.println(productRepositoryImplPort.findById(1)
                .getName());
    }
}
