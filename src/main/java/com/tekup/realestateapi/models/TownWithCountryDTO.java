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
public class TownWithCountryDTO {
	private Long  idTown;
	private String name;
	private Long countryId;
	private String countryName;
	 


}
