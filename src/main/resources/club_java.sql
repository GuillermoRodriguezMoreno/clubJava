-- Crear la base de datos si no existe
DROP DATABASE IF EXISTS club_java;
CREATE DATABASE IF NOT EXISTS club_java;
USE club_java;

-- Crear la tabla Usuario
CREATE TABLE IF NOT EXISTS "user" (
                                       id INT PRIMARY KEY AUTO_INCREMENT,
                                       user_name VARCHAR(255) NOT NULL,
                                       email VARCHAR(255) NOT NULL,
                                       password VARCHAR(255) NOT NULL,
                                       "role" VARCHAR(20) CHECK (rol IN ('administrador', 'autor', 'lector'))
);

-- Crear la tabla Publicacion
CREATE TABLE IF NOT EXISTS post (
                                           id INT PRIMARY KEY AUTO_INCREMENT,
                                           tittle VARCHAR(255) NOT NULL,
                                           body TEXT NOT NULL,
                                           post_date DATE NOT NULL,
                                           author_id INT,
                                           FOREIGN KEY (author_id) REFERENCES "user"(id)
);

-- Crear la tabla Comentario
CREATE TABLE IF NOT EXISTS comment (
                                          id INT PRIMARY KEY AUTO_INCREMENT,
                                          body TEXT NOT NULL,
                                          comment_date DATE NOT NULL,
                                          author_id INT,
                                          post_id INT,
                                          FOREIGN KEY (author_id) REFERENCES "user"(id),
                                          FOREIGN KEY (post_id) REFERENCES post(id)
);

-- Crear la tabla Etiqueta
CREATE TABLE IF NOT EXISTS tag (
                                        id INT PRIMARY KEY AUTO_INCREMENT,
                                        "name" VARCHAR(255) NOT NULL
);

-- Crear la tabla intermedia para la relación muchos a muchos entre Publicacion y Etiqueta
CREATE TABLE IF NOT EXISTS post_tag (
                                                    id INT PRIMARY KEY AUTO_INCREMENT,
                                                    post_id INT,
                                                    tag_id INT,
                                                    FOREIGN KEY (post_id) REFERENCES post(id),
                                                    FOREIGN KEY (tag_id) REFERENCES tag(id)
);
