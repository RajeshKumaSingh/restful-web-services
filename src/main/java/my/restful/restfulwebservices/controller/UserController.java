package my.restful.restfulwebservices.controller;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import my.restful.restfulwebservices.dao.UsersDaoService;
import my.restful.restfulwebservices.exception.UserNotFoundException;
import my.restful.restfulwebservices.model.User;

@SuppressWarnings("deprecation")
@RestController
public class UserController {
	
	@Autowired
	private UsersDaoService userService;

	// retrieve All user
	// GET  users
	@GetMapping(path="/users")
	public List<User> retrieveAllUser(){
		return userService.findAll();
	}
	
	// retrieve user
	// GET user/{id}
	@GetMapping(path="/users/{id}")
	public User retrieveUser(@PathVariable int id){
		User user = userService.findOne(id);
		if(user==null) throw new UserNotFoundException("id-"+id);
		return user;
	}
	
	// retrieve user
	// GET user/{id}
	// HATEOAS
	@GetMapping(path="/husers/{id}")
	public EntityModel<User>  retrieveHaetoasUser(@PathVariable int id){
		User user = userService.findOne(id);
		if(user==null) throw new UserNotFoundException("id-"+id);
		
		EntityModel<User> entityModel = new EntityModel<User>(user);
		Link link = linkTo(methodOn(this.getClass()).retrieveAllUser()).withRel("all-users");
		entityModel.add(link);
		return entityModel;
	}
	
	// Input -> Details of User
	// Output -> Create and return uRI of created resource
	// @Valid -> javax validation
	@PostMapping(path="/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User createdUser = userService.save(user);
		
		// Create URI for saved user
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();
		
		// Http-staus as created
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping(path="/users/{id}")
	public void deleteUser(@PathVariable int id) {
		
		if(!userService.deleteById(id)) {
			throw new UserNotFoundException("id-"+id);
		}
		
		// Http-staus as success
		
	}
}
