package com.danicode.storage;

import com.danicode.objects.Meat;
import com.danicode.objects.Potato;
import com.danicode.objects.Product;
import com.danicode.objects.Rice;

public class Database {

    private Product[] products;

    public Database() {
        this.products = new Product[3];
        Product potato = new Potato("Para Amarilla");
        Product rice = new Rice("Costeño");
        Product meat = new Meat("Lomo Fino");
        this.products[0] = potato;
        this.products[1] = rice;
        this.products[2] = meat;
    }

    public Product getByIndex(int index) {
        if (index < 0 || index > 2) {
            System.out.println("Índice no válido");
            return null;
        }

        Product pr = null;
        try {
            pr = this.products[index].clone();
        } catch (CloneNotSupportedException cnse) {
            System.out.println("Error => " + cnse);
        }
        return pr;
    }

    public Product[] getProducts() {
        Product[] list = new Product[3];
        try {
            list[0] = this.products[0].clone();
            list[1] = this.products[1].clone();
            list[2] = this.products[2].clone();
        } catch (CloneNotSupportedException cnse) {
            System.out.println("Error => " + cnse);
        }
        return list;
    }
}
