package com.igor.wishlistapi.controller;

import com.igor.wishlistapi.controller.dto.ContentRq;
import com.igor.wishlistapi.controller.dto.ContentRs;
import com.igor.wishlistapi.model.Content;
import com.igor.wishlistapi.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content")
@CrossOrigin(origins="*")
public class contentController {
    @Autowired
    private ContentService contentService;

    //Get all the content from a user
    @GetMapping("/{id}")
    public List<Content> findByPerson(@PathVariable("id") Integer id){
        return contentService.findByPerson(id);
    }

    //Get all the content from a user and a specific category
    @GetMapping("/{id}/{category}")
    public List<Content> findByPersonAndFilter(@PathVariable("id") Integer id,
                                               @PathVariable("category") String category){
        return contentService.findByPersonAndFilter(id, category);
    }

    //Insert a new content in the database
    @PostMapping("/")
    public void insertContent(@RequestBody ContentRq content){
        contentService.insertContent(content);
    }

    //Delete a content by id
    @DeleteMapping("/{id}")
    public void deleteContent(@PathVariable Integer id){ contentService.deleteContent(id); }


    //Return the top 8 content order by id
    @GetMapping("/top/{id}")
    public Page<Content> findTopEight(@PathVariable Integer id){
        return contentService.findTopEight(id);
    }

    //Update a content
    @PutMapping("/")
    public void updateContent(@RequestBody ContentRs content){
        contentService.updateContent(content);
    }

    //Find content by id
    @GetMapping("/findId/{contentId}")
    public ContentRs findContentById(@PathVariable("contentId") Integer contentId){
        return contentService.findContentById(contentId);
    }
}
