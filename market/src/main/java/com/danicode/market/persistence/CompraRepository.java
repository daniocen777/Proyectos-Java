package com.danicode.market.persistence;

import com.danicode.market.domain.Purchase;
import com.danicode.market.domain.repository.PurchaseRepository;
import com.danicode.market.persistence.crud.CompraCrudRepository;
import com.danicode.market.persistence.entity.Compra;
import com.danicode.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public List<Purchase> getAll() {

        return purchaseMapper.toPurchases((List<Compra>) this.compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return this.compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> purchaseMapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = purchaseMapper.toCompra(purchase);
        // Guardado en cascada (Compra conoce los productos y los productos
        // conozcan a que compra pertenece) => en el atributo productos de la
        // clase Compra: cascade = {CascadeType.ALL}
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return purchaseMapper.toPurchase(this.compraCrudRepository.save(compra));
    }
}
