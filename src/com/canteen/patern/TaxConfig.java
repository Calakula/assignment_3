package com.canteen.pattern.singleton;

public class TaxConfig {
    private static TaxConfig instance;
    private double taxRate;

    private TaxConfig() {
        this.taxRate = 0.12; // 12% по умолчанию
    }

    public static synchronized TaxConfig getInstance() {
        if (instance == null) {
            instance = new TaxConfig();
        }
        return instance;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }
}
