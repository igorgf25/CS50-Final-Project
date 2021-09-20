package com.igor.wishlistapi.repository;

import com.igor.wishlistapi.model.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {
    List<Content> findByUserIdOrderByInterest(Integer id);
    List<Content> findByUserIdAndCategoryOrderByInterest(Integer id, String category);
    @Query("SELECT C FROM Content AS C WHERE C.userId = ?1 ORDER BY C.interest DESC")
    Page<Content> findTopEight(Integer id, Pageable pageable);
    Optional<Content> findById(Integer id);
    Integer countContentById(Integer id);
    Integer countContentByTitle(String title);
}
