package com.ml.rest.model;

public class DayForecast {

	public static final String WEATHER_RAIN = "lluvia";
	public static final String WEATHER_OPTIMUS = "optimo";
	public static final String WEATHER_DRY = "sequia";

	private int dia;
    private String clima;
    
    
    public DayForecast() {
    	
    }

    public DayForecast(int dia, String clima) {
        this.dia = dia;
        this.clima = clima;
        
		
    }

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}


}
