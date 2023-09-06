package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.NotificationTask;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationTaskRepository extends JpaRepository<NotificationTask,Long> {
//@Query(value = "select * from notification_task where date_time=date_trunc('minute', current_timestamp)",nativeQuery = true)
List<NotificationTask> findNotificationTaskByDateTime(LocalDateTime currentDateTime);
}
