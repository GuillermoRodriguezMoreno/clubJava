package org.iesvdm.clubjava.service;

import org.iesvdm.clubjava.domain.User;
import org.iesvdm.clubjava.exception.EntityNotFoundException;
import org.iesvdm.clubjava.repository.CommentRepository;
import org.iesvdm.clubjava.repository.PostRepository;
import org.iesvdm.clubjava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    // Injects
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MailSenderService mailSenderService;

    // List all users
    public List<User> all(){
        return this.userRepository.findAll();
    }

    // Find user by id
    public User one(Long id){
        return this.userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id, User.class));
    }

    // Create a new user
    public User save(User user){
        // Notificar por email al crear usuario
        mailSenderService.notificarPorEmail(user, "Hola " + user.getUsername() +
                ", bienvenido al Club de Java!");
        return this.userRepository.save(user);
    }

    // Update a user
    public User replace(Long id, User newUser){
        // Busco usuario por id
        return this.userRepository.findById(id).map(oldUser -> {
            // Actualizo usuario
            oldUser.setUsername(newUser.getUsername());
            oldUser.setPassword(newUser.getPassword());
            oldUser.setEmail(newUser.getEmail());
            oldUser.setRole(newUser.getRole());
            // Guardo usuario
            return this.userRepository.save(oldUser);
        }).orElseThrow(()-> new EntityNotFoundException(id, User.class));
    }

    // Delete a user
    public void delete(Long id){
        // Busco usuario por id
        this.userRepository.findById(id).map(user -> {
            // Recorro los comentarios y los pongo a null
            user.getComments().stream().map(comment -> {
                comment.setAuthor(null);
                return comment;
                // Guardo los comentarios
            }).forEach(this.commentRepository::save);
            // Recorro los posts y los pongo a null
            user.getPosts().stream().map(post -> {
                post.setAuthor(null);
                return post;
                // Guardo los posts
            }).forEach(this.postRepository::save);
            // Borro el usuario
            this.userRepository.deleteById(user.getId());
            return user;
        }).orElseThrow(() -> new EntityNotFoundException(id, User.class));
    }
}
