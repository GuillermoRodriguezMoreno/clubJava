package org.iesvdm.clubjava.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Comment {

    // Props
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Lob // Esto es para ajustar el tamaño del campo en la base de datos
    @Column(length = 10000) // Esto es para ajustar el tamaño del campo en la base de datos
    private String body;
    @JsonFormat(pattern = "dd-MM-yyyy-HH:mm",  shape = JsonFormat.Shape.STRING)
    private Date commentDate = new Date();
    // Relationships
    @ManyToOne()
    private User author;
    @ManyToOne()
    private Post post;
}
