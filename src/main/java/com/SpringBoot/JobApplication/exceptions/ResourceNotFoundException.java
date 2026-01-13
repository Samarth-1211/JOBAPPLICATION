package com.SpringBoot.JobApplication.exceptions;




public class ResourceNotFoundException extends RuntimeException{


    public ResourceNotFoundException(String message){
        super(message);
    }
}