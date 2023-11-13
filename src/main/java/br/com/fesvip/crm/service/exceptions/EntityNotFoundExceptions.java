package br.com.fesvip.crm.service.exceptions;

public class EntityNotFoundExceptions extends RuntimeException{
    public EntityNotFoundExceptions(String msg){
        super(msg);
    }
}

