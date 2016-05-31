package com.solt.jpa.model;

import java.util.*;

import javax.ejb.Local;

import com.solt.jpa.entity.Category;

@Local
public interface CategoryModel {


    List<Category> getAll();
    
    Category findById(int id);

}