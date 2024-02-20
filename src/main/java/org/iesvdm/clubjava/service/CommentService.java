package org.iesvdm.clubjava.service;

import org.iesvdm.clubjava.domain.Comment;
import org.iesvdm.clubjava.exception.EntityNotFoundException;
import org.iesvdm.clubjava.repository.CommentRepository;
import org.iesvdm.clubjava.repository.PostRepository;
import org.iesvdm.clubjava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    // Injects
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    // List all comments
    public List<Comment> all(){
        return this.commentRepository.findAll();
    }

    // Find comment by id
    public Comment one(Long id){
        return this.commentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id, Comment.class));
    }

    // Create a new comment
    public Comment save(Comment comment){
        return this.commentRepository.save(comment);
    }

    // Update a comment
    public Comment replace(Long id, Comment newComment) {
        // Busco comentario por id
        return this.commentRepository.findById(id).map(old -> {
            // Actualizo comentario
            old.setBody(newComment.getBody());
            // Hago que la fecha del comentario sea la actual
            old.setCommentDate(new Date());
            // Guardo comentario
            return this.commentRepository.save(old);
        }).orElseThrow(() -> new EntityNotFoundException(id, Comment.class));
    }

    // Delete a comment
    public void delete(Long id){
        // Busco comentario por id
        this.commentRepository.findById(id).map(comment -> {
            // Borro el comentario en el post
            comment.getPost().getComments().remove(comment);
            // Borro el comentario en el usuario
            comment.getAuthor().getComments().remove(comment);
            // Guardo post y usuario
            this.postRepository.save(comment.getPost());
            this.userRepository.save(comment.getAuthor());
            // Borro comentario
            this.commentRepository.deleteById(id);
            return comment;
        }).orElseThrow(()-> new EntityNotFoundException(id, Comment.class));
    }
}
