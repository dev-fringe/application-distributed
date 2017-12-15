package dev.fringe;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.util.Timeout;
import dev.fringe.config.ApplicationExtension;
import dev.fringe.domain.Greet;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

@Configuration
@ComponentScan
public class Application {

    @Autowired private ActorSystem system;
    
    @SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
    	new AnnotationConfigApplicationContext(Application.class).getBean(Application.class).run();
	}
    
    public void run() throws Exception {
        ActorRef greeter = system.actorOf(ApplicationExtension.SPRING_PROVIDER.get(system).props("greetingActor"), "greeter");
        FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
        Timeout timeout = Timeout.durationToTimeout(duration);
        Future<Object> result = akka.pattern.Patterns.ask(greeter, new Greet("Lee Hak do"), timeout);
        System.out.println(Await.result(result, duration));
	}
    
}
