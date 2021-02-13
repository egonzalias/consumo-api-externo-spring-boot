package com.wolox.challenge.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable{

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
    
    /*private class Address{
    	private String street;
        private String city;
        private String zipcode;
    }
    
    private class Company{
    	private String name;
        private String catchPhrase;
        private String bs;
    }*/
    
}
