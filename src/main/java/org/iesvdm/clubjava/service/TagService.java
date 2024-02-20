package org.iesvdm.clubjava.service;

import org.iesvdm.clubjava.domain.Tag;
import org.iesvdm.clubjava.exception.EntityNotFoundException;
import org.iesvdm.clubjava.repository.PostRepository;
import org.iesvdm.clubjava.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    // Injects
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private PostRepository postRepository;

    // List all tags
    public List<Tag> all(){
        return this.tagRepository.findAll();
    }

    // Find tag by id
    public Tag one(Long id){
        return this.tagRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id, Tag.class));
    }

    // Create a new tag
    public Tag save(Tag tag){
        return this.tagRepository.save(tag);
    }

    // Update a tag
    public Tag replace(Long id, Tag newTag){
        // Busco tag por id
        return this.tagRepository.findById(id).map(oldTag -> {
            // Actualizo tag
            oldTag.setName(newTag.getName());
            // Guardo tag
            return this.tagRepository.save(oldTag);
        }).orElseThrow(()-> new EntityNotFoundException(id, Tag.class));
    }

    // Delete a tag
    public void delete(Long id){
        // Busco tag por id
        this.tagRepository.findById(id).map(tag -> {
            // Borro el tag en los posts
            tag.getPosts().stream().map(post -> {
                post.getTags().remove(tag);
                postRepository.save(post);
                return post;
            });
            // Borro tag
            this.tagRepository.delete(tag);
            return tag;
        }).orElseThrow(()-> new EntityNotFoundException(id, Tag.class));
    }
}
