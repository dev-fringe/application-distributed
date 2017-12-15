package dev.fringe.actor;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import akka.actor.UntypedAbstractActor;
import dev.fringe.domain.Greet;
import dev.fringe.service.GreetingService;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GreetingActor extends UntypedAbstractActor  {

    private GreetingService greetingService;

    public GreetingActor(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public void onReceive(Object message) throws Throwable {
        if (message instanceof Greet) {
            String name = ((Greet) message).getName();
            getSender().tell(greetingService.greet(name), getSelf());
        } else {
            unhandled(message);
        }
    }
}