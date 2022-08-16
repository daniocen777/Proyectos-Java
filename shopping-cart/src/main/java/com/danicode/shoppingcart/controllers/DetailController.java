package com.danicode.shoppingcart.controllers;

import com.danicode.shoppingcart.entities.Detail;
import com.danicode.shoppingcart.services.IDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/saleDetail")
public class DetailController {

    @Autowired
    private IDetailService detailService;

    @GetMapping("/{sale_id}")
    public ResponseEntity<List<Detail>> getDetailsBySale(@PathVariable("sale_id") String id) {
        return new ResponseEntity<>(this.detailService.getDetailBySale(id), HttpStatus.OK);
    }
}
