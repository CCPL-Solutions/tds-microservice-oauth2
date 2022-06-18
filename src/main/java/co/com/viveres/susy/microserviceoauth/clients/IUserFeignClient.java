package co.com.viveres.susy.microserviceoauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import co.com.viveres.susy.microserviceoauth.dto.UserDto;

@FeignClient(name = "microservice-user/v1/users")
public interface IUserFeignClient {
	
	@GetMapping(value = "/username/{username}")
	ResponseEntity<UserDto> findByUsername(@PathVariable String username);
	
	@GetMapping(value = "/{user-id}")
	ResponseEntity<UserDto> findById(@PathVariable("user-id") Long id);
	
	@PutMapping(value = "/{user-id}")
	ResponseEntity<UserDto> update(@PathVariable("user-id") Long id, @RequestBody UserDto user);
}
