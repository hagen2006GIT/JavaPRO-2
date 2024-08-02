package org.example;

import org.example.repository.ClientRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {

    private static ClientRepository clientRepository;

    public App(ClientRepository clientRepository) {
        App.clientRepository = clientRepository;
    }

    public static void main( String[] args ) {
        ApplicationContext ctx = SpringApplication.run(App.class, args);

        clientRepository.findAll().forEach(System.out::println);

//        ProductService productService = ctx.getBean(ProductService.class);
//        ClientService clientService = ctx.getBean(ClientService.class);
//
//        productService.save(new ProductDto(null, "TestAccount_1", 158.8, ProductType.ACCOUNT, 1L));
//        productService.save(new ProductDto(null, "TestAccount_2", 0.0, ProductType.CARD, 1L));
//        productService.save(new ProductDto(null, "TestAccount_3", 1058.1, ProductType.CARD, 2L));
//        productService.save(new ProductDto(null, "TestAccount_4", 34.2, ProductType.ACCOUNT, 3L));
//        productService.save(new ProductDto(null, "TestAccount_5", 0.0, ProductType.ACCOUNT, 2L));
//
//        clientService.save(new ClientDto(null, "TestClient_1"));
//        clientService.save(new ClientDto(null, "TestClient_2"));
//        clientService.save(new ClientDto(null, "TestClient_3"));
    }
}