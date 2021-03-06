package com.wolox.challenge.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
    private String username;
    private String email;
    private AddressDTO address;
    private String phone;
    private String website;
    /*private String address;
    private String company;*/
    private CompanyDTO company;


}
