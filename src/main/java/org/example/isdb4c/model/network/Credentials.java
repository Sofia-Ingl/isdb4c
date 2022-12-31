package org.example.isdb4c.model.network;

import lombok.Data;

import java.io.Serializable;

@Data
public class Credentials implements Serializable {
    private String login;
    private String token;

    public Credentials() {
        this.login = null;
        this.token = null;
    }
    public Credentials(String login, String token) {
        this.login = login;
        this.token = token;
    }
}
