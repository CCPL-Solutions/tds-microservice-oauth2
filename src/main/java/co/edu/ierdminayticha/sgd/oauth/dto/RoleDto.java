package co.edu.ierdminayticha.sgd.oauth.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RoleDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private Long id;

	@NotBlank
	private String name;
}
