import java.util.Date;

public class EstrategiaAnalisisCalidadAire implements EstrategiaAnalisis{
    @Override
    public Alerta analizar(Sensor sensor) {
        if (!"aire".equals(sensor.getTipo())) return null;

        double valor = sensor.getValor();
        if (valor >= 0 && valor <= 50) {
            return new Alerta(sensor.getId(),
                    "CALIDAD BUENA - ICA/AQI: " + valor + " EN " + sensor.getUbicacion(),
                    NivelAlerta.INFORMATIVO, new Date());
        } else if(valor >= 51 && valor <= 100){
            return new Alerta(sensor.getId(),
                    "CALIDAD MODERADA - ICA/AQI: " + valor + " EN " + sensor.getUbicacion(),
                    NivelAlerta.ADVERTENCIA, new Date());
        } else if(valor >= 101){
            return new Alerta(sensor.getId(),
                    "CALIDAD MALA - ICA/AQI: " + valor + " EN " + sensor.getUbicacion(),
                    NivelAlerta.CRITICO, new Date());
        }
        return null;
    }
}
