package com.eazybytes.eazyschool.model;

import javax.persistence.*;


@Entity
@Table(name = "holidays")
public class Holiday extends BaseEntity {

    @Id
    private String day;

    private String reason;

    @Enumerated(EnumType.STRING)
    private Type type;

    public Holiday() {
    }

    public enum Type {
        FESTIVAL, FEDERAL, TRADITIONAL
    }

    public String getDay() {
        return day;
    }

    public String getReason() {
        return reason;
    }

    public Type getType() {
        return type;
    }
}
