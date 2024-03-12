package org.iesvdm.clubjava.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.clubjava.domain.User;
import org.iesvdm.clubjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/users")
public class UserController {

    // Injects
    @Autowired
    private UserService userService;

    // Get all users
    @GetMapping({"", "/"})
    public List<User> all(){
        log.info("accediendo a todos los usuarios");
        return this.userService.all();
    }

    // Create a new user
    @PostMapping({"", "/"})
    public User newUser(@RequestBody User user){
        log.info("guardando usuario: " + user.getUsername());
        return this.userService.save(user);
    }

    // Get a user by id
    @GetMapping("/{id}")
    public User one(@PathVariable Long id){
        log.info("accediendo a usuario con id: " + id);
        return this.userService.one(id);
    }

    // Update a user
    @PutMapping("/{id}")
    public User replaceUser(@PathVariable Long id, @RequestBody User newUser){
        log.info("actualizando usuario con id: " + id);
        return this.userService.replace(id, newUser);
    }

    // Delete a user
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        log.info("borrando usuario con id: " + id);
        this.userService.delete(id);
    }
}
