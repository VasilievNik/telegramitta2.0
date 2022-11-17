package pro.sky.telegrambot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class BDService {

    private static BDRepository bdRepository;
    private static final Logger logger = LoggerFactory.getLogger(BDService.class);

    @Autowired
    public BDService(BDRepository bdRepository) {
        this.bdRepository = bdRepository;
    }

    public Notify createNotify(Notify notify) {
        logger.info("createStudent method used in StudentService");
        return bdRepository.save(notify);
    }

    public Notify updateNotify(Notify notifyNew) {
        logger.info("updateStudent method used in StudentService");
        Notify notifyOld = bdRepository.findById(notifyNew.getId()).get();
        notifyOld = notifyNew;
        bdRepository.save(notifyOld);
        return notifyNew;
    }

    public Notify findNotify(Long id) {
        logger.info("findStudent method used in StudentService");
        return bdRepository.findById(id).get();
    }

    public void deleteNotify(Long id) {
        logger.info("deleteStudent method used in StudentService");
        bdRepository.deleteById(id);
    }

    public List<Notify> getDateTimeNotify(LocalDateTime localDateTime){
        LocalDate date = LocalDate.from(localDateTime);
        LocalTime time = LocalTime.from(localDateTime);
        return bdRepository.getAllByLocalDateTime(localDateTime);
    }

}
