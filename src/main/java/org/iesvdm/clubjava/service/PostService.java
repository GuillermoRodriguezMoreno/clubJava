package org.iesvdm.clubjava.service;

import org.iesvdm.clubjava.domain.Post;
import org.iesvdm.clubjava.exception.EntityNotFoundException;
import org.iesvdm.clubjava.repository.CommentRepository;
import org.iesvdm.clubjava.repository.PostRepository;
import org.iesvdm.clubjava.repository.TagRepository;
import org.iesvdm.clubjava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {

    // Injects
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TagRepository tagRepository;

    // List all posts
    public List<Post> all(){
        return this.postRepository.findAll();
    }

    // Find post by id
    public Post one(Long id){
        return this.postRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id, Post.class));
    }

    // Create a new post
    public Post save(Post post){
        return this.postRepository.save(post);
    }

    // Update a post
    public Post replace(Long id, Post newPost){
        // Busco post por id
        return this.postRepository.findById(id).map(oldPost -> {
            // Actualizo post
            oldPost.setTitle(newPost.getTitle());
            oldPost.setBody(newPost.getBody());
            oldPost.setPostDate(newPost.getPostDate());
            oldPost.setAuthor(newPost.getAuthor());
            // Borro los tags del post
            oldPost.getTags().stream().map(tag -> {
                tag.getPosts().remove(oldPost);
                tagRepository.save(tag);
                return tag;
            });
            // Añado los nuevos tags al post
            newPost.getTags().stream().map(tag -> {
                tag.getPosts().add(oldPost);
                tagRepository.save(tag);
                return tag;
            });
            oldPost.setTags(newPost.getTags());
            // Guardo post
            return this.postRepository.save(oldPost);
        }).orElseThrow(()-> new EntityNotFoundException(id, Post.class));
    }

    // Delete a post
    public void delete(Long id){
        // Busco post por id
        this.postRepository.findById(id).map(post -> {
            // Borro el post en los comentarios
            post.getComments().stream().map(comment -> {
                comment.setPost(null);
                commentRepository.save(comment);
                return comment;
            });
            // Borro el post en los tags
            post.getTags().stream().map(tag -> {
                tag.getPosts().remove(post);
                tagRepository.save(tag);
                return tag;
            });
            // Borro el post en el usuario
            post.getAuthor().getPosts().remove(post);
            userRepository.save(post.getAuthor());
            //  Borro el post
            this.postRepository.delete(post);
            return post;
        }).orElseThrow(()-> new EntityNotFoundException(id, Post.class));
    }
}
