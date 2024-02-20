package org.iesvdm.clubjava.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.clubjava.domain.Post;
import org.iesvdm.clubjava.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/posts")
public class PostController {

    // Injects
    @Autowired
    private PostService postService;

    // Get all posts
    @GetMapping({"", "/"})
    public List<Post> all(){
        log.info("accediendo a todos los posts");
        return this.postService.all();
    }

    // Create a new post
    @PostMapping({"", "/"})
    public Post newPost(@RequestBody Post post){
        log.info("guardando post: " + post.getTitle());
        return this.postService.save(post);
    }

    // Get a post by id
    @GetMapping("/{id}")
    public Post one(@PathVariable Long id){
        log.info("accediendo a post con id: " + id);
        return this.postService.one(id);
    }

    // Update a post
    @PutMapping("/{id}")
    public Post replacePost(@PathVariable Long id, @RequestBody Post newPost){
        log.info("actualizando post con id: " + id);
        return this.postService.replace(id, newPost);
    }

    // Delete a post
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id){
        log.info("borrando post con id: " + id);
        this.postService.delete(id);
    }
}
