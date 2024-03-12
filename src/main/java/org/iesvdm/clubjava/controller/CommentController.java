package org.iesvdm.clubjava.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.clubjava.domain.Comment;
import org.iesvdm.clubjava.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/comments")
public class CommentController {

    // Injects
    @Autowired
    private CommentService commentService;

    // Get all comments
    @GetMapping({"", "/"})
    public List<Comment> all(){
        log.info("accediendo a todos los comentarios");
        return this.commentService.all();
    }

    // Create a new comment
    @PostMapping({"", "/"})
    public Comment newComment(@RequestBody Comment comment){
        log.info("guardando comentario: " + comment.getId());
        return this.commentService.save(comment);
    }

    // Get a comment by id

    @GetMapping("/{id}")
    public Comment one(@PathVariable Long id){
        log.info("accediendo a comentario con id: " + id);
        return this.commentService.one(id);
    }

    // Update a comment
    @PutMapping("/{id}")
    public Comment replaceComment(@PathVariable Long id, @RequestBody Comment newComment){
        log.info("actualizando comentario con id: " + id);
        return this.commentService.replace(id, newComment);
    }

    // Delete a comment
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id){
        log.info("borrando comentario con id: " + id);
        this.commentService.delete(id);
    }

    /* ********* FILTERS ********* */
    @GetMapping(value = {"", "/"}, params = {"postId"})
    public List<Comment> allByPostId(@RequestParam Long postId){
        log.info("accediendo a todos los comentarios del post con id: " + postId);
        return this.commentService.allByPostId(postId);
    }
}
