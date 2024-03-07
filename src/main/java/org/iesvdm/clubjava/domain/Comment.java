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
    private String body;
    @JsonFormat(pattern = "dd-MM-yyyy-HH:mm",  shape = JsonFormat.Shape.STRING)
    private Date commentDate;
    // Relationships
    @JsonIgnore
    @ManyToOne()
    private User author;
    @JsonIgnore
    @ManyToOne()
    private Post post;
}
