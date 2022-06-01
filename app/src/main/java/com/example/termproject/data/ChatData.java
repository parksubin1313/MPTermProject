package com.example.termproject.data;

public class ChatData {
    private String nickname;
    private String msg;
    private String time;

    public ChatData(String name, String message, String time) {
        this.nickname = name;
        this.msg = message;
        this.time = time;
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
}
