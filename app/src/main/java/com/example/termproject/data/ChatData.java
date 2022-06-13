package com.example.termproject.data;

public class ChatData {
    private String nickname;
    private String msg;
    private String time;
    private String uid;

    public ChatData(String name, String message, String time, String uid) {
        this.nickname = name;
        this.msg = message;
        this.time = time;
        this.uid = uid;
    }

    public ChatData(){

    }



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}