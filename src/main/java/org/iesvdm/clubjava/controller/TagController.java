package org.iesvdm.clubjava.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.clubjava.domain.Tag;
import org.iesvdm.clubjava.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/tags")
public class TagController {

    // Injects
    @Autowired
    private TagService tagService;

    // Get all tags
    @GetMapping({"", "/"})
    public List<Tag> all(){
        log.info("accediendo a todas las tags");
        return this.tagService.all();
    }

    // Create a new tag
    @PostMapping({"", "/"})
    public Tag newTag(@RequestBody Tag tag){
        log.info("guardando tag: " + tag.getName());
        return this.tagService.save(tag);
    }

    // Get a tag by id
    @GetMapping("/{id}")
    public Tag one(@PathVariable Long id){
        log.info("accediendo a tag con id: " + id);
        return this.tagService.one(id);
    }

    // Update a tag
    @PutMapping("/{id}")
    public Tag replaceTag(@PathVariable Long id, @RequestBody Tag newTag){
        log.info("actualizando tag con id: " + id);
        return this.tagService.replace(id, newTag);
    }

    // Delete a tag
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable Long id){
        log.info("borrando tag con id: " + id);
        this.tagService.delete(id);
    }
}
