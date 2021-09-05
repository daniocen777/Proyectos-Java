package com.danicode.market.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.danicode.market.domain.Category;
import com.danicode.market.persistence.entity.Categoria;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	// De Categoria a Category
	@Mappings({ @Mapping(source = "idCategoria", target = "categoryId"),
			@Mapping(source = "descripcion", target = "category"), @Mapping(source = "estado", target = "active") })
	Category toCategory(Categoria categoria);

	// De Category a Categoria sin mapear productos
	@InheritInverseConfiguration
	@Mapping(target = "productos", ignore = true)
	Categoria toCategoria(Category category);
}
