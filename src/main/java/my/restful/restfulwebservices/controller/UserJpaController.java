package my.restful.restfulwebservices.controller;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import my.restful.restfulwebservices.exception.UserNotFoundException;
import my.restful.restfulwebservices.model.User;
import my.restful.restfulwebservices.repository.UsersRepository;

@RestController
public class UserJpaController {
	
	@Autowired
	private UsersRepository usersRepository;

	@GetMapping(path="/jpa/users")
	public List<User> retrieveAllUser(){
		return usersRepository.findAll();
	}
	
	@GetMapping(value="/jpa/users/{id}")
	public User retrieveUser(@PathVariable int id){
		Optional<User> user = usersRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		}
		return user.get();
	}
	
	@GetMapping(path="/jpa/husers/{id}")
	public EntityModel<User>  retrieveHaetoasUser(@PathVariable int id){
		Optional<User> user = usersRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		}
		EntityModel<User> entityModel = new EntityModel<User>(user.get());
		@SuppressWarnings("deprecation")
		Link link = linkTo(methodOn(this.getClass()).retrieveAllUser()).withRel("all-users");
		entityModel.add(link);
		return entityModel;
	}
	
	@PostMapping(path="/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User createdUser = usersRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping(path="/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		usersRepository.deleteById(id);
	}
}
