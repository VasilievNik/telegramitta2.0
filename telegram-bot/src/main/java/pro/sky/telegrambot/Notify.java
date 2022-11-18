package pro.sky.telegrambot;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Notify {
    @Id
    @GeneratedValue
    private Long id;
    private Long chatId;
    private String text;
    private LocalDateTime localDateTime;

    public Notify(Long chatId, String text, LocalDateTime dateAndTime){
        this.chatId = chatId;
        this.text = text;
        this.localDateTime = dateAndTime;
    }

    public Notify() {
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getChatId(){
        return this.chatId;
    }

    public void setChatId(Long chatId){
        this.chatId = chatId;
    }

    public String getText(){
        return this.text;
    }

    public void setText(String text){
        this.text = text;
    }

    public LocalDateTime getLocalDateTime(){
        return this.localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime){
        this.localDateTime = localDateTime;
    }

}
