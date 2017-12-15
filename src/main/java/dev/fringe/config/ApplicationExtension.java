package dev.fringe.config;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;

public class ApplicationExtension extends AbstractExtensionId<ApplicationActorExtension> {

    public static final ApplicationExtension SPRING_PROVIDER = new ApplicationExtension();

    public ApplicationActorExtension createExtension(ExtendedActorSystem system) {
        return new ApplicationActorExtension();
    }
}
