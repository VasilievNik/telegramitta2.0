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

    public Long setId(Long id){
        this.id = id;
        return id;
    }

    public Long getChatId(){
        return this.chatId;
    }

    public Long setChatId(Long chatId){
        this.chatId = chatId;
        return chatId;
    }

    public String getText(){
        return this.text;
    }

    public String setText(String text){
        this.text = text;
        return text;
    }

    public LocalDateTime getLocalDateTime(){
        return this.localDateTime;
    }

    public LocalDateTime setLocalDateTime(LocalDateTime localDateTime){
        this.localDateTime = localDateTime;
        return localDateTime;
    }

}
