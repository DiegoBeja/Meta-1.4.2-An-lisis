public class SistemaMonitoreoIoT {
    public static void main(String[] args) {
        // 1. Inicializar gestor y notificador
        GestorSensores gestor = GestorSensores.obtenerInstancia();
        NotificadorAlertas notificador = NotificadorAlertas.obtenerInstancia();

        // 2. Registrar observadores
        notificador.registrarObservador(new NotificadorEmail());
        notificador.registrarObservador(new NotificadorDashboard());
        notificador.registrarObservador(new NotificadorSMS());
        notificador.registrarObservador(new RegistradorLogs());

        // 3. Crear sensores de diferentes tipos
        Sensor sTemp = new Sensor("S1", "temperatura", 45.0, "Sala de máquinas");
        Sensor sVib = new Sensor("S2", "vibracion", 2.5, "Turbina");
        Sensor sEner = new Sensor("S3", "energia", 800.0, "Planta principal");
        Sensor sAire = new Sensor("S4", "aire", 40.0, "Zona norte");
        Sensor sHum = new Sensor("S5", "humedad", 25.0, "Almacén");

        // 4. Registrar sensores
        gestor.registrarSensor(sTemp);
        gestor.registrarSensor(sVib);
        gestor.registrarSensor(sEner);
        gestor.registrarSensor(sAire);
        gestor.registrarSensor(sHum);

        // 5. PRUEBA: activar distintas estrategias y actualizar valores

        // Estrategia Temperatura
        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisTemperatura());
        gestor.actualizarValorSensor("S1", 85.0); // CRÍTICO
        gestor.actualizarValorSensor("S1", 65.0); // ADVERTENCIA

        // Estrategia Vibración
        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisVibracion());
        gestor.actualizarValorSensor("S2", 4.0); // ADVERTENCIA
        gestor.actualizarValorSensor("S2", 6.0); // CRÍTICO

        // Estrategia Energía
        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisEnergia());
        gestor.actualizarValorSensor("S3", 1200.0); // ADVERTENCIA

        // Estrategia Calidad Aire
        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisCalidadAire());
        gestor.actualizarValorSensor("S4", 110.0); // CRÍTICO
        gestor.actualizarValorSensor("S4", 60.0);  // MODERADO

        // Estrategia Humedad
        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisHumedad());
        gestor.actualizarValorSensor("S5", 20.0); // BAJA
        gestor.actualizarValorSensor("S5", 65.0); // ALTA

        System.out.println("\n✅ Pruebas completadas.");
    }
}
