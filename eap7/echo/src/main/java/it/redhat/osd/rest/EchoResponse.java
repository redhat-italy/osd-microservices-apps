package it.redhat.osd.rest;

import java.util.UUID;

public class EchoResponse {
    public String status;
    public String payload;

    public EchoResponse(){
        status = new String("success");
        payload = new String(UUID.randomUUID().toString());
    }

    public EchoResponse setPayload(String payload){
        this.payload = payload;
        return this;
    }

    public EchoResponse setStatus(String status){
        this.status = status;
        return this;
    }
}
