package org.capgemini.carmen.demo.webservice.controllers;

import org.capgemini.carmen.demo.webservice.model.User;
import org.capgemini.carmen.demo.webservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;

@Controller
@RequestMapping(path="/demo")
public class MainController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping(path="/add")
	public @ResponseBody String addNewUser (@RequestParam String name
			, @RequestParam String email) {

		User user = new User();
		user.setName(name);
		user.setEmail(email);
		userRepository.save(user);

		return user.toString();
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

    @GetMapping(path="/delete")
    public @ResponseBody String deleteUser (@RequestParam String name
            , @RequestParam String email) {
        Iterable<User> allUsers = getAllUsers();
        if (allUsers != null) {
            for(User user : allUsers) {
                System.out.println("============================================");
                System.out.println(user.getName() + ", " + user.getEmail());
                System.out.println("============================================");

                if(user.getName().equals(name) &&
                        (user.getEmail().equals(email))) {
                    userRepository.delete(user);
                }
            }
        }

        return "User deleted";
    }

    
}
