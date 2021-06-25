package com.bicco.autonomo.Form.Login;

import java.util.ArrayList;

public class feedAutonomos {
    ArrayList<perfilPagoGratuito> pago = new ArrayList();
    ArrayList<perfilPagoGratuito> gratuito = new ArrayList();

    public ArrayList<perfilPagoGratuito> getPago() {
        return pago;
    }

    public void setPago(ArrayList<perfilPagoGratuito> pago) {
        this.pago = pago;
    }

    public ArrayList<perfilPagoGratuito> getGratuito() {
        return gratuito;
    }

    public void setGratuito(ArrayList<perfilPagoGratuito> gratuito) {
        this.gratuito = gratuito;
    }
}
