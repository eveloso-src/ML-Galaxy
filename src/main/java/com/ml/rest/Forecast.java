package com.ml.rest;


public class Forecast {

    private static final String CLIMA_LLUVIA = "lluvia";
    private static final String CLIMA_SEQUIA = "sequia";
	private int dia;
    private String clima;

    public Forecast(int dia) {
        this.dia = dia;
        this.clima = CLIMA_LLUVIA;
        
        // calcular desplazamiento 1
        // calcular desplazamiento 2
        // calcular desplazamiento 3
        
        
    }

	public long getDia() {
		return dia;
	}

	public String getClima() {
		return clima;
	}

    


}
