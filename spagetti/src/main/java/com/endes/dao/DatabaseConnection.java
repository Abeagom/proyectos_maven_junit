package com.endes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton para la conexión a la base de datos SQLite con reconexión automática.
 */
public class DatabaseConnection {

    private static final String PATH = "/home/oem/Documentos/proyectos_git";
    private static final String URL = "jdbc:sqlite:" + PATH + "/proyectos_maven_junit/BBDD/spaghetti";
    
    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() {
        conectar();
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private void conectar() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(URL);
                System.out.println("Conexión establecida con la base de datos en: " + URL);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error al conectar a la base de datos: " + e.getMessage(), e);
        }
    }

    public Connection getConnection() {
        conectar();  // Asegurar que la conexión está abierta antes de retornarla
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Conexión cerrada.");
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }
}
