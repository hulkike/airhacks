
package com.airhacks.hyperloop.stats.control;

import javax.enterprise.event.ObservesAsync;

/**
 *
 * @author airhacks.com
 */
public class MonitoringSink {

    public void onStats(@ObservesAsync String event) {
        System.out.println("--- asynchronous notification " + event);
    }


}
