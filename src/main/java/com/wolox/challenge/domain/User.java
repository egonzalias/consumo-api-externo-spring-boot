package com.wolox.challenge.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    
    private class address{
    	private String street;
        private String city;
        private String zipcode;
    }
    
    private class company{
    	private String name;
        private String catchPhrase;
        private String bs;
    }
    
}
