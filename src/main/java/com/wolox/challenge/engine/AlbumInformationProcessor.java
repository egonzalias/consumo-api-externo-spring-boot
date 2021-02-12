package com.wolox.challenge.engine;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Service;


public class AlbumInformationProcessor implements Processor{

	//private CamelContext getCamelContext;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		//getCamelContext = exchange.getContext();
		
		/*String in = exchange.getIn().getBody(String.class);
		String header = exchange.getIn().getHeader("TEST_HEAD").toString();
		System.out.println("Response:"+in);*/
	}

	/*public CamelContext getGetCamelContext() {
		return getCamelContext;
	}

	public void setGetCamelContext(CamelContext getCamelContext) {
		this.getCamelContext = getCamelContext;
	}*/

	
}
