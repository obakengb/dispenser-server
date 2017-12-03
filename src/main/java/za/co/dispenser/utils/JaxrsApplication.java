package za.co.dispenser.utils;

import za.co.dispenser.rest.DenominationDispenserEndpoint;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class JaxrsApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(DenominationDispenserEndpoint.class);

        return classes;
    }
}
