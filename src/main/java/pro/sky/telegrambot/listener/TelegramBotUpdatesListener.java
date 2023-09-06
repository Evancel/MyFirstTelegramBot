package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.parser.Dates;
import pro.sky.telegrambot.parser.InMessage;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final NotificationTaskRepository notificationTaskRepository;
    public TelegramBotUpdatesListener(NotificationTaskRepository notificationTaskRepository1){
        this.notificationTaskRepository=notificationTaskRepository1;
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

            // Greetings
            if (update.message().text().equals("/start")){
                sendAnswer(update.message().chat().id(),"Welcome to my telegram Bot!");
            } else {
                //получаем от пользователя текст
                String s = update.message().text();
                LocalDateTime notificationDate = Dates.parse(s);
                String incomingMessage = InMessage.parseInMessage(s);

                //если дата не определена, значит ввод формата сообщения не соответствует ожидаемому шаблону,
                //текст сообщения обнуляем
                if (notificationDate == null || incomingMessage == null) {
                    sendAnswer(update.message().chat().id(), "Error in format input data!");
                } else {
                    NotificationTask notificationTask = new NotificationTask(update.message().chat().id(),
                            incomingMessage,
                            notificationDate);
                    notificationTaskRepository.save(notificationTask);
                    sendAnswer(notificationTask.getChatId(), "Task: " + notificationTask.getMessage());
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void sendAnswer(Long chatId, String text){
        SendMessage message = new SendMessage(chatId,text);
        telegramBot.execute(message);
    }
}
