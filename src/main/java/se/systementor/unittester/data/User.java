package se.systementor.unittester.data;

import jakarta.persistence.Entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Entity
@Builder
public class User {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String passwordHashed;

    @Getter
    @Setter
    private java.sql.Timestamp registeredAt;

    public User() {

    }
}
