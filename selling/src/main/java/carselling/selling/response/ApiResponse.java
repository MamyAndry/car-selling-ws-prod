package carselling.selling.response;

import java.util.HashMap;

public class ApiResponse {
    Object data = null;
    Object error = null;


    

    public void addData(String key, Object value) {
        if(key.equals("data"))
            this.setData(value);
        else
            this.setError(value);
    }

    public void addError(String key, Object value) {
        if(key.equals("error"))
            this.setError(value);
    }
    public ApiResponse(){}


    public Object getData() {
        return data;
    }


    public void setData(Object data) {
        this.data = data;
    }


    public Object getError() {
        return error;
    }


    public void setError(Object error) {
        this.error = error;
    }
}
