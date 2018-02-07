/*
 */
package com.airhacks.hyperloop;

import java.util.concurrent.TimeUnit;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class PingIT {

    private Client client;
    private WebTarget tut;

    @Before
    public void init() {
        this.client = ClientBuilder.newBuilder().
                connectTimeout(1, TimeUnit.SECONDS).
                readTimeout(1, TimeUnit.SECONDS).
                build();

        this.tut = this.client.target("http://localhost:8282/hyperloop/resources/ping");
    }

    @Test
    public void smoke() {
        Response response = this.tut.request().get();
        assertThat(response.getStatus(), is(200));
        String payload = response.readEntity(String.class);
        System.out.println("payload = " + payload);
    }


}
