package com.example.storecode_android;

public class Proceso {
    private String intent;
    private String purchase_units;

    public Proceso(String intent, String purchase_units) {
        this.intent = intent;
        this.purchase_units = purchase_units;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getPurchase_units() {
        return purchase_units;
    }

    public void setPurchase_units(String purchase_units) {
        this.purchase_units = purchase_units;
    }
}
