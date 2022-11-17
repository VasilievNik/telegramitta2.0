package pro.sky.telegrambot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BDRepository extends JpaRepository<Notify, Long> {
    List<Notify> getAllByLocalDateTime(LocalDateTime localDateTime);

}