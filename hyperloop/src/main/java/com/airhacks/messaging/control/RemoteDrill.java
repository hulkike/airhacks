
package com.airhacks.messaging.control;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.event.Event;
import javax.enterprise.event.NotificationOptions;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author airhacks.com
 */
//@Interceptors(Seykering.class)
public class RemoteDrill {

    private Client client;
    private WebTarget tut;

    @Resource
    ManagedExecutorService mes;

    @Inject
    Event<String> queue;

    @PostConstruct
    public void init() {
        this.client = ClientBuilder.newBuilder().
                executorService(mes).
                connectTimeout(1, TimeUnit.SECONDS).
                readTimeout(1, TimeUnit.SECONDS).
                build();

        this.tut = this.client.target("http://boring:8080/boring/resources/ping");
    }

    public CompletionStage<String> message() {
        NotificationOptions options = NotificationOptions.ofExecutor(this.mes);
        queue.fireAsync("-- boring called", options);
        return this.tut.request().
                rx().
                get(String.class);
    }


}
