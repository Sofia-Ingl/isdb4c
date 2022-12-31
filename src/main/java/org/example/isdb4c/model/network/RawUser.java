package org.example.isdb4c.model.network;

import lombok.Data;

import javax.persistence.Column;

@Data
public class RawUser {

    private String login;
    private String password;

}
