package my.restful.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.restful.restfulwebservices.model.User;

public interface UsersRepository extends JpaRepository<User, Integer>{
	
}
