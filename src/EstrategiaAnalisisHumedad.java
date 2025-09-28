import java.util.Date;

public class EstrategiaAnalisisHumedad implements EstrategiaAnalisis {
    @Override
    public Alerta analizar(Sensor sensor) {
        if (!"humedad".equals(sensor.getTipo())) return null;

        double valor = sensor.getValor();

        if (valor >= 0 && valor <= 30) {
            return new Alerta(sensor.getId(),
                    "HUMEDAD BAJA: " + valor + "% EN " + sensor.getUbicacion(),
                    NivelAlerta.ADVERTENCIA, new Date());
        } else if (valor >= 31 && valor <= 60) {
            return new Alerta(sensor.getId(),
                    "HUMEDAD OPTIMA: " + valor + "% EN " + sensor.getUbicacion(),
                    NivelAlerta.INFORMATIVO, new Date());
        } else if (valor >= 61) {
            return new Alerta(sensor.getId(),
                    "HUMEDAD ALTA: " + valor + "% EN " + sensor.getUbicacion(),
                    NivelAlerta.ADVERTENCIA, new Date());
        }

        return null;
    }
}
