package com.micro.userservice.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="micro_users")
public class User {

    @Id
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "USER_NAME", length = 35)
    private String name;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ABOUT")
    private String about;
}
