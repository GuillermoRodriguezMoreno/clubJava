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
        User user = new User(0L, "Guille", "administrador", "administrador@administrador.com", "administrador", new HashSet<>(), new HashSet<>());
        User user2 = new User(0L, "Matti", "lector", "lector@lector.com", "lector", new HashSet<>(), new HashSet<>());
        User user3 = new User(0L, "Hector", "autor", "autor@autor.com", "autor", new HashSet<>(), new HashSet<>());

        this.userService.save(user);
        this.userService.save(user2);
        this.userService.save(user3);

        // Posts
        Post post = new Post(0L, "Post 1: Explorando la Elegancia de Java: Records y CompletableFuture", "En el fascinante universo de Java, dos características brillan con luz propia: los Records y CompletableFuture. " +
                "Los Records han llegado para liberarnos del código boilerplate, ofreciendo una sintaxis concisa para la creación de clases inmutables. Con una simple declaración, obtenemos automáticamente " +
                "métodos como toString(), equals() y hashCode(). Por otro lado, CompletableFuture nos sumerge en el poder de la programación asíncrona de manera clara y elegante. Manejar tareas asíncronas " +
                "nunca fue tan sencillo, y con esta clase, podemos desbloquear el potencial de operaciones concurrentes de manera eficiente. ¡Acompáñanos en este breve viaje donde descubriremos cómo estas " +
                "características elevan la programación en Java a nuevas alturas de elegancia y eficiencia!",
                new Date(), null, new HashSet<>(), new HashSet<>());

        Post post2 = new Post(0L, "Post 2: Dominando el Envío de Emails con Spring Boot: Una Guía Práctica", "En el vasto panorama de desarrollo con Spring Boot, el envío de correos electrónicos " +
                "se vuelve esencial. En este artículo, exploraremos cómo integrar y aprovechar las capacidades de Spring Boot para gestionar fácilmente el envío de emails en nuestras aplicaciones. Desde " +
                "la configuración básica hasta la implementación de plantillas de correo electrónico personalizadas, descubriremos los pasos clave para construir una sólida funcionalidad de envío de emails. " +
                "Utilizaremos el módulo spring-boot-starter-mail y aprenderemos a enviar correos de manera síncrona y asíncrona. Además, abordaremos las mejores prácticas de seguridad, como la gestión de " +
                "credenciales y la prevención de abusos. Prepárate para desbloquear el potencial completo de Spring Boot y llevar tus capacidades de envío de correos electrónicos al siguiente nivel. " +
                "¡Vamos a sumergirnos en el fascinante mundo de la comunicación por email con Spring Boot!"
                , new Date(), null, new HashSet<>(), new HashSet<>());

        Post post3 = new Post(0L, "Post 3: Explorando la Potencia de los Streams en Java: Un Viaje a la Elegancia Funcional", "En el corazón de la programación funcional en Java, " +
                "los streams emergen como una herramienta poderosa para transformar la manera en que manipulamos y procesamos datos. En este artículo, nos sumergiremos en el fascinante mundo de los " +
                "streams de Java, explorando cómo esta característica nos permite escribir código más conciso, legible y eficiente. Desde las operaciones básicas como filter y map, hasta conceptos " +
                "avanzados como flatMap y reduce, desentrañaremos los secretos de los streams y cómo pueden simplificar tareas complejas. Aprenderemos a utilizar expresiones lambda en conjunto con " +
                "streams para lograr un estilo de programación más declarativo y funcional. Además, exploraremos casos prácticos de uso, desde la manipulación de colecciones hasta la manipulación de " +
                "datos en tiempo real. ¡Acompáñanos en este emocionante viaje hacia la elegancia funcional con los streams de Java!"
                , new Date(), null, new HashSet<>(), new HashSet<>());

        this.postService.save(post);
        this.postService.save(post2);
        this.postService.save(post3);

        // Comments
        Comment comment = new Comment(0L, "Es demasiado complejo!", new Date(), null, null);
        Comment comment2 = new Comment(0L, "Que va, si es to facil!", new Date(), null, null);

        this.commentService.save(comment);
        this.commentService.save(comment2);

        // Tags
        Tag tag = new Tag(0L, "Spring Boot", new HashSet<>());
        Tag tag2 = new Tag(0L, "Streams", new HashSet<>());

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
