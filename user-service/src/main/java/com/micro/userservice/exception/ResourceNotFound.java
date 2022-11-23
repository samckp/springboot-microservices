package com.micro.userservice.exception;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(){
        super("Resource Not Found on server !!");
    }

    public ResourceNotFound(String message){
        super(message);
    }
}
