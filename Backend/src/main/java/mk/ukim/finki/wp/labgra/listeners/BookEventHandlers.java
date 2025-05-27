//package mk.ukim.finki.wp.labgra.listeners;
//import mk.ukim.finki.wp.labgra.events.BookDataChangedEvent;
//import mk.ukim.finki.wp.labgra.service.domain.BookService;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class BookEventHandlers {
//    private final BookService bookService;
//
//    public BookEventHandlers(BookService bookService) {
//        this.bookService = bookService;
//    }
//
//    @EventListener
//    public void onBookDataChanged(BookDataChangedEvent event){
//            this.bookService.refreshMaterializedView();
//    }
//}
