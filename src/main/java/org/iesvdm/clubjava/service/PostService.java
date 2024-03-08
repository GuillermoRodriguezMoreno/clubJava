package org.iesvdm.clubjava.service;

import org.iesvdm.clubjava.domain.Post;
import org.iesvdm.clubjava.exception.EntityNotFoundException;
import org.iesvdm.clubjava.repository.CommentRepository;
import org.iesvdm.clubjava.repository.PostRepository;
import org.iesvdm.clubjava.repository.TagRepository;
import org.iesvdm.clubjava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            // Actualizo post. Solo editable titulo, cuerpo y fecha
            oldPost.setTitle(newPost.getTitle());
            oldPost.setBody(newPost.getBody());
            oldPost.setPostDate(newPost.getPostDate());
            // Borro el post en los tags
            oldPost.getTags().stream().map(tag -> {
                tag.getPosts().remove(oldPost);
                tagRepository.save(tag);
                return tag;
            });
            // Añado el post a las nuevas tags
            newPost.getTags().stream().map(tag -> {
                tag.getPosts().add(oldPost);
                tagRepository.save(tag);
                return tag;
            });
            // Actualizo tags
            oldPost.setTags(newPost.getTags());
            // Guardo post
            return this.postRepository.save(oldPost);
        }).orElseThrow(()-> new EntityNotFoundException(id, Post.class));
    }

    // Delete a post
    public void delete(Long id){
        // Busco post por id
        this.postRepository.findById(id).map(post -> {
            // Borro los comentarios del post
            post.getComments().stream().map(comment -> {
                commentRepository.delete(comment);
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

    /* ******** PAGINATION ******** */

    public Map<String, Object> allWithPagination(int page, int size){
        Pageable paginado = PageRequest.of(page, size, Sort.by("tittle").ascending());
        Page<Post> pageAll = this.postRepository.findAll(paginado);
        Map<String, Object> response = new HashMap<>();
        response.put("posts", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());
        return response;
    }

    /* ******** FILTERS ******** */

    // Find post by title ordered by post date
    public List<Post> findByTittle(String title){
        return this.postRepository.findAllByTitleContainingIgnoreCaseOrderByPostDate(title);
    }

    // Find post by tag ordered by post date
    public List<Post> findByTag(String tag){
        return this.postRepository.findAllByTagsNameContainingIgnoreCaseOrderByPostDate(tag);
    }
}
