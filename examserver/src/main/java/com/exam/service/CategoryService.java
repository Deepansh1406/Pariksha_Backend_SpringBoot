package com.exam.service;

import com.exam.model.exam.Category;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service

public interface CategoryService {

    public Category addCategory(Category category);
    public Category updateCategory(Category category);

    public Set<Category> getCategories();
    public  Category getCategory(Long categoryId);

    public  void deleteCategory(Long categoryId);



}
