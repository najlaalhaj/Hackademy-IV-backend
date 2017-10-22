package io.orten.nano.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * represents a donor
 */
@Entity
@Table
public class User {

        @Id
        private String userID;
        private String firstName;
        private String lastName;

        /**
         * default constructor required by hibernate
         */
        public User(){

        }

        /**
         * business constructor
         */
        public User(String userID, String firstName, String lastName) {
            this.userID = userID;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        /**
         * getters methods
         */
        public String getUserID() {
            return userID;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        /**
         * setter methods
         */
        public void setUserID(String userID) {
            this.userID = userID;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;

        }
    }


