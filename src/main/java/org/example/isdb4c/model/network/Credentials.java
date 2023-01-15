package org.example.isdb4c.model.network;

import lombok.Data;

import java.io.Serializable;

@Data
public class Credentials implements Serializable {
    private String login;
    private String token;
    private Integer employee_id;

    public Credentials() {
        this.login = null;
        this.token = null;
        this.employee_id = null;
    }
    public Credentials(String login, String token, Integer employee_id) {
        this.login = login;
        this.token = token;
        this.employee_id = employee_id;
    }
}
