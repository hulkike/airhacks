
package com.airhacks.hyperloop.configuration.boundary;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author airhacks.com
 */
public class Configurator {

    @Produces
    public String configure(InjectionPoint ip) {
        String fieldName = ip.getMember().getName();
        return System.getenv().getOrDefault(fieldName, "-- not set--");
    }


}
