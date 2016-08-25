package com.company;

/**
 * Created by karen on 24.08.2016.
 */
public class User {
    private int age;
    private String name;
    private String surname;

    public User(int age, String name, String surname)
    {
        if(age < 0 || name == null || surname == null)
            throw new RuntimeException("Can't create an object");
        this.age = age;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
