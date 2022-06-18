package co.com.viveres.susy.microserviceoauth.security.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import co.com.viveres.susy.microserviceoauth.dto.UserDto;
import co.com.viveres.susy.microserviceoauth.service.IUserService;
import feign.FeignException;

@Component
public class AuthenticationSuccesErrorHandler implements AuthenticationEventPublisher {

	@Autowired
	private IUserService userService;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {

		if (authentication.getDetails() instanceof WebAuthenticationDetails) {
			return;
		}

		UserDetails user = (UserDetails) authentication.getPrincipal();
		System.out.println("Success: " + user.getUsername());
		
		String username = authentication.getName();
		UserDto userDto = this.userService.findByUsername(username);
		
		if (userDto.getAttempts() != null && userDto.getAttempts() > 0) {
			userDto.setAttempts(0);
			this.userService.update(userDto.getId(), userDto);
		}
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String username = authentication.getName();

		try {
			UserDto user = this.userService.findByUsername(username);
			if (user.getAttempts() == null) {
				user.setAttempts(0);
			}
			
			System.out.println("Intntos actual es de: " + user.getAttempts());
			
			user.setAttempts(user.getAttempts() + 1);
			
			System.out.println("Intntos despues es de: " + user.getAttempts());
			
			if (user.getAttempts() >=3) {
				System.out.println(String.format("Usuario %s deshabilitado por maximo de intentos", username));
				user.setEnabled(Boolean.FALSE);
			}
			
			this.userService.update(user.getId(), user);
		} catch (FeignException e) {
			System.out.println("El usuario no existe en el sistema: " + username);
		}
	}

}
