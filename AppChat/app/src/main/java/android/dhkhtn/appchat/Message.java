package android.dhkhtn.appchat;

import java.util.Date;

/**
 * Created by DELL on 4/9/2018.
 */

public class Message {
    private String messageText;
    private String messageUser;
    private long messageTime;
    private String urlImage;
    private String id;
    private MessageType messageType;
    private String url_download;


    public Message(String messageText, String messageUser, long messageTime, String urlImage, String id,MessageType messageType,String url_download) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.messageTime = messageTime;
        this.urlImage = urlImage;
        this.id = id;
        this.messageType = messageType;
        this.url_download=url_download;
    }

    public Message(String id,String messageText, String messageUser, String urlImage,MessageType messageType,String url_download) {
        this.id=id;
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.urlImage=urlImage;
        this.messageType = messageType;
        this.url_download=url_download;
        // Initialize to current time
        messageTime = new Date().getTime();

    }


    public Message(){

    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public void setUrl_download(String url_download) {
        this.url_download = url_download;
    }

    public MessageType getMessageType() {

        return messageType;
    }

    public String getUrl_download() {
        return url_download;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {

        return id;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getUrlImage() {

        return urlImage;
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
