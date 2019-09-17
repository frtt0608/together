package com.ssafy.hashtag.db.dto;

public class MessageDto {

    private int message_pk;
    private int send_id;
    private int receive_id;
    private int chatroom_id;
    private String content;
    private String created_at;

    public MessageDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public MessageDto(int message_pk, int send_id, int receive_id, int chatroom_id, String content, String created_at) {
        super();
        this.message_pk = message_pk;
        this.send_id = send_id;
        this.receive_id = receive_id;
        this.chatroom_id = chatroom_id;
        this.content = content;
        this.created_at = created_at;
    }

    public int getMessage_pk() {
        return message_pk;
    }

    public void setMessage_pk(int message_pk) {
        this.message_pk = message_pk;
    }

    public int getSend_id() {
        return send_id;
    }

    public void setSend_id(int send_id) {
        this.send_id = send_id;
    }

    public int getReceive_id() {
        return receive_id;
    }

    public void setReceive_id(int receive_id) {
        this.receive_id = receive_id;
    }

    public int getChatroom_id() {
        return chatroom_id;
    }

    public void setChatroom_id(int chatroom_id) {
        this.chatroom_id = chatroom_id;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "MessageDto [message_pk=" + message_pk + ", send_id=" + send_id + ", receive_id=" + receive_id
                + ", content=" + content + ", created_at=" + created_at + "]";
    }
}