package com.solt.jpa.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.solt.jpa.entity.Blog;
import com.solt.jpa.entity.Comment;
import com.solt.jpa.entity.User;

@Local
public interface BlogModel extends Serializable {

    public Blog findBlogById(long id);

    public List<Blog> searchBlog(Map<SearchParam, Object> params);

    public void deleteBlog(Blog blog);

    public void createBlog(Blog blog);

    public void saveBlog(Blog blog);
    
    List<Comment> getUserComments(User user);
    
    void saveComment(Comment comment);

    public enum SearchParam {
        Category,
        Tag,
        DateFrom,
        DateTo,
        Keyword,
        Status,
        User
    }

}