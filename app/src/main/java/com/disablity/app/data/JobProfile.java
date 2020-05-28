package com.disablity.app.data;


public class JobProfile
{
    private String id;
    private String position;
    private String salary;
    private String location;
    private String recuruiter_id;

    public JobProfile(String position, String salary, String location, String recuruiter_id)
    {
        this.position = position;
        this.salary = salary;
        this.location = location;
        this.recuruiter_id = recuruiter_id;
        this.id= System.currentTimeMillis() +"";
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public String getSalary()
    {
        return salary;
    }

    public void setSalary(String salary)
    {
        this.salary = salary;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getRecuruiter_id()
    {
        return recuruiter_id;
    }

    public void setRecuruiter_id(String recuruiter_id)
    {
        this.recuruiter_id = recuruiter_id;
    }
}
