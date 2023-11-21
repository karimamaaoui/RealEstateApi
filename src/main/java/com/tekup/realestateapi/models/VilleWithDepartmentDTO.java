package com.tekup.realestateapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VilleWithDepartmentDTO {

	  private Long idVille;
	    private String name;
	    private Long idPays; 
}
