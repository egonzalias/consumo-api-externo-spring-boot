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
    private String phone;
    private String website;
    private Address address;
    private Company company;
    
    private class Address{
    	private String street;
        private String city;
        private String zipcode;
        private Geo geo;
    }
    
    private class Company{
    	private String name;
        private String catchPhrase;
        private String bs;
    }
    
    private class Geo{
    	private String lng;
        private String lat;
    }
}
