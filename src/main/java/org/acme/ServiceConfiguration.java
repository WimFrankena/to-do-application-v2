package org.acme;

import javax.enterprise.inject.Produces;

public class ServiceConfiguration {
    @Produces
    IToDoService createInMemoryService() {
        return new ToDoService();
    }
}
