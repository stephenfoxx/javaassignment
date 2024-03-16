package com.hjss.models;

public class Coach {
    /**
     * Coach Id
     */
    private final int id;

    /**
     * Coach Name
     */
    private String name;

    /**
     * @param id   Coach Id
     * @param name Coach Name
     */
    public Coach(int id, String name) {
        this.id = id;
        setName(name);
    }

    /**
     * @return Coach's Id
     */
    public int getId() {
        return id;
    }

    /**
     * @return Coach's name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAsString() {
        return "id: " + id + "\nname: " + name;
    }
}
