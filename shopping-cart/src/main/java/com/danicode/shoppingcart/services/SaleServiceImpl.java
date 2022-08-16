package com.danicode.shoppingcart.services;

import com.danicode.shoppingcart.entities.Detail;
import com.danicode.shoppingcart.entities.Sale;
import com.danicode.shoppingcart.entities.ShoppingCart;
import com.danicode.shoppingcart.repositories.ISaleRepository;
import com.danicode.shoppingcart.security.entities.User;
import com.danicode.shoppingcart.security.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
public class SaleServiceImpl implements ISaleService {

    @Autowired
    private ISaleRepository saleRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IShoppingCartService shoppingCartService;

    @Autowired
    private IDetailService detailService;

    @Override
    public List<Sale> getSalesByClient(String username) {
        return this.saleRepository.findByClient_Username(username);
    }

    @Override
    public void createSale(String username) {
        User client = this.userService.getByUsername(username).get();
        List<ShoppingCart> shoppingCartList = this.shoppingCartService.getListByClient(client.getUsername());
        DecimalFormat decimalFormat = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        double total = shoppingCartList
                .stream()
                .mapToDouble(
                        shoppingCartItem -> shoppingCartItem.getProduct().getPrice() * shoppingCartItem.getAmount())
                .sum();

        Sale sale = new Sale(Double.parseDouble(decimalFormat.format(total)), new Date(), client);
        Sale saleSave = this.saleRepository.save(sale);
        for (ShoppingCart shoppingCart : shoppingCartList) {
            Detail detail = new Detail();
            detail.setProduct(shoppingCart.getProduct());
            detail.setAmount(shoppingCart.getAmount());
            detail.setSale(saleSave);
            this.detailService.createDetail(detail);
        }
        this.shoppingCartService.cleanShoppingCart(client.getId());
    }
}
