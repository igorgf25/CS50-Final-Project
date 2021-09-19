package com.igor.wishlistapi.controller.dto;

import com.igor.wishlistapi.model.Content;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContentRs {
    private Integer id;
    private String title;
    private String description;
    private String category;
    private String source;
    private String inclusion;
    private Integer interest;
    private Integer userId;

    public static ContentRs convert(Content c){
        ContentRs content = new ContentRs();
        content.setCategory(c.getCategory());
        content.setDescription(c.getDescription());
        content.setInterest(c.getInterest());
        content.setSource(c.getSource());
        content.setId(c.getId());
        content.setInclusion(c.getInclusion());
        content.setTitle(c.getTitle());
        content.setUserId(c.getUserId());

        return content;
    }
}
