package com.danicode.inventory.services;

import com.danicode.inventory.model.Category;
import com.danicode.inventory.response.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {
    ResponseEntity<CategoryResponseRest> search();

    ResponseEntity<CategoryResponseRest> searchById(Long id);

    ResponseEntity<CategoryResponseRest> save(Category category);

    ResponseEntity<CategoryResponseRest> update(Category category, Long id);

    ResponseEntity<CategoryResponseRest> deleteById(Long id);
}
