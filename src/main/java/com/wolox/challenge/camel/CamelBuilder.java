package com.wolox.challenge.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;

public class CamelBuilder extends RouteBuilder{

	private CamelContext getCamelContext;
	
	@Override
	public void configure() throws Exception {
		getCamelContext = getContext();
	}
	
	public CamelContext getGetCamelContext() {
		return getCamelContext;
	}

	public void setGetCamelContext(CamelContext getCamelContext) {
		this.getCamelContext = getCamelContext;
	}

}
