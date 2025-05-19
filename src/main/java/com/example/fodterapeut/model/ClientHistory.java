package com.example.fodterapeut.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientHistory {

    private String date;
    private String note;

    public ClientHistory() {
    }

    public ClientHistory(String date, String note) {
        this.date = date;
        this.note = note;
    }
}
