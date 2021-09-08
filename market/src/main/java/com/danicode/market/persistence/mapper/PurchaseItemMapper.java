package com.danicode.market.persistence.mapper;

import com.danicode.market.domain.PurchaseItem;
import com.danicode.market.persistence.entity.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {

    // El atributo total esta en ambas clases, no es necesario mapearlo
    @Mappings({
            @Mapping(source = "id.idProducto", target = "productId"),
            @Mapping(source = "cantidad", target = "quantity"),
            @Mapping(source = "total", target = "total"),
            @Mapping(source = "estado", target = "active")
    })
    PurchaseItem toPurchaseItem(ComprasProducto producto);

    /*
     * Conversion contraria
     * Se debe mapear todos los atributos, si no se usan, mapearlos con
     * ignore = true (en ComprasProducto, ignorar "compra", "producto") y
     * dentro de "ComprasProductoPK", ignorar "idCompra"
     * */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "id.idCompra", ignore = true),
    })
    ComprasProducto toComprasProducto(PurchaseItem item);
}
