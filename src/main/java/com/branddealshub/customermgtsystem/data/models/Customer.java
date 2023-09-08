//package com.branddealshub.customermgtsystem.data.models;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import org.hibernate.annotations.NaturalId;
//
//import java.io.Serializable;
//
//@Data
//
//@Entity
//@Table(name = "customers")
//public class Customer implements   Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//
//    private String firstName;
//    private String lastName;
//
//    @NaturalId
//    private String email;
//
//    @NaturalId
//    private String phone;
//
//    private String country;
//    private String city;
//    private String address;
//
//
//    // Protected no-arg constructor for JPA
//
//}


package com.branddealshub.customermgtsystem.data.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import java.io.Serializable;

@Data
@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @NaturalId
    private String email;

    @NaturalId
    private String phone;

    private String country;
    private String city;
    private String address;

    public Customer() {
    }

    public Customer(Long id, String firstName, String lastName, String email, String phone, String country, String city, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.city = city;
        this.address = address;
    }
}