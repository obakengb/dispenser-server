package za.co.dispenser.rest;

import org.slf4j.Logger;
import za.co.dispenser.services.DenominationDispenserService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/DenominationDispenserService")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.APPLICATION_JSON})
public class DenominationDispenserEndpoint {

    @Inject
    private DenominationDispenserService denominationDispenserService;
    @Inject
    private Logger logger;

    @GET
    @Path("/getChangeDenominations/{change}")
    public Response getChangeDenominations(@PathParam("change") String change) {
        try {
            logger.info("Change denominations request for change {}", change);
            return Response.ok(denominationDispenserService.getDenominations(Double.parseDouble(change))).build();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Response.serverError().entity("Could not generate change for you :( ").build();
        }
    }
}
