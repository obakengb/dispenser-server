package za.co.dispenser.services;

import org.slf4j.Logger;
import za.co.dispenser.dto.DenominationsDto;
import za.co.dispenser.utils.DispenseDescription;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class DenominationDispenserService {

    public static EnumMap<DispenseDescription, Double> dispenser = new EnumMap<>(DispenseDescription.class);

    @Inject
    private Logger logger;

    @PostConstruct
    public void init() {
        dispenser.put(DispenseDescription.LESS_200, 100d);
        dispenser.put(DispenseDescription.LESS_100, 50d);
        dispenser.put(DispenseDescription.LESS_50, 20d);
        dispenser.put(DispenseDescription.LESS_20, 10d);
        dispenser.put(DispenseDescription.LESS_10, 5d);
        dispenser.put(DispenseDescription.LESS_5, 2d);
        dispenser.put(DispenseDescription.LESS_2, 1d);
        dispenser.put(DispenseDescription.LESS_1, 0.5);
        dispenser.put(DispenseDescription.LESS_0_5, 0.2);
        dispenser.put(DispenseDescription.LESS_0_2, 0.1);
        dispenser.put(DispenseDescription.LESS_0_1, 0d);
    }

    public DenominationsDto getDenominations(double returnedChange) {

        logger.info("Requesting change denominations for {}", returnedChange);

        List<String> denominations = new ArrayList<>();
        DenominationsDto denominationsDto = new DenominationsDto();

        DispenseDescription dispenseDescription = getDispenseDescription(returnedChange);

        logger.info("dispenseDescription = {}", dispenseDescription.getAmount());

        Double changeAmount = dispenser.get(dispenseDescription);

        denominations.add(DispenseDescription.fromAmount(changeAmount).getDescription());

        while (!dispenseDescription.equals(DispenseDescription.LESS_0_1)) {
            returnedChange = returnedChange - changeAmount;
            dispenseDescription = getDispenseDescription(returnedChange);

            changeAmount = dispenser.get(dispenseDescription);

            if(changeAmount != 0) {
                denominations.add(DispenseDescription.fromAmount(changeAmount).getDescription());
            }

            changeAmount = dispenser.get(dispenseDescription);

        }

        denominationsDto.setDenominations(denominations);

        logger.info("denomination = {}", denominations);

        return denominationsDto;
    }

    private DispenseDescription getDispenseDescription(double amount) {
        if(amount >= 100 && amount < 200) {
            return DispenseDescription.LESS_100;
        } else if(amount >= 50 && amount < 100) {
            return DispenseDescription.LESS_100;
        } else if(amount >= 20 && amount < 50) {
            return DispenseDescription.LESS_50;
        } else if(amount >= 10 && amount < 20) {
            return DispenseDescription.LESS_20;
        } else if(amount >= 5 && amount < 10) {
            return DispenseDescription.LESS_10;
        } else if(amount >= 2 && amount < 5) {
            return DispenseDescription.LESS_5;
        } else if(amount >= 1 && amount < 2) {
            return DispenseDescription.LESS_2;
        } else if(amount >= 0.5 && amount < 1) {
            return DispenseDescription.LESS_1;
        } else if(amount >= 0.2 && amount < 0.5) {
            return DispenseDescription.LESS_0_5;
        } else if(amount >= 0.1 && amount < 0.2) {
            return DispenseDescription.LESS_0_2;
        } else if(amount >= 0 && amount < 0.1) {
            return DispenseDescription.LESS_0_1;
        }

        return DispenseDescription.LESS_0;
    }
}

