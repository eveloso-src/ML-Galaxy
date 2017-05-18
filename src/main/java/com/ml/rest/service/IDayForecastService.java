package com.ml.rest.service;

import com.ml.rest.model.DayForecast;

public interface IDayForecastService {
	
	public int getPeriodAmount(String climaPeriodo);

	public DayForecast getDayForecast(int dia);
}
