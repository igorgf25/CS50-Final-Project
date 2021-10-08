package com.igor.wishlistapi.service;

import com.igor.wishlistapi.controller.dto.CategoryRq;
import com.igor.wishlistapi.exceptions.bdException;
import com.igor.wishlistapi.model.Category;
import com.igor.wishlistapi.repository.CategoryRepository;
import com.igor.wishlistapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PersonRepository personRepository;

    public List<Category> getUserCategories(Integer id) throws bdException {
        try{
            return categoryRepository.getByUserId(id);
        }catch (Exception err){
            throw new bdException(err);
        }
    }

    public Category getCategory(Integer id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            return category.get();
        }else{
            throw new NoSuchElementException();
        }
    }

    public ResponseEntity<?> deleteCategory(Integer id){
        Integer count = categoryRepository.countById(id);
        if(count == 1){
            try{
                categoryRepository.deleteById(id);
                return new ResponseEntity<>(null, HttpStatus.OK);
            }catch (Exception err){
                return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
            }
        }else{
            throw new NoSuchElementException();
        }
    }

    public ResponseEntity<?> insertCategory(CategoryRq category){
        Integer count = categoryRepository.countByNameAndUserId(category.getName(), category.getUserId());
        Integer countUser = personRepository.countById(Long.valueOf(category.getUserId()));
        if(count == 0 && countUser == 1){
            Category a =  new Category();
            a.setName(category.getName());
            a.setUserId(category.getUserId());
            try {
                categoryRepository.save(a);
                return new ResponseEntity<>(null, HttpStatus.OK);
            }catch (Exception err){
                return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
            }
        }else{
            throw new NoSuchElementException();
        }
    }
}
