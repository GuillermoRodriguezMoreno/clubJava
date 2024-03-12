package org.iesvdm.clubjava.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.clubjava.domain.Post;
import org.iesvdm.clubjava.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/posts")
public class PostController {

    // Injects
    @Autowired
    private PostService postService;

    /* ******* CRUD *******/

    // Get all posts
    @GetMapping(value = {"", "/"}, params = {"!search", "!tag", "!page", "!size"})
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

    /* ******* PAGINATION *******/

    // Get all posts with pagination
    @GetMapping(value = {"", "/"}, params = {"page", "size"})
    public ResponseEntity<Map<String, Object>> allWithPagination(@RequestParam (value = "page", defaultValue = "0") int page,
                                                                 @RequestParam (value = "size", defaultValue = "3") int size){
        log.info("accediendo a todos los posts con paginacion");
        Map<String, Object> responseAll = this.postService.allWithPagination(page, size);
        return ResponseEntity.ok(responseAll);
    }

    /* ******* FILTERS *******/

    // Get all posts by tittle
    @GetMapping(value = {"", "/"}, params = "search")
    public List<Post> searchByTittle(@RequestParam String search){
        log.info("buscando posts con titulo: " + search);
        return this.postService.findByTittle(search);
    }

    // Get all posts by tag
    @GetMapping(value = {"", "/"}, params = "tag")
    public List<Post> searchByTag(@RequestParam String tag){
        log.info("buscando posts con tag: " + tag);
        return this.postService.findByTag(tag);
    }
}
