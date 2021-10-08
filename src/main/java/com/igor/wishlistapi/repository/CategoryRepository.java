package com.igor.wishlistapi.repository;

import com.igor.wishlistapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> getByUserId(Integer userId);
    Integer countById(Integer id);
    Integer countByNameAndUserId(String name, Integer userId);
}
