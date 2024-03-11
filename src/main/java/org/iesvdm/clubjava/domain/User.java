package org.iesvdm.clubjava.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "user")
@Entity
public class User {

    // Props
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
    // Relationships
    @OneToMany(mappedBy = "author")
    @JsonIgnore
    @ToString.Exclude
    private Set<Comment> comments = new HashSet<>();
    @OneToMany(mappedBy = "author")
    @JsonIgnore
    @ToString.Exclude
    private Set<Post> posts = new HashSet<>();
}
