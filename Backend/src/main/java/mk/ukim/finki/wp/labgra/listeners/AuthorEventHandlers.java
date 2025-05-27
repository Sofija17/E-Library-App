//package mk.ukim.finki.wp.labgra.listeners;
//
//import mk.ukim.finki.wp.labgra.events.AuthorChangedEvent;
//import mk.ukim.finki.wp.labgra.events.AuthorCreatedEvent;
//import mk.ukim.finki.wp.labgra.events.AuthorDeletedEvent;
//import mk.ukim.finki.wp.labgra.service.application.AuthorApplicationService;
//import mk.ukim.finki.wp.labgra.service.application.CountryApplicationService;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AuthorEventHandlers {
//    private final AuthorApplicationService authorApplicationService;
//
//    public AuthorEventHandlers(AuthorApplicationService authorApplicationService) {
//        this.authorApplicationService = authorApplicationService;
//    }
//
//    @EventListener
//    public void onAuthorCreated(AuthorCreatedEvent event) {
//        this.authorApplicationService.refreshMaterializedView();
//    }
//    @EventListener
//    public void onAuthorDeleted(AuthorDeletedEvent event) {
//        this.authorApplicationService.refreshMaterializedView();
//    }
//    @EventListener
//    public void onAuthorChanged(AuthorChangedEvent event) {
//        this.authorApplicationService.refreshMaterializedView();
//    }
//}
