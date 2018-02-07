
package com.airhacks.messaging.control;

import java.util.concurrent.atomic.AtomicInteger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author airhacks.com
 */
public class Seykering {

    AtomicInteger COUNTER = new AtomicInteger();

    @AroundInvoke
    public Object guard(InvocationContext ic) throws Exception {
        try {
            if (COUNTER.get() > 3) {
                System.out.println("Seykering is durchbrant " + COUNTER.get());
                return "durchbrant!!";
            }
            return ic.proceed();
        } catch (Exception ex) {
            COUNTER.incrementAndGet();
            throw ex;
        }
    }


}
