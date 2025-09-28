public class PruebaSistemaIoT {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== INICIO PRUEBA SISTEMA IoT ===\n");

        // Configuración inicial
        GestorSensores gestor = GestorSensores.obtenerInstancia();
        NotificadorAlertas notificador = NotificadorAlertas.obtenerInstancia();

        // Registrar observadores
        notificador.registrarObservador(new NotificadorEmail());
        notificador.registrarObservador(new NotificadorDashboard());
        notificador.registrarObservador(new NotificadorSMS());
        notificador.registrarObservador(new RegistradorLogs());

        // Crear sensores
        Sensor sensorTemperatura = new Sensor("TEMP-001", "temperatura", 25.0, "Sala de Máquinas A");
        Sensor sensorVibracion = new Sensor("VIB-001", "vibracion", 2.0, "Motor Principal");
        Sensor sensorEnergia = new Sensor("ENER-001", "energia", 800.0, "Subestación Eléctrica");
        Sensor sensorCalidadAire = new Sensor("AIRE-001", "aire", 10, "Mexicali");

        gestor.registrarSensor(sensorTemperatura);
        gestor.registrarSensor(sensorVibracion);
        gestor.registrarSensor(sensorEnergia);
        gestor.registrarSensor(sensorCalidadAire);

        System.out.println("\n=== 🔬 SIMULANDO LECTURAS DE SENSORES ===\n");

        // Simular lecturas normales
        System.out.println("--- Lecturas Normales ---");
        gestor.actualizarValorSensor("TEMP-001", 45.0);
        Thread.sleep(1000);

        // Temperatura crítica
        System.out.println("\n--- Temperatura Crítica ---");
        gestor.actualizarValorSensor("TEMP-001", 85.0);
        Thread.sleep(1000);

        // Vibración peligrosa
        System.out.println("\n--- Vibración Peligrosa ---");
        gestor.actualizarValorSensor("VIB-001", 6.5);
        Thread.sleep(1000);

        System.out.println("\n--- Calidad aire buena ---");
        gestor.actualizarValorSensor("AIRE-001", 50);
        Thread.sleep(1000);

        System.out.println("\n--- Calidad aire mala ---");
        gestor.actualizarValorSensor("AIRE-001", 110);
        Thread.sleep(1000);

        // Cambiar estrategia en tiempo de ejecución
        System.out.println("TEMP");
        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisTemperatura());
        gestor.actualizarValorSensor("TEMP-001", 45.0);

        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisTemperatura());
        gestor.actualizarValorSensor("TEMP-001", 85.0);

        System.out.println("Aire");
        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisCalidadAire());
        gestor.actualizarValorSensor("AIRE-001", 50);

        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisCalidadAire());
        gestor.actualizarValorSensor("AIRE-001", 150);

        System.out.println("\n--- Cambio de Estrategia ---");
        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisVibracion());
        gestor.actualizarValorSensor("VIB-001", 3.5);

        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisTemperatura());
        gestor.actualizarValorSensor("TEMP-001", 45.0);

        // Alto consumo energético
        System.out.println("\n--- Alto Consumo Energético ---");
        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisEnergia());
        gestor.actualizarValorSensor("ENER-001", 1200.0);

        System.out.println("\n=== 📊 ESTADÍSTICAS FINALES ===");
        System.out.println("Sensores registrados: " + gestor.obtenerCantidadSensores());
        System.out.println("Observadores activos: " + notificador.obtenerCantidadObservadores());

        System.out.println("\n=== 🏁 FIN PRUEBA SISTEMA IoT ===");
    }
}

