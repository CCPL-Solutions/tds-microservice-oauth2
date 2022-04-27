package co.edu.ierdminayticha.sgd.oauth.service;

import co.edu.ierdminayticha.sgd.oauth.dto.UserDto;

public interface IUserService {

	UserDto findByUsername(String username);

}
