package com.prasad.productapi.controller;

import com.prasad.productapi.model.Product;
import com.prasad.productapi.repo.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class ProductController {

    @Autowired
    ProductRepository productRepository;


    @Autowired
    private ApplicationContext context;

    @GetMapping("/healthcheck")
    ResponseEntity<String> healthcheck() throws InterruptedException {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        log.info("Inside ProductController --> healthcheck");
        String responseText = "product-api healthcheck @ " + timeStamp + " - All OK";
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body(responseText.toString());
    }


    @GetMapping("/shutdown")
    public void shutdownApp() {

        int exitCode = SpringApplication.exit(context, (ExitCodeGenerator) () -> 0);
        System.exit(exitCode);
    }

    @GetMapping("/listAll")
    public List<Product> getAllProducts() {
        log.info("Inside ProductController --> getAllProducts");
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        log.info("Inside ProductController --> getProduct");
        return productRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping("/addProduct")
    public ResponseEntity createProduct(@RequestBody Product product) throws URISyntaxException {
        log.info("Inside ProductController --> createProduct");
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.ok(savedProduct);
    }

    @PostMapping("/update")
    public ResponseEntity updateProduct(@RequestParam Long id, @RequestParam int orderQty) {
        log.info("Inside ProductController --> updateProduct");
        Product product = productRepository.findById(id).orElseThrow(RuntimeException::new);
        log.info("Before update Available Quantity : "+product.getProductAvlQty());
        product.setProductAvlQty((product.getProductAvlQty()- orderQty));
        productRepository.save(product);
        log.info("After update Available Quantity : "+product.getProductAvlQty());
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        log.info("Inside ProductController --> deleteProduct");
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
