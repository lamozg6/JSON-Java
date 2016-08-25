package com.company;

/**
 * Created by Karen on 24.08.2016.
 */
public class Page implements Comparable<Page> {
    private String id;
    private String name;
    private String created_time;

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        if(created_time == null)
            throw new RuntimeException("Created_time can't be null");
        this.created_time = created_time;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        if(id == null)
            throw new RuntimeException("ID can't be null");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null)
            throw new RuntimeException("Name can't be null");
        this.name = name;
    }

    @Override
    public int compareTo(Page o) {
        if(this.getName().compareTo(o.getName()) != 0)
            return this.getName().compareTo(o.getName());
        return this.getID().compareTo(o.getID());
    }

    @Override
    public String toString() {
        return "Page{" +
                "ID='" + id + '\'' +
                ", name='" + name + '\'' +
                ", created_time='" + created_time + '\'' +
                '}' + "\n";
    }
}
