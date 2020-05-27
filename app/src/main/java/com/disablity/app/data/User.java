package com.disablity.app.data;

import java.util.Objects;

public class User
{
    private String name;
    private String address;
    private int age;
    private String id;
    private String type;

    /**
     * @param name
     * @param address
     * @param age
     * @param id
     * @param type
     */
    public User(String name, String address, int age, String id, String type)
    {
        this.name = name;
        this.address = address;
        this.age = age;
        this.id = id;
        this.type = type;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(name, user.name) &&
                Objects.equals(address, user.address);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, address, age);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }
}
