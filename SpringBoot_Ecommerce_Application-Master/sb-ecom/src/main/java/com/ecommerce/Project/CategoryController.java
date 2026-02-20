package com.ecommerce.Project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Project.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/public/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @PostMapping("/public/categories")
    public ResponseEntity<String> addCategories(@RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category added Succesfully" , HttpStatus.OK);
    }

    @DeleteMapping("/public/categories/{CategoryId}")
    public ResponseEntity<String> deleteCetagories(@PathVariable Long CategoryId){
        String status = categoryService.deleteCetagory(CategoryId);
        return new ResponseEntity<>(status , HttpStatus.OK);
    }

    @PutMapping("/public/categories/{CategoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category, @PathVariable Long CategoryId) {
        boolean isUpdated = categoryService.updateCategory(category, CategoryId);
        if (isUpdated) {
            return new ResponseEntity<>("Category is Updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Category is not Updated", HttpStatus.NOT_FOUND);
        }
    }

}
