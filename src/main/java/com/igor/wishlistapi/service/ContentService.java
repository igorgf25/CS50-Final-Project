package com.igor.wishlistapi.service;

import com.igor.wishlistapi.controller.dto.ContentRq;
import com.igor.wishlistapi.controller.dto.ContentRs;
import com.igor.wishlistapi.model.Content;
import com.igor.wishlistapi.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    public List<Content> findByPerson(Integer id){
        return contentRepository.findByUserIdOrderByInterest(id);
    }

    public List<Content> findByPersonAndFilter(Integer id, String category){
        return contentRepository.findByUserIdAndCategoryOrderByInterest(id, category);
    }

    public void insertContent(ContentRq content){

        Integer titleExist = contentRepository.countContentByTitle(content.getTitle());

        if(titleExist.equals(0)){
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
        }else{
            throw new IllegalArgumentException();
        }

    }

    public void deleteContent( Integer id){
        Integer contentExist = contentRepository.countContentById(id);
        if(contentExist.equals(1)) {
            contentRepository.deleteById(id);
        }else{
            throw new NoSuchElementException();
        }
    }

    public Page<Content> findTopEight(Integer id){
        Pageable page = PageRequest.of(0, 8);
        return contentRepository.findTopEight(id, page);
    }

    public void updateContent(ContentRs content){
        Integer contentExist = contentRepository.countContentById(content.getId());
        if(contentExist.equals(1)){
            Content c = new Content();
            c.setId(content.getId());
            c.setTitle(content.getTitle());
            c.setDescription(content.getDescription());
            c.setCategory(content.getCategory());
            c.setInterest(content.getInterest());
            c.setSource(content.getSource());
            c.setUserId(content.getUserId());
            contentRepository.save(c);
        }else{
            throw new NoSuchElementException();
        }
    }

    public ContentRs findContentById(Integer userId){
        Optional<Content> content = contentRepository.findById(userId);
        ContentRs c = new ContentRs();
        if(content.isPresent()){
            c = c.convert(content.get());
            return c;
        }else{
            throw new NoSuchElementException();
        }

    }

}
