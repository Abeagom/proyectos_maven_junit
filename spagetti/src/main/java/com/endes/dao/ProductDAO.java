package com.endes.dao;

import com.endes.entidad.Product;
import com.endes.exception.ProductNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la tabla "product". Maneja las operaciones CRUD con la base de datos.
 * Versión sin logs externos; usa System.out.println / System.err.println.
 */
public class ProductDAO {

    /**
     * Crea la tabla "product" si no existe.
     */
    public void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS product ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL, "
                + "price DOUBLE NOT NULL"
                + ");";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Tabla 'product' creada o verificada.");

        } catch (SQLException e) {
            System.err.println("Error al crear la tabla: " + e.getMessage());
        }
    }

    /**
     * Inserta un nuevo producto en la base de datos.
     *
     * @param product Producto a insertar.
     */
    public void insertProduct(Product product) {
        String sql = "INSERT INTO product(name, price) VALUES (?, ?)";
        // try-with-resources cierra automáticamente la conexión y el statement
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.executeUpdate();

            System.out.println("Producto insertado: " + product);

        } catch (SQLException e) {
            System.err.println("Error al insertar producto: " + e.getMessage());
        }
    }

    /**
     * Retorna todos los productos de la tabla.
     * @return Lista de objetos Product.
     */
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT name, price FROM product";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                productList.add(new Product(name, price));
            }
            System.out.println("Se han obtenido " + productList.size() + " productos.");

        } catch (SQLException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
        }
        return productList;
    }

    /**
     * Busca un producto por nombre (ignora mayúsculas/minúsculas).
     * @param searchName Nombre a buscar.
     * @return Product encontrado.
     * @throws ProductNotFoundException si el producto no existe.
     */
    public Product findByName(String searchName) throws ProductNotFoundException {
        String sql = "SELECT name, price FROM product WHERE LOWER(name) = LOWER(?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, searchName);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    return new Product(name, price);
                } else {
                    throw new ProductNotFoundException("No se encontró el producto: " + searchName);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar producto: " + e.getMessage());
            throw new ProductNotFoundException("Error interno al buscar el producto: " + e.getMessage());
        }
    }

    /**
     * Actualiza el precio de un producto, buscándolo por su nombre (ignora mayúsculas).
     * @param name      Nombre del producto
     * @param newPrice  Nuevo precio a asignar
     * @throws ProductNotFoundException si no existe un producto con ese nombre
     */
    public void updatePriceByName(String name, double newPrice) throws ProductNotFoundException {
        String sql = "UPDATE product SET price = ? WHERE LOWER(name) = LOWER(?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, newPrice);
            pstmt.setString(2, name);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new ProductNotFoundException("No se encontró producto con nombre: " + name);
            }
            System.out.println("Precio actualizado correctamente para " + name);

        } catch (SQLException e) {
            System.err.println("Error al actualizar precio: " + e.getMessage());
            throw new ProductNotFoundException("Error al actualizar precio: " + e.getMessage());
        }
    }

    /**
     * Elimina un producto buscándolo por nombre (ignora mayúsculas).
     * @param name Nombre del producto
     * @throws ProductNotFoundException si no existe un producto con ese nombre
     */
    public void deleteByName(String name) throws ProductNotFoundException {
        String sql = "DELETE FROM product WHERE LOWER(name) = LOWER(?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new ProductNotFoundException("No se encontró producto con nombre: " + name);
            }
            System.out.println("Producto eliminado: " + name);

        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
            throw new ProductNotFoundException("Error interno al eliminar producto: " + e.getMessage());
        }
    }

    /**
     * Cierra la conexión (opcional). Normalmente se llama cuando
     * ya no se requiere realizar más operaciones en la base de datos.
     */
    public void close() {
        DatabaseConnection.getInstance().closeConnection();
    }
    
    public void deleteAll() {
        String sql = "DELETE FROM product";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar todos los productos: " + e.getMessage(), e);
        }
    }
}
