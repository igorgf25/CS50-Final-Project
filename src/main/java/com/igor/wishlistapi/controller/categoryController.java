package com.igor.wishlistapi.controller;

import com.igor.wishlistapi.controller.dto.CategoryRq;
import com.igor.wishlistapi.exceptions.bdException;
import com.igor.wishlistapi.model.Category;
import com.igor.wishlistapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/category")
public class categoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public List<Category> getCategories(@PathVariable("id") Integer id) throws bdException {
        return categoryService.getUserCategories(id);
    }

    @GetMapping("/byId/{id}")
    public Category getCategory(@PathVariable("id") Integer id){
        return categoryService.getCategory(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Integer id){
        return categoryService.deleteCategory((id));
    }

    @PostMapping("/")
    public ResponseEntity<?> insertCategory(@RequestBody CategoryRq category){
        return categoryService.insertCategory(category);
    }
}
