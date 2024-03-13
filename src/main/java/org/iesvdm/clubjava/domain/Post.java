package org.iesvdm.clubjava.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "post")
@Entity
public class Post {

    // Props
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String title;
    @Lob // Esto es para ajustar el tamaño del campo en la base de datos
    @Column(length = 10000) // Esto es para ajustar el tamaño del campo en la base de datos
    private String body;
    @JsonFormat(pattern = "dd-MM-yyyy",  shape = JsonFormat.Shape.STRING)
    private Date postDate = new Date();
    // Relationships
    @ManyToOne()
    private User author;
    @OneToMany(mappedBy = "post")
    @JsonIgnore
    @ToString.Exclude
    private Set<Comment> comments = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();
}
