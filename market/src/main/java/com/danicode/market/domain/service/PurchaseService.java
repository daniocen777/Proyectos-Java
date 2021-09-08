package com.danicode.market.domain.service;

import com.danicode.market.domain.Purchase;
import com.danicode.market.domain.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Purchase> getAll() {
        return this.purchaseRepository.getAll();
    }

    public Optional<List<Purchase>> getByClient(String clientId) {
        return this.purchaseRepository.getByClient(clientId);
    }

    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }
}
