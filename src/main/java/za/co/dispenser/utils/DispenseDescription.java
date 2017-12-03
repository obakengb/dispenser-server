package za.co.dispenser.utils;

public enum DispenseDescription {
    LESS_200("R200", 200),
    LESS_100("R100", 100),
    LESS_50("R50", 50),
    LESS_20("R20", 20),
    LESS_10("R10", 10),
    LESS_5("R5", 5),
    LESS_2("R2", 2),
    LESS_1("R1", 1),
    LESS_0_5("R0.50", 0.5),
    LESS_0_2("R0.20", 0.2),
    LESS_0_1("R0.10", 0.1),
    LESS_0("R0", 0);

    private String description;
    private double amount;

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    DispenseDescription(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public static DispenseDescription fromAmount(Double amount) {
        if (amount != null) {
            for (DispenseDescription dispenseDescription : DispenseDescription.values()) {
                if (amount == (dispenseDescription.amount)) {
                    return dispenseDescription;
                }
            }
        }
        return null;
    }
}

