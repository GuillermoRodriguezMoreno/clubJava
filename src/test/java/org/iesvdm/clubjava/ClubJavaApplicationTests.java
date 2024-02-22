package org.iesvdm.clubjava;

import org.iesvdm.clubjava.domain.Comment;
import org.iesvdm.clubjava.domain.Post;
import org.iesvdm.clubjava.domain.Tag;
import org.iesvdm.clubjava.domain.User;
import org.iesvdm.clubjava.service.CommentService;
import org.iesvdm.clubjava.service.PostService;
import org.iesvdm.clubjava.service.TagService;
import org.iesvdm.clubjava.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashSet;

@SpringBootTest
class ClubJavaApplicationTests {

    // Injects
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;
    @Autowired
    private TagService tagService;

    // Test
    @Test
    void contextLoads() {
    }

    @Test
    void cargaDatos(){
        /* Create data */

        // Users
        User user = new User(0L, "administrador", "administrador", "administrador@administrador.com", "administrador", new HashSet<>(), new HashSet<>());
        User user2 = new User(0L, "lector", "lector", "lector@lector.com", "lector", new HashSet<>(), new HashSet<>());
        User user3 = new User(0L, "autor", "autor", "autor@autor.com", "autor", new HashSet<>(), new HashSet<>());

        this.userService.save(user);
        this.userService.save(user2);
        this.userService.save(user3);

        // Posts
        Post post = new Post(0L, "Post 1", "Cuerpo del post 1", new Date(), null, new HashSet<>(), new HashSet<>());
        Post post2 = new Post(0L, "Post 2", "Cuerpo del post 2", new Date(), null, new HashSet<>(), new HashSet<>());
        Post post3 = new Post(0L, "Post 3", "Cuerpo del post 3", new Date(), null, new HashSet<>(), new HashSet<>());

        this.postService.save(post);
        this.postService.save(post2);
        this.postService.save(post3);

        // Comments
        Comment comment = new Comment(0L, "Comentario 1", new Date(), null, null);
        Comment comment2 = new Comment(0L, "Comentario 2", new Date(), null, null);

        this.commentService.save(comment);
        this.commentService.save(comment2);

        // Tags
        Tag tag = new Tag(0L, "Tag 1", new HashSet<>());
        Tag tag2 = new Tag(0L, "Tag 2", new HashSet<>());

        this.tagService.save(tag);
        this.tagService.save(tag2);

        /* Set relationships */

        // User - Post
        user.getPosts().add(post);
        user.getPosts().add(post2);
        user2.getPosts().add(post3);
        post.setAuthor(user);
        post2.setAuthor(user);
        post3.setAuthor(user2);

        // Post - Comment
        post.getComments().add(comment);
        post.getComments().add(comment2);
        comment.setPost(post);
        comment2.setPost(post);

        // Post - Tag
        post.getTags().add(tag);
        post.getTags().add(tag2);
        tag.getPosts().add(post);
        tag2.getPosts().add(post);

        // Comment - User
        comment.setAuthor(user);
        comment2.setAuthor(user2);
        user.getComments().add(comment);
        user2.getComments().add(comment2);

        // Save data
        this.userService.save(user);
        this.userService.save(user2);
        this.userService.save(user3);
        this.postService.save(post);
        this.postService.save(post2);
        this.postService.save(post3);
        this.commentService.save(comment);
        this.commentService.save(comment2);
        this.tagService.save(tag);
        this.tagService.save(tag2);
    }
}
