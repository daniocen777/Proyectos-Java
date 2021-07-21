package com.danicode.objects;

public abstract class Product implements Cloneable {

    private String name;
    private int amount;
    private double price;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    // Clone => Para no modificar el arreglo de productos (database) por defecto
    @Override
    public Product clone() throws CloneNotSupportedException {
        return (Product) super.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        if (amount < 0) {
            System.out.println("No puede asignar una cantidad negativa");
            return;
        }
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            System.out.println("No puede asignar un precio negativa");
            return;
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "name=" + name + ", amount=" + amount + ", price=" + price + '}';
    }
}
