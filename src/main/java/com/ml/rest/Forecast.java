package com.ml.rest;


public class Forecast {

    private int dia;
    private String clima;

    public Forecast(int dia) {
        this.dia = dia;
        this.clima = "";
    }

	public long getDia() {
		return dia;
	}

	public String getClima() {
		return clima;
	}

    


}
