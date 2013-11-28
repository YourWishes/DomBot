package com.domsplace.DomBot.Enums;

public class ManagerType {
    public static final ManagerType CONFIG = new ManagerType("Configuration");
    public static final ManagerType BRAIN = new ManagerType("Brain");
    
    //Instance
    private String type;
    
    public ManagerType(String type) {
        this.type = type;
    }
    
    public String getType() {
        return this.type;
    }
}
