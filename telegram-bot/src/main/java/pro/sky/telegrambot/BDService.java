package pro.sky.telegrambot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class BDService {

    private static final Logger logger = LoggerFactory.getLogger(BDService.class);
    private final BDRepository bdRepository;

    public BDService(BDRepository bdRepository) {
        this.bdRepository = bdRepository;
    }

    public Notify createNotify(Notify notify) {
        logger.info("createStudent method used in StudentService");
        return bdRepository.save(notify);
    }

    public Notify updateNotify(Notify notifyNew) {
        logger.info("updateStudent method used in StudentService");
        if (bdRepository.existsById(notifyNew.getId())){
            Notify notifyOld = bdRepository.findById(notifyNew.getId()).get();
            notifyOld.setChatId(notifyNew.getChatId());
            notifyOld.setLocalDateTime(notifyNew.getLocalDateTime());
            notifyOld.setText(notifyNew.getText());
            return notifyNew;
        }
        throw new NotifyNotExistException();
    }

    public Notify findNotify(Long id) {
        logger.info("findStudent method used in StudentService");
        if (bdRepository.existsById(id)){
            return bdRepository.findById(id).get();
        }
        throw new NotifyNotExistException();
    }

    public void deleteNotify(Long id) {
        logger.info("deleteStudent method used in StudentService");
        bdRepository.deleteById(id);
    }

    public List<Notify> getDateTimeNotify(LocalDateTime localDateTime){
        LocalDate date = localDateTime.toLocalDate();
        LocalTime time = localDateTime.toLocalTime();
        return bdRepository.getAllByLocalDateTime(localDateTime);
    }

}
