package com.danicode.shoppingcart.services;

import com.danicode.shoppingcart.entities.Detail;

import java.util.List;

public interface IDetailService {

    public void createDetail(Detail detail);

    public List<Detail> getDetailBySale(String saleId);
}
