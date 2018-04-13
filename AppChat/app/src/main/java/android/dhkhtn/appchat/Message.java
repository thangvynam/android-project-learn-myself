package android.dhkhtn.appchat;

import java.util.Date;

/**
 * Created by DELL on 4/9/2018.
 */

public class Message {
    private String messageText;
    private String messageUser;
    private long messageTime;

    public Message(String messageText, String messageUser,long messageTime) {
        this.messageText = messageText;
        this.messageUser = messageUser;


        this.messageTime = messageTime;
    }

    public Message(String messageText, String messageUser) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        // Initialize to current time
        messageTime = new Date().getTime();

    }


    public Message(){

    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}
