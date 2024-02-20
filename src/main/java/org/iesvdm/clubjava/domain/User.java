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
    private HashSet<Comment> comments = new HashSet<>();
    @OneToMany(mappedBy = "author")
    private HashSet<Post> posts = new HashSet<>();
}
