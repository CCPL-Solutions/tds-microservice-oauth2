package co.com.viveres.susy.microserviceoauth.service;

import co.com.viveres.susy.microserviceoauth.dto.UserDto;

public interface IUserService {

	UserDto findByUsername(String username);

}
