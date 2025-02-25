package com.endes.entidad;

/**
 * Representa un producto con nombre y precio.
 * 
 * @author TuNombre
 */
public class Product {

    private String name;
    private double price;

    /**
     * Constructor de producto.
     * 
     * @param name  El nombre no puede estar vacío o nulo.
     * @param price El precio debe ser mayor o igual que 0.
     */
    public Product(String name, double price) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: Nombre inválido");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Error: Precio negativo");
        }
        this.name = name;
        this.price = price;
    }

    public Product() {
        // Constructor vacío para posibles frameworks de serialización, etc.
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: Nombre inválido");
        }
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Error: Precio negativo");
        }
        this.price = price;
    }
    
    

    @Override
    public String toString() {
        return "Product [name=" + name + ", price=" + price + "]";
    }
}
