package mk.ukim.finki.wp.labgra.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import java.time.LocalDateTime;

@Getter
public class BookDataChangedEvent extends ApplicationEvent {

    private LocalDateTime when;

    public BookDataChangedEvent(Object source) {
        super(source);
        this.when =LocalDateTime.now();
    }

    public BookDataChangedEvent(Object source, LocalDateTime when) {
        super(source);
        this.when = when;
    }
}
