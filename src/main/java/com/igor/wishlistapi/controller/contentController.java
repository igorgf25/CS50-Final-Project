package com.igor.wishlistapi.controller;

import com.igor.wishlistapi.controller.dto.ContentRq;
import com.igor.wishlistapi.controller.dto.ContentRs;
import com.igor.wishlistapi.model.Content;
import com.igor.wishlistapi.repository.ContentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/content")
public class contentController {
    private final ContentRepository contentRepository;

    public contentController(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    //Get all the content from a user
    @GetMapping("/{id}")
    public List<Content> findByPerson(@PathVariable("id") Integer id){
        return contentRepository.findByUserIdOrderByInterest(id);
    }

    //Get all the content from a user and a specific category
    @GetMapping("/{id}/{category}")
    public List<Content> findByPersonAndFilter(@PathVariable("id") Integer id, @PathVariable("category") String category){
        return contentRepository.findByUserIdAndCategoryOrderByInterest(id, category);
    }

    //Insert a new content in the database
    @PostMapping("/")
    public void insertContent(@RequestBody ContentRq content){
        LocalDate date = LocalDate.now();
        Content c = new Content();
        c.setTitle(content.getTitle());
        c.setDescription(content.getDescription());
        c.setCategory(content.getCategory());
        c.setInterest(content.getInterest());
        c.setSource(content.getSource());
        c.setUserId(content.getUserId());
        c.setInclusion(date.toString());
        contentRepository.save(c);
    }

    //Delete a content by id
    @DeleteMapping("/delete/{id}")
    public void deleteContent(@PathVariable Integer id){ contentRepository.deleteById(id); }


    //Return the top 8 content order by id
    @GetMapping("/top/{id}")
    public Page<Content> findTopEight(@PathVariable Integer id){
        Pageable page = PageRequest.of(0, 8);
        return contentRepository.findTopEight(id, page);
    }

    //Update a content
    @PostMapping("/update")
    public void updateContent(@RequestBody ContentRs content){
        Content c = new Content();
        c.setId(content.getId());
        c.setTitle(content.getTitle());
        c.setDescription(content.getDescription());
        c.setCategory(content.getCategory());
        c.setInterest(content.getInterest());
        c.setSource(content.getSource());
        c.setUserId(content.getUserId());
        contentRepository.save(c);
    }

    //Find content by id
    @GetMapping("/findId/{userId}")
    public ContentRs findContentById(@PathVariable("userId") Integer userId){
        Optional<Content> content = contentRepository.findById(userId);
        ContentRs c = new ContentRs();
        c = c.convert(content.get());
        return c;
    }
}
