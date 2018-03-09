package org.capgemini.carmen.demo.webservice.controllers;

import org.capgemini.carmen.demo.webservice.model.User;
import org.capgemini.carmen.demo.webservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
	public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email) {

		User user = new User();
		user.setName(name);
		user.setEmail(email);
		userRepository.save(user);

		return "Added: " + user.toString();
	}

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path="/all/{id}")
    public @ResponseBody User getUserById(@PathVariable("id") Long id) {
        User retrievedUser = new User();
        Iterable<User> allUsers = getAllUsers();
        if(allUsers != null) {
            for(User user : allUsers) {
                if(id == user.getId()) {
                    retrievedUser = user;
                }
            }
        }
        return retrievedUser;
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
    public @ResponseBody String updateUser(@RequestParam String name, @RequestParam String email) {
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

    /**
     *
     * @param name
     * @param email
     * @return
     */
    @GetMapping(path="/delete")
    public @ResponseBody String deleteUser (@RequestParam String name, @RequestParam String email) {
        User deletedUser = new User();
        Iterable<User> allUsers = getAllUsers();
        if (allUsers != null) {
            for(User user : allUsers) {
                if(user.getName().equals(name) &&
                        (user.getEmail().equals(email))) {
                    userRepository.delete(user);
                }
                deletedUser = user;
            }
        }

        return "Deleted: " + deletedUser.toString();
    }
}