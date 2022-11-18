package pro.sky.telegrambot;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("base")
public class BDController {

    private final BDService bdService;

    public BDController(BDService bdService) {
        this.bdService = bdService;
    }

    @PostMapping
    public Notify addNotify(@RequestBody Notify notify){
        return bdService.createNotify(notify);
    }

    @PutMapping
    public Notify updateNotify(@RequestBody Notify notifyNew){
        return bdService.updateNotify(notifyNew);
    }

    @GetMapping("{id}")
    public Notify findNotify(@PathVariable Long id){
        return bdService.findNotify(id);
    }

    @DeleteMapping("{id}")
    public void deleteNotify(@PathVariable Long id){
        bdService.deleteNotify(id);
    }
}
