package co.edu.ierdminayticha.sgd.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import co.edu.ierdminayticha.sgd.oauth.dto.UserDto;

@FeignClient(name = "microservice-user/v1/users")
public interface IUserFeignClient {
	
	@GetMapping(value = "/username/{username}")
	ResponseEntity<UserDto> findByUsername(@PathVariable String username);
}
