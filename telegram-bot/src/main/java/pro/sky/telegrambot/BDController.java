package pro.sky.telegrambot;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("base")
public class BDController {

    private static BDService bdService;

    public BDController(BDService bdService) {
        this.bdService = bdService;
    }

    @PostMapping
    public static Notify addNotify(@RequestBody Notify notify){
        return bdService.createNotify(notify);
    }

    @PutMapping
    public Notify updateNotify(@RequestBody Notify notifyNew){
        return bdService.updateNotify(notifyNew);
    }

    @GetMapping
    public Notify findNotify(@PathVariable Long id){
        return bdService.findNotify(id);
    }

    @DeleteMapping("{id}")
    public void deleteNotify(@PathVariable Long id){
        bdService.deleteNotify(id);
    }
}
