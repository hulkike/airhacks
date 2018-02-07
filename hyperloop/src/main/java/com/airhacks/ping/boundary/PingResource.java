package com.airhacks.ping.boundary;

import com.airhacks.messaging.control.RemoteDrill;
import java.util.concurrent.TimeUnit;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Path("ping")
public class PingResource {

    @Inject
    String message;

    @Inject
    RemoteDrill drill;

    @GET
    public void ping(@Suspended AsyncResponse response) {
        response.setTimeout(1, TimeUnit.SECONDS);
        drill.message().
                thenApply(this::decorateWithMessage).
                thenAccept(response::resume);
    }

    String decorateWithMessage(String input) {
        return input + this.message;
    }

    //return this.drill.message() + this.message + System.currentTimeMillis();

}
