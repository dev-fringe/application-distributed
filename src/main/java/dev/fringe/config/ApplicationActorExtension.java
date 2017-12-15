package dev.fringe.config;

import org.springframework.context.ApplicationContext;

import akka.actor.Extension;
import akka.actor.Props;

public class ApplicationActorExtension implements Extension {

    private volatile ApplicationContext applicationContext;

    public void initialize(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Props props(String beanName) {
        return Props.create(ApplicationActorProducer.class, applicationContext, beanName);
    }

}
