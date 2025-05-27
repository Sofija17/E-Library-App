//package mk.ukim.finki.wp.labgra.jobs;
//
//import mk.ukim.finki.wp.labgra.service.domain.BookService;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ScheduledTasks {
//
//    private final BookService bookService;
//
//    public ScheduledTasks(BookService bookService) {
//        this.bookService = bookService;
//    }
//
//    @Scheduled(cron = "0 0 * * * *")
//    public void refreshMaterializedView() {
//        this.bookService.refreshMaterializedView();
//    }
//}
