package com.mycompany.myapp.service.dto;

import java.io.Serializable;

public class CreatePublisherDTO implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
