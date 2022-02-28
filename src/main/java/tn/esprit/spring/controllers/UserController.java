package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.ExpertSpec;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.service.IUserService;




@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	IUserService UserService;
	
	@GetMapping("/retrieve-all-User")
	@ResponseBody
	public List<User> getUser() {
	List<User> listUser = UserService.retrieveAllUser();
	return listUser;
	}

	@GetMapping("/retrieve-User/{User-id}")
	@ResponseBody
	public User retrieveUser(@PathVariable("User-id") int UserId) {
	return UserService.retrieveUser(UserId);
	}

	@PostMapping("/add-User")
	@ResponseBody
	public User AddUser(@RequestBody User c)
	{
		User User = UserService.addUser(c);
	return User;
	}


	@DeleteMapping("/remove-User/{User-id}")
	@ResponseBody
	public void removeUser(@PathVariable("User-id") int UserId) {
		UserService.deleteUser(UserId);
	}


	@PutMapping("/modify-User")
	@ResponseBody
	public User modifyUser(@RequestBody User User) {
	return UserService.updateUser(User);
	}
	
	@GetMapping("/liste-expertbyspeciality/{speciality}")
	List<User> listeDeexpertParspecialite(@PathVariable("speciality") ExpertSpec spec){
		return UserService.listeDeUserParexpertspeciality(spec);
	}



}
