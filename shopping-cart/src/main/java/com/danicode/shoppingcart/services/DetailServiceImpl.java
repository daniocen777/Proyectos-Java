package com.danicode.shoppingcart.services;

import com.danicode.shoppingcart.entities.Detail;
import com.danicode.shoppingcart.repositories.IDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DetailServiceImpl implements IDetailService {

    @Autowired
    private IDetailRepository detailRepository;

    @Override
    public void createDetail(Detail detail) {
        detailRepository.save(detail);
    }

    @Override
    public List<Detail> getDetailBySale(String saleId) {
        return detailRepository.findBySale_Id(saleId);
    }
}
