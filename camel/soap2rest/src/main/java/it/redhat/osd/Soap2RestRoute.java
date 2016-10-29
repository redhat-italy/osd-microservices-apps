/**
 *  Copyright 2005-2015 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package it.redhat.osd;

import javax.inject.Inject;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.cdi.Uri;

/**
 * Configures all our Camel routes, components, endpoints and beans
 */
@ContextName("Soap2RestRoute")
public class Soap2RestRoute extends RouteBuilder {


    @Inject
    @Uri("log:output")
    private Endpoint logEndpoint;
    
    @Inject
    @Uri("cxfrs://http://localhost:8080/?resourceClasses=it.redhat.osd.ShippingEndpoint")
    private Endpoint restEndpoint;

    @Inject
    @Uri("http://{{SHIPPING_SOAP_ENDPOINT}}/ShippingService?bridgeEndpoint=true")
    private Endpoint soapWsEndpoint;
    
    
    
    @Override
    public void configure() throws Exception {
    	from(restEndpoint)
    		.convertBodyTo(String.class)
    		    .to("freemarker:it/redhat/osd/soapTemplate.ftl")
    		    	.removeHeaders("*")
    		    		.hystrix()
    		    	      	    .to(soapWsEndpoint)
    		    	      		.setBody(xpath("//return",String.class))
    		    	      .onFallback()
    		    	            .transform().constant("Shipping Info Unavailable")
    		    	      .end() 
		    		       			.marshal().json()
		    		       					.to(logEndpoint);
    }	

}
