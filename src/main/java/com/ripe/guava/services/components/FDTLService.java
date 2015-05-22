package com.ripe.guava.services.components;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fdtl/rest/service")
public class FDTLService {
	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	 public String getGreeting(@PathVariable String name) {
	  String result="Hello "+name;  
	  return result;
	}
}
