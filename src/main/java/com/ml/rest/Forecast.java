package com.ml.rest;


public class Forecast {

    private final long dia;
    private final String clima;

    public Forecast(long dia, String clima) {
        this.dia = dia;
        this.clima = clima;
    }

	public long getDia() {
		return dia;
	}

	public String getClima() {
		return clima;
	}

    


}
