package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.BDController;
import pro.sky.telegrambot.BDService;
import pro.sky.telegrambot.Notify;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private static final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final Pattern pattern = Pattern.compile("([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)");
    private final BDService bdService;
    public TelegramBotUpdatesListener(BDService bdService) {
        this.bdService = bdService;
    }

    @Autowired
    private TelegramBot telegramBot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            if (Objects.equals(update.message().text(), "/start")){
                SendMessage message = new SendMessage(update.message().chat().id(), "Ь");
                SendResponse response = telegramBot.execute(message);
            }

            Matcher matcher = pattern.matcher(update.message().text());
            if (matcher.matches()) {
                LocalDateTime date = LocalDateTime.parse(matcher.group(1));
                String text = matcher.group(2);
                Notify notify = new Notify(update.message().chat().id(), text, date);
                bdService.createNotify(notify);
            }


        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void run() {
        LocalDateTime currentTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        List<Notify> currentList = bdService.getDateTimeNotify(currentTime);
        currentList.forEach((notify) -> {
            SendMessage message = new SendMessage(notify.getChatId(), notify.getText());
            SendResponse response = telegramBot.execute(message);
        });
    }


}
