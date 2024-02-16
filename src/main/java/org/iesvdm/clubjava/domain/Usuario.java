package org.iesvdm.clubjava.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "usuario")
@Entity
public class Usuario {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String nombre_usuario;
    private String contrasena;
    private String coreo_electronico;
    private String rol;
    // Relaciones
    @OneToMany(mappedBy = "autor")
    private HashSet<Comentario> comentarios;
    @OneToMany(mappedBy = "autor")
    private HashSet<Publicacion> publicaciones;
}
