package org.iesvdm.clubjava.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "publicacion")
@Entity
public class Publicacion {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String titulo;
    private String contenido;
    @JsonFormat(pattern = "yyyy-MM-dd-HH:mm:ss",  shape = JsonFormat.Shape.STRING)
    private Date fecha_publicacion;
    // Relaciones
    @ManyToOne()
    private Usuario autor;
    @OneToMany(mappedBy = "publicacion")
    private Set<Comentario> comentarios = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "publicacion_etiqueta",
            joinColumns = @JoinColumn(name = "publicacion_id"),
            inverseJoinColumns = @JoinColumn(name = "etiqueta_id"))
    private Set<Etiqueta> etiquetas = new HashSet<>();
}
