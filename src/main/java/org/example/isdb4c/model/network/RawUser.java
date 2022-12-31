package org.example.isdb4c.model.network;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class RawUser implements Serializable {

    private String login;
    private String password;

}
