-- Crear la base de datos si no existe
DROP DATABASE IF EXISTS club_java;
CREATE DATABASE IF NOT EXISTS club_java;
USE club_java;

-- Crear la tabla Usuario
CREATE TABLE IF NOT EXISTS usuario (
                                       id INT PRIMARY KEY AUTO_INCREMENT,
                                       nombre_usuario VARCHAR(255) NOT NULL,
                                       correo_electronico VARCHAR(255) NOT NULL,
                                       contrasena VARCHAR(255) NOT NULL,
                                       rol VARCHAR(20) CHECK (rol IN ('administrador', 'autor', 'lector'))
);

-- Crear la tabla Publicacion
CREATE TABLE IF NOT EXISTS publicacion (
                                           id INT PRIMARY KEY AUTO_INCREMENT,
                                           titulo VARCHAR(255) NOT NULL,
                                           contenido TEXT NOT NULL,
                                           fecha_publicacion DATE NOT NULL,
                                           autor_id INT,
                                           FOREIGN KEY (autor_id) REFERENCES usuario(id)
);

-- Crear la tabla Comentario
CREATE TABLE IF NOT EXISTS comentario (
                                          id INT PRIMARY KEY AUTO_INCREMENT,
                                          contenido TEXT NOT NULL,
                                          fecha_comentario DATE NOT NULL,
                                          autor_id INT,
                                          publicacion_id INT,
                                          FOREIGN KEY (autor_id) REFERENCES usuario(id),
                                          FOREIGN KEY (publicacion_id) REFERENCES publicacion(id)
);

-- Crear la tabla Etiqueta
CREATE TABLE IF NOT EXISTS etiqueta (
                                        id INT PRIMARY KEY AUTO_INCREMENT,
                                        nombre VARCHAR(255) NOT NULL
);

-- Crear la tabla intermedia para la relación muchos a muchos entre Publicacion y Etiqueta
CREATE TABLE IF NOT EXISTS publicacion_etiqueta (
                                                    id INT PRIMARY KEY AUTO_INCREMENT,
                                                    publicacion_id INT,
                                                    etiqueta_id INT,
                                                    FOREIGN KEY (publicacion_id) REFERENCES publicacion(id),
                                                    FOREIGN KEY (etiqueta_id) REFERENCES etiqueta(id)
);
