package com.ml.rest.util;

import com.ml.rest.model.PlanetBase;
import com.ml.rest.model.PlanetBetasoide;
import com.ml.rest.model.PlanetFerengi;
import com.ml.rest.model.PlanetVulcano;

public class PlanetFactory {
	public static PlanetBase getPlanet(String planetName)  {
		if (PlanetBase.PLANET_BETA.equals(planetName)) {
			new PlanetBetasoide();
			
		}
		else if (PlanetBase.PLANET_FERE.equals(planetName)) {
			new PlanetFerengi();
			
		}
		else {
			new PlanetVulcano();
		}
		
		return null;
		
	}

}
