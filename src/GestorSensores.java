import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GestorSensores {
    private static GestorSensores instancia;
    private Map<String, Sensor> sensores;

    private GestorSensores() {
        this.sensores = new ConcurrentHashMap<>();
        System.out.println("🔧 Gestor de Sensores inicializado");
    }

    public static GestorSensores obtenerInstancia() {
        if (instancia == null) {
            synchronized (GestorSensores.class) {
                if (instancia == null) {
                    instancia = new GestorSensores();
                }
            }
        }
        return instancia;
    }

    public void registrarSensor(Sensor sensor) {
        sensores.put(sensor.getId(), sensor);
        System.out.println("✅ Sensor registrado: " + sensor.getId() + " en " + sensor.getUbicacion());
    }

    public void actualizarValorSensor(String idSensor, double nuevoValor) {
        Sensor sensor = sensores.get(idSensor);
        if (sensor != null) {
            sensor.setValor(nuevoValor);
            sensor.setUltimaActualizacion(new Date());
            System.out.println("📊 Sensor " + idSensor + " actualizado: " + nuevoValor);

            // Notificar a los observadores
            NotificadorAlertas.obtenerInstancia().verificarYNotificar(sensor);
        } else {
            System.out.println("❌ Sensor no encontrado: " + idSensor);
        }
    }

    public Sensor obtenerSensor(String idSensor) {
        return sensores.get(idSensor);
    }

    public void eliminarSensor(String idSensor) {
        sensores.remove(idSensor);
        System.out.println("🗑️ Sensor eliminado: " + idSensor);
    }

    public List<Sensor> obtenerTodosSensores() {
        return new ArrayList<>(sensores.values());
    }

    public int obtenerCantidadSensores() {
        return sensores.size();
    }

    public List<Sensor> obtenerSensoresPorTipo(String tipo){
        List<Sensor> sensoresPorTipo = new ArrayList<>();
        for(Sensor x : sensores.values()){
            if(x.getTipo().equals(tipo)){
                sensoresPorTipo.add(x);
            }
        }
        return sensoresPorTipo;
    }

    public void obtenerEstadisticas(){
        Sensor sensorMaxValor = null;
        Sensor sensorMinValor = null;

        for(Sensor x : sensores.values()){
            if (sensorMaxValor == null || x.getValor() > sensorMaxValor.getValor()) {
                sensorMaxValor = x;
            }
            if (sensorMinValor == null || x.getValor() < sensorMinValor.getValor()) {
                sensorMinValor = x;
            }
        }
        System.out.println("~~~ Estadisticas ~~~");
        System.out.println("Cantidad de sensores: " + sensores.size());
        System.out.println("Valor maximo - " + "Id: " + sensorMaxValor.getId() + "Tipo" + sensorMaxValor.getTipo() + "Valor: " + sensorMaxValor.getValor());
        System.out.println("Valor minimo - " +  "Id: " + sensorMinValor.getId() + "Tipo" + sensorMinValor.getTipo() + "Valor: " + sensorMinValor.getValor());
    }
}
