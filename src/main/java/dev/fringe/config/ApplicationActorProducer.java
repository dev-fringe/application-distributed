package dev.fringe.config;

import org.springframework.context.ApplicationContext;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;

public class ApplicationActorProducer implements IndirectActorProducer {

    private ApplicationContext applicationContext;

    private String beanActorName;

    public ApplicationActorProducer(ApplicationContext applicationContext, String beanActorName) {
        this.applicationContext = applicationContext;
        this.beanActorName = beanActorName;
    }

    public Actor produce() {
        return (Actor) applicationContext.getBean(beanActorName);
    }

    @SuppressWarnings("unchecked")
	public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) applicationContext.getType(beanActorName);
    }

}