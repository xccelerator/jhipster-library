package com.mycompany.myapp.service.dto;

import java.io.Serializable;

public class CreateBorrowBookDTO implements Serializable {

    private String bookIsbn;
    private Integer clientId;

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
}
