package carselling.selling.service;

import java.util.HashMap;

public class Response {
    private HashMap<String, Object> errors;
    private HashMap<String, Object> datas;
    private HashMap<String, Object> message;

//GETTERS and SETTERS
    public HashMap<String, Object> getErrors() {
        return errors;
    }
    public void setErrors(HashMap<String, Object> errors) {
        this.errors = errors;
    }
    public HashMap<String, Object> getDatas() {
        return datas;
    }
    public void setDatas(HashMap<String, Object> datas) {
        this.datas = datas;
    }
    public HashMap<String, Object> getMessage() {
        return message;
    }
    public void setMessage(HashMap<String, Object> message) {
        this.message = message;
    }

//CONSTRUCTORS
    public Response(){}

//METHODS
    public void putError(String key, Object obj){
        this.getErrors().put(key, obj);
    }
    public void putData(String key, Object obj){
        this.getDatas().put(key, obj);
    }
    public void putMessage(String key, Object obj){
        this.getMessage().put(key, obj);
    }

    
}
