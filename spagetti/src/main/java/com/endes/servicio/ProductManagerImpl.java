package com.endes.servicio;

import com.endes.dao.ProductDAO;
import com.endes.entidad.Product;
import com.endes.exception.ProductNotFoundException;
import java.util.List;

/**
 * Implementación de la lógica de negocio para gestionar productos.
 * Cumple con todos los métodos definidos en la interfaz ProductManager.
 */
public class ProductManagerImpl implements ProductManager {

    private final ProductDAO productDAO;

    /**
     * Constructor por defecto que inicializa el DAO.
     */
    public ProductManagerImpl() {
        this.productDAO = new ProductDAO();
    }
    
    /**
     * Constructor para pruebas que permite inyectar un mock de `ProductDAO`.
     */
    public ProductManagerImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    

    /**
     * Crea la tabla 'product' si no existe.
     */
    @Override
    public void crearTabla() {
        productDAO.crearTabla();
    }

    /**
     * Añade (Crea) un nuevo producto.
     * @param name  Nombre del producto.
     * @param price Precio del producto.
     */
    @Override
    public void addProduct(String name, double price) {
        // Se construye la entidad y se delega al DAO para insertarla
        Product nuevo = new Product(name, price);
        productDAO.insertProduct(nuevo);
    }

    /**
     * Lista (Lee) todos los productos.
     * @return Lista de productos.
     */
    @Override
    public List<Product> listProducts() {
        return productDAO.findAll();
    }

    /**
     * Busca (Lee) un producto por nombre.
     * @param searchName Nombre a buscar.
     * @return El producto encontrado.
     * @throws ProductNotFoundException Si no se encuentra el producto.
     */
    @Override
    public Product findProduct(String searchName) throws ProductNotFoundException {
        return productDAO.findByName(searchName);
    }

    /**
     * Actualiza (Update) el precio de un producto, buscándolo por su nombre.
     * @param name      Nombre del producto.
     * @param newPrice  Nuevo precio a asignar.
     * @throws ProductNotFoundException Si el producto no se encuentra.
     */
    @Override
    public void updateProductPrice(String name, double newPrice) throws ProductNotFoundException {
        productDAO.updatePriceByName(name, newPrice);
    }

    /**
     * Elimina (Delete) un producto de la base de datos, buscándolo por nombre.
     * @param name Nombre del producto.
     * @throws ProductNotFoundException Si el producto no se encuentra.
     */
    @Override
    public void deleteProduct(String name) throws ProductNotFoundException {
        productDAO.deleteByName(name);
    }
}
