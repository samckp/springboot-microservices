package com.micro.hotelservice.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(){
        super("Resource not found !!");
    }
    public ResourceNotFoundException(String s) {
        super(s);
    }
}
