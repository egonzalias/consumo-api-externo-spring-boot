package com.wolox.challenge.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.wolox.challenge.engine.AlbumInformationProcessor;

@Component
public class ApiBuilder  {
	
	@Autowired
	private CamelBuilder processor;
	
	@SuppressWarnings("deprecation")
	public String sendRequest(String url) throws Exception {
		String response = null;
		//CamelContext context = new DefaultCamelContext();
		CamelContext context = processor.getGetCamelContext();
	    try {
	    	
	        ProducerTemplate template = context.createProducerTemplate();
	        //context.start();

	        Exchange exchange = template
	                .request(url,
	                        new Processor() {
	                            public void process(Exchange exchange)
	                                    throws Exception {
	                            }
	                        });

	        if (exchange != null) {
	        	int responseCode = exchange.getOut().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
	        	if(responseCode == HttpStatus.OK.value()) {
	        		response = exchange.getOut().getBody(String.class);
	        	}
	        }
	        //Thread.sleep(1000 * 1);
	    } catch (Exception ex) {
	        System.out.println("Exception: " + ex);
	    }finally {
	    	/*context.stop();
	    	context.close();*/
		}
	    return response;
	}

}
