package com.ml.rest;

import com.ml.rest.model.PlanetBetasoide;
import com.ml.rest.model.PlanetFerengi;
import com.ml.rest.model.PlanetVulcano;
import com.ml.rest.model.Position;

public class Forecast {

    private static final String CLIMA_LLUVIA = "lluvia";
    private static final String CLIMA_SEQUIA = "sequia";
	private int dia;
    private String clima;

    public Forecast(int dia) {
        this.dia = dia;
        this.clima = CLIMA_LLUVIA;
        
        PlanetBetasoide pbeta = new PlanetBetasoide();
        PlanetFerengi pfere = new PlanetFerengi();
        PlanetVulcano pvul = new PlanetVulcano();
        
        // calcular desplazamiento 1
         Position posBeta = pbeta.getPosition(pbeta.getOffsetInGrades(dia));
        // calcular desplazamiento 2
         Position posFere = pbeta.getPosition(pfere.getOffsetInGrades(dia));
        // calcular desplazamiento 3
         Position posVul = pbeta.getPosition(pvul.getOffsetInGrades(dia));
        
        
    }

	public long getDia() {
		return dia;
	}

	public String getClima() {
		return clima;
	}

    


}
