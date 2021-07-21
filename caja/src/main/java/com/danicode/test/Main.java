package com.danicode.test;

import com.danicode.objects.Product;
import com.danicode.storage.Database;

public class Main {

    public static void main(String[] args) {
        Database database = new Database();
        Product product = database.getByIndex(0);
        System.out.println(product);
    }
}
