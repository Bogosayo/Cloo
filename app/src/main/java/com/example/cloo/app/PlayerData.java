package com.example.cloo.app;

/**
 * Created by Andrew on 1/7/2015.
 */
public class PlayerData {
    private boolean activeStatus;
    private boolean humanity;
    private String name;

    public void PlayerData(){
        activeStatus = true;
        humanity = false;
        name = new String();
    }

    public void setActiveStatus(boolean placeHolderStatus){
        this.activeStatus = placeHolderStatus;
    }
    public boolean isPlaying(){
        return activeStatus;
    }

    public void setHumanity(boolean placeHolderHumanity){
        this.humanity = placeHolderHumanity;
    }
    public boolean isHuman(){
        return humanity;
    }

    public void setName(String placeHolderName){
        this.name = placeHolderName;
    }
    public String getName(){
        return name;
    }
}
