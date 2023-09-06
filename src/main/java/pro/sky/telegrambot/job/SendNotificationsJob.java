package pro.sky.telegrambot.job;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class SendNotificationsJob {
   private final NotificationTaskRepository notificationTaskRepository;

    public SendNotificationsJob(NotificationTaskRepository notificationTaskRepository) {
        this.notificationTaskRepository = notificationTaskRepository;
    }

    private final Logger log = LoggerFactory.getLogger(SendNotificationsJob.class);

    @Autowired
    private TelegramBot telegramBot;

    @Scheduled(timeUnit = TimeUnit.MINUTES, fixedRate = 1)
    //(cron = "0 0/1 * * * *")
    public void processBySchedule() {
        LocalDateTime currentTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        log.info("Sending notifications time: " + currentTime);
        List<NotificationTask> currentTasks = notificationTaskRepository.findNotificationTaskByDateTime(currentTime);
        currentTasks.forEach(task->{
                    SendMessage message = new SendMessage(task.getChatId(),"It's time: " + task.getMessage());
                    SendResponse response = telegramBot.execute(message);
                }
        );
    }
}
