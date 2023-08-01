package com.danicode.inventory.services;

import com.danicode.inventory.dao.ICategoryDao;
import com.danicode.inventory.model.Category;
import com.danicode.inventory.response.CategoryResponseRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryDao categoryDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> search() {
        CategoryResponseRest response = new CategoryResponseRest();
        try {
            List<Category> categories = (List<Category>) categoryDao.findAll();
            response.getCategoryResponse().setCategories(categories);
            response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
        } catch (Exception e) {
            response.setMetadata("Respuesta NOK", "-1", "Error CategoryServiceImpl => search");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> searchById(Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> categoryList = new ArrayList<>();
        try {
            Optional<Category> category = categoryDao.findById(id);
            if (category.isPresent()) {
                categoryList.add(category.get());
                response.getCategoryResponse().setCategories(categoryList);
                response.setMetadata("Respuesta OK", "00", "Categoría encontrada");
            } else {
                response.setMetadata("Respuesta NOK", "-1", "Categoría no encontrada");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
            }
            response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
        } catch (Exception e) {
            response.setMetadata("Respuesta NOK", "-1", "Error CategoryServiceImpl => searchById");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> save(Category category) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> categoryList = new ArrayList<>();
        try {
            Category categorySaved = categoryDao.save(category);
            if (categorySaved != null) {
                categoryList.add(categorySaved);
                response.getCategoryResponse().setCategories(categoryList);
                response.setMetadata("Respuesta OK", "00", "Categoría guardada");
            } else {
                response.setMetadata("Respuesta NOK", "-1", "Error al grabar categoría");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response.setMetadata("Respuesta NOK", "-1", "Error al grabar categoría");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> update(Category category, Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> categoryList = new ArrayList<>();
        try {
            Optional<Category> categorySearch = categoryDao.findById(id);
            if (categorySearch.isPresent()) {
                categorySearch.get().setName(category.getName());
                categorySearch.get().setDescription((category.getDescription()));
                Category categoryToUpdate = categoryDao.save(categorySearch.get());
                if (categoryToUpdate != null) {
                    categoryList.add(categoryToUpdate);
                    response.getCategoryResponse().setCategories(categoryList);
                    response.setMetadata("Respuesta OK", "00", "Categoría actualizada correctamente");
                } else {
                    response.setMetadata("Respuesta NOK", "-1", "Error al actualizar categoría");
                    return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                response.setMetadata("Respuesta NOK", "-1", "Categoría no encontrada");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetadata("Respuesta NOK", "-1", "Error al actualizar categoría");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> deleteById(Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        try {
            categoryDao.deleteById(id);
            response.setMetadata("Respuesta OK", "00", "Categoría eliminada correctamente");
        } catch (Exception e) {
            response.setMetadata("Respuesta NOK", "-1", "Error al eliminar categoría");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }
}
