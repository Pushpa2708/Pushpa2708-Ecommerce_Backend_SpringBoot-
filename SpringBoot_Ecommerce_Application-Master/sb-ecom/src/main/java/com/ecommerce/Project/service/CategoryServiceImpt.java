package com.ecommerce.Project.service;

import java.util.List;
import com.ecommerce.Project.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Project.repositories.CategoryRepository;


@Service
public class CategoryServiceImpt implements CategoryService{

//    private  List<Category> categories = new ArrayList<>();
    private long nextId = 1L;


    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public boolean createCategory(Category category) {
        if(category != null){
            category.setCategoryId(nextId++);
            categoryRepository.save(category);
            return true;
        }
        else {
            return false;
        }

    }


    @Override
    public String deleteCetagory(Long categoryId) {
        if (categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
            return "Category " + categoryId + " deleted successfully";
        }
        return "Category not found";
    }


    @Override
    public boolean updateCategory(Category category, Long categoryId) {
        return categoryRepository.findById(categoryId).map(existingCategory ->{
            existingCategory.setCategoryName(category.getCategoryName());
            categoryRepository.save(existingCategory);
            return true;
        }).orElse(false);
    }
    


}
