package com.endes.entidad;

import java.util.Objects;

/**
 * Representa un producto con nombre y precio.
 * 
 * @author TuNombre
 */
public class Product {

    private Long id;
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

    /**
     * Constructor con ID, nombre y precio.
     * 
     * @param id    Identificador único del producto.
     * @param name  Nombre del producto.
     * @param price Precio del producto.
     */
    public Product(Long id, String name, double price) {
        this(name, price); // Llama al constructor principal
        if (id != null && id < 0) {
            throw new IllegalArgumentException("Error: ID no puede ser negativo");
        }
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id != null && id < 0) {
            throw new IllegalArgumentException("Error: ID no puede ser negativo");
        }
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                Objects.equals(id, product.id) &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @Override
    public String toString() {
        return "Product{id=" + (id != null ? id : "null") + ", name='" + name + "', price=" + price + "}";
    }
}
