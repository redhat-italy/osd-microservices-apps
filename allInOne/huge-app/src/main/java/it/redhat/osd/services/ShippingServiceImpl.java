/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package it.redhat.osd.services;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Logger;

import javax.jws.WebService;

/**
 * 
 * @author gbonocor@redhat.com
 */
@WebService(serviceName = "ShippingService", portName = "Shipping", name = "Shipping", endpointInterface = "it.redhat.osd.services.ShippingService")
public class ShippingServiceImpl implements ShippingService {

	private static Logger log = Logger.getLogger(ShippingServiceImpl.class.getName());

	
	private static Hashtable<String,String> countryPrices;
	
	public ShippingServiceImpl() {
		populatePrices();
	}
	
    

	@Override
    public String quoteShipping(final String country, final String address) {
			log.info("coming request for "+country+" : "+address);
			return countryPrices.getOrDefault(country,countryPrices.get("OTHER"));
		
    }

	
	
	
	private static void populatePrices() {
		if(countryPrices == null)
		{
			countryPrices = new Hashtable<String,String>();
			countryPrices.put("IT","15 EUR");
			countryPrices.put("UK","25 EUR");
			countryPrices.put("DE","20 EUR");
			countryPrices.put("FR","30 EUR");
			countryPrices.put("ES","15 EUR");
			countryPrices.put("US","40 EUR");
			countryPrices.put("CH","35 EUR");
			countryPrices.put("OTHER","50 EUR");
			
		}
	}
    
    
}
