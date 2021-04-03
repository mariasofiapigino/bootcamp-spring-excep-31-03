package com.segundaparte.linktracker.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LinkDTO {
    private String url;
    private int visitas;
    private int id;
    private String password;

    public LinkDTO(){
        this.visitas = 0;
    }

    public LinkDTO(String url, int id, String password) {
        this.url = url;
        this.visitas = 0;
        this.id = id;
        this.password = password;
    }
}
