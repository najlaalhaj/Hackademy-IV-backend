package io.orten.nano.model;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
/*
  represents an organization raising fund for one or more projects
 */

@Entity
@Table(name="organization")
public class Organization {
    @Id
    public String organizationID;
    public String organizationName;
    public String organizationAddress;
    public String contactPersonName;
    public String contactPersonEmail;
    public String accountNumber;
    public String billingInformation;
    public String description;

    //  default constructor required by hibernate

    public Organization()
    {

    }

    //Bussiness Constructor


    public Organization(String organizationID, String organizationName, String organizationAddress, String contactPersonName, String contactPersonEmail, String accountNumber, String billingInformation, String description) {
        this.organizationID = organizationID;
        this.organizationName = organizationName;
        this.organizationAddress = organizationAddress;
        this.contactPersonName = contactPersonName;
        this.contactPersonEmail = contactPersonEmail;
        this.accountNumber = accountNumber;
        this.billingInformation = billingInformation;
        this.description = description;
    }
}
