package carselling.selling.response;

import java.util.HashMap;

public class ApiResponse {
    HashMap<String, Object> data;
    HashMap<String, Object> error;
    int status = 200;

    public HashMap<String, Object> getData() {
        return data;
    }
    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public void addData(String key, Object value) {
        this.data.put(key, value);
    }

    public HashMap<String, Object> getError() {
        return error;
    }
    public void setError(HashMap<String, Object> error) {
        this.error = error;
    }
    public void addError(String key, Object value) {
        this.error.put(key, value);
    }

    public ApiResponse(){
        data = new HashMap<>();
        error = new HashMap<>();
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
