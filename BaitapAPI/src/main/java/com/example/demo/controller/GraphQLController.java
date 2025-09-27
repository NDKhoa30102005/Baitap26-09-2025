package com.example.demo.controller;



import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;

@Controller
public class GraphQLController {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public GraphQLController(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @QueryMapping
    public List<Product> allProductsSortedByPrice() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    @QueryMapping
    public List<Product> productsByCategory(@Argument Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @QueryMapping
    public List<User> users() {
        return userRepository.findAll();
    }

    @QueryMapping
    public Optional<User> user(@Argument Long id) {
        return userRepository.findById(id);
    }

    @QueryMapping
    public List<Category> categories() {
        return categoryRepository.findAll();
    }

    @QueryMapping
    public Optional<Category> category(@Argument Long id) {
        return categoryRepository.findById(id);
    }

    @QueryMapping
    public List<Product> products() {
        return productRepository.findAll();
    }

    @QueryMapping
    public Optional<Product> product(@Argument Long id) {
        return productRepository.findById(id);
    }

    @MutationMapping
    public User createUser(@Argument String fullname, @Argument String email, @Argument String password, @Argument String phone) {
        User user = new User();
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        return userRepository.save(user);
    }

    @MutationMapping
    public User updateUser(@Argument Long id, @Argument String fullname, @Argument String email, @Argument String password, @Argument String phone) {
        User user = userRepository.findById(id).orElseThrow();
        if (fullname != null) user.setFullname(fullname);
        if (email != null) user.setEmail(email);
        if (password != null) user.setPassword(password);
        if (phone != null) user.setPhone(phone);
        return userRepository.save(user);
    }

    @MutationMapping
    public boolean deleteUser(@Argument Long id) {
        userRepository.deleteById(id);
        return true;
    }

    @MutationMapping
    public Category createCategory(@Argument String name, @Argument String images) {
        Category category = new Category();
        category.setName(name);
        category.setImages(images);
        return categoryRepository.save(category);
    }

    @MutationMapping
    public Category updateCategory(@Argument Long id, @Argument String name, @Argument String images) {
        Category category = categoryRepository.findById(id).orElseThrow();
        if (name != null) category.setName(name);
        if (images != null) category.setImages(images);
        return categoryRepository.save(category);
    }

    @MutationMapping
    public boolean deleteCategory(@Argument Long id) {
        categoryRepository.deleteById(id);
        return true;
    }

    @MutationMapping
    public Product createProduct(@Argument String title, @Argument Integer quantity, @Argument String desc, @Argument Double price, @Argument Long userid, @Argument List<Long> categoryIds) {
        Product product = new Product();
        product.setTitle(title);
        product.setQuantity(quantity);
        product.setDescription(desc);
        product.setPrice(price);
        product.setUser(userRepository.findById(userid).orElseThrow());
        if (categoryIds != null) {
            Set<Category> categories = categoryIds.stream()
                    .map(categoryRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
            product.setCategories(categories);
        }
        return productRepository.save(product);
    }

    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument String title, @Argument Integer quantity, @Argument String desc, @Argument Double price, @Argument Long userid, @Argument List<Long> categoryIds) {
        Product product = productRepository.findById(id).orElseThrow();
        if (title != null) product.setTitle(title);
        if (quantity != null) product.setQuantity(quantity);
        if (desc != null) product.setDescription(desc);
        if (price != null) product.setPrice(price);
        if (userid != null) product.setUser(userRepository.findById(userid).orElseThrow());
        if (categoryIds != null) {
            Set<Category> categories = categoryIds.stream()
                    .map(categoryRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
            product.setCategories(categories);
        }
        return productRepository.save(product);
    }

    @MutationMapping
    public boolean deleteProduct(@Argument Long id) {
        productRepository.deleteById(id);
        return true;
    }
}