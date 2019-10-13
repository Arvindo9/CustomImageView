package com.example.imageview.model;

/**
 * Author       : Arvindo Mondal
 * Created on   : 22-09-2019
 * Email        : arvindo@aiprog.in
 * Company      : AIPROG
 * Designation  : Programmer
 * About        : I am a human can only think, I can't be a person like machine which have lots of memory and knowledge.
 * Quote        : No one can measure limit of stupidity but stupid things bring revolutions
 * Strength     : Never give up
 * Motto        : To be known as great Mathematician
 * Skills       : Algorithms and logic
 * Website      : www.aiprog.in
 */
public class User {

    int color;
    String avatarUrl;

    /*
     * Return user short name
     * */
    public static String getShortName(String name) {
        String[] strings = name.split(" ");//no i18n
        String shortName;
        if (strings.length == 1) {
            shortName = strings[0].substring(0, 2);
        } else {
            shortName = strings[0].substring(0, 1) + strings[1].substring(0, 1);
        }
        return shortName.toUpperCase();
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
