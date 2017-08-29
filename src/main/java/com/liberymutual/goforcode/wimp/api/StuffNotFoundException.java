package com.liberymutual.goforcode.wimp.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No stuff found. \u2639")
public class StuffNotFoundException extends Exception {
	
	
	private static final long serialVersionUID = 1L;
}
