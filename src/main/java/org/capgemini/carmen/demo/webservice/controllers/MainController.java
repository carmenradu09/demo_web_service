package org.capgemini.carmen.demo.webservice.controllers;

import org.capgemini.carmen.demo.webservice.model.User;
import org.capgemini.carmen.demo.webservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/demo")
public class MainController {
	@Autowired
	private UserRepository userRepository;


    /**
     *
     * @param name
     * @param email
     * @return
     */
    @GetMapping(path="/add")
    public @ResponseBody String addNewUser (@RequestParam("name") String name,
                                            @RequestParam("email") String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);

        return "Added: " + user.toString();
    }

    /**
     *
     * @param name
     * @param email
     * @return
     */
    @PostMapping(path="/adduser")
    public ResponseEntity addUser(@RequestParam("name") String name,
                                        @RequestParam("email") String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        User newUser = userRepository.save(user);

        return new ResponseEntity(newUser, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path="/all/{id}")
    public ResponseEntity getUserById(@PathVariable("id") Long id) {
        User retrieveUser = new User();
        Iterable<User> allUsers = getAllUsers();
        if(allUsers != null) {
            for (User user : allUsers) {
                if (user.getId().equals(id)) {
                    retrieveUser = user;
                }
            }
        }
        return new ResponseEntity(retrieveUser,HttpStatus.OK );
    }

    /**
     *
     * @return
     */
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

    /**
     *
     * @param name
     * @param email
     * @return
     */
    @GetMapping(path="/update")
    public @ResponseBody String updateUser(@RequestParam("name") String name,
                                           @RequestParam("email") String email) {
        User updatedUser = new User();
        Iterable<User> allUsers = getAllUsers();

        if (allUsers != null) {
            for(User user : allUsers) {
                if(user.getEmail().equals(email)) {
                    user.setName(name);
                    userRepository.save(user);
                }
                updatedUser = user;
            }
        }
        return "Updated: \n" + updatedUser.toString();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUserById(@PathVariable("id") Long id, @RequestParam("name") String name,
                                        @RequestParam("email") String email) {
        User updateUser = new User();
        Iterable<User> allUsers = getAllUsers();
        if(allUsers != null) {
            for(User user : allUsers) {
                if(user.getId().equals(id)) {
                    updateUser = user;
                    updateUser.setName(name);
                    updateUser.setEmail(email);
                    userRepository.save(updateUser);
                }
            }
        }
        return new ResponseEntity(updateUser, HttpStatus.OK);
    }


    /**
     *
     * @param name
     * @param email
     * @return
     */
    @DeleteMapping(path="/delete")
    public ResponseEntity deleteUser (@RequestParam String name, @RequestParam String email) {
        User deletedUser = new User();
        Iterable<User> allUsers = getAllUsers();
        if (allUsers != null) {
            for(User user : allUsers) {
                if(user.getName().equals(name) /*&&
                        (user.getEmail().equals(email))*/) {
                    deletedUser = user;
                    userRepository.delete(user);
                }
            }
        }
        return new ResponseEntity(deletedUser, HttpStatus.OK);
}

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id){
        User deleteUser = new User();
        Iterable<User> allUsers = getAllUsers();
        for(User user : allUsers) {
            if(user.getId().equals(id)) {
                deleteUser = user;
                userRepository.delete(user);
            }
        }
        return new ResponseEntity(deleteUser, HttpStatus.OK);
    }
}