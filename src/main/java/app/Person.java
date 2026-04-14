package app;

import java.io.Serializable;

public class Person implements Serializable {

    private String name;

    private String nationalId;

    private String address;

    private int age;

    public Person(String name,  String nationalId){
        this.name = name.toUpperCase();
        this.nationalId = nationalId.toUpperCase();
    }

    public Person(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
