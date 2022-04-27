package co.edu.ierdminayticha.sgd.oauth.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import co.edu.ierdminayticha.sgd.oauth.dto.UserDto;
import co.edu.ierdminayticha.sgd.oauth.service.IUserService;

@Component
public class AdditionalInformationToken implements TokenEnhancer {
	
	@Autowired
	private IUserService userService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		UserDto user = this.userService.findByUsername(authentication.getName());
		
		Map<String, Object> information = new HashMap<>();
		information.put("name", user.getName());
		information.put("lastName", user.getLastName());
		information.put("email", user.getEmail());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(information);
		
		return accessToken;
	}

}
