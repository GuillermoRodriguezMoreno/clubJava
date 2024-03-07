package org.iesvdm.clubjava.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "post")
@Entity
public class Post {

    // Props
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String title;
    private String body;
    @JsonFormat(pattern = "dd-MM-yyyy",  shape = JsonFormat.Shape.STRING)
    private Date postDate;
    // Relationships
    @JsonIgnore
    @ManyToOne()
    private User author;
    @OneToMany(mappedBy = "post")
    @JsonIgnore
    private Set<Comment> comments = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @JsonIgnore
    private Set<Tag> tags = new HashSet<>();
}
