package com.danicode.inventory.response;

import com.danicode.inventory.model.Category;

import java.util.List;

public class CategoryResponse {
    private List<Category> categories;

    public CategoryResponse() {
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
