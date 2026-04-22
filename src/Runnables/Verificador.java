package Runnables;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.Sensors;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import coretrackapp.TelaInicialController;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import oshi.hardware.CentralProcessor;

public class Verificador implements Runnable {

    private boolean pausa = false;
    private Supplier<TelaInicialController> controllerSupplier; // Serve para apontar sempre ao controller novo

    //função de pausar e continuar a verificação
    public synchronized void pausar() {
        pausa = true;
    }

    public synchronized void continuar() {
        pausa = false;
        notify();
    }

    public Verificador(Supplier<TelaInicialController> controllerSupplier) {
        this.controllerSupplier = controllerSupplier;
    }

    //execução do Runnable que rodará em loop
    @Override
    public void run() {
        coretrackapp.Globais.reset();
        while (true) {
            synchronized (this) {
                while (pausa) {
                    try {
                        wait(); // pausa aqui
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            // verificar desempenho
            SystemInfo si = new SystemInfo();

            GlobalMemory memory = si.getHardware().getMemory();
            OperatingSystem os = si.getOperatingSystem();

            double RAMt = memory.getTotal() / (1024.0 * 1024.0 * 1024.0);
            BigDecimal bdt = new BigDecimal(RAMt).setScale(2, RoundingMode.HALF_UP);
            double RAMtf = bdt.doubleValue();
            
            double usoRAM = (memory.getTotal() - memory.getAvailable())/ 1024.0 / 1024.0 / 1024.0;
            
            double RAMp = (double) usoRAM / RAMt;
            BigDecimal bd = new BigDecimal(usoRAM).setScale(2, RoundingMode.HALF_UP);
            double usoRAMf = bd.doubleValue();
            
            //tempo ativo do computador
            long totalSegundos = os.getSystemUptime();
            long horas = totalSegundos / 3600;
            long minutos = (totalSegundos % 3600) / 60;
            long segundos = totalSegundos % 60;
            String tempo = String.format("%02d:%02d:%02d", horas, minutos, segundos);

            // verificar armazenamento
            double memoria = 0;
            double memoriaUso = 0;
            FileSystem fs = si.getOperatingSystem().getFileSystem();

            for (OSFileStore disk : fs.getFileStores()) { // roda para cada partição
                long total = disk.getTotalSpace();
                long livre = disk.getUsableSpace();
                long usado = total - livre;

                memoria += total / (1024 * 1024 * 1024);
                memoriaUso += usado / (1024 * 1024 * 1024);
            }
            double memTotal = memoria;
            double memUsada = memoriaUso; // necessário para usar dentro do Platform.runLater, lambda só aceita variaveis finais

            // verificar temperatura
            HardwareAbstractionLayer hal = si.getHardware();
            Sensors sensors = hal.getSensors();
            double temp = sensors.getCpuTemperature();
            String tempV;
            if (temp == 0) {
                tempV = "Temperatura indisponível";
            } else {
                tempV = temp + "C゜";
            }
            // verificar cpu
            CentralProcessor cpu = si.getHardware().getProcessor();
            long[] ticks = cpu.getSystemCpuLoadTicks();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Verificador.class.getName()).log(Level.SEVERE, null, ex);
            }
            double usoCPU = cpu.getSystemCpuLoadBetweenTicks(ticks);

            // verificar se a Tela Inicial está aberta
            TelaInicialController c = coretrackapp.Globais.getController();
            if (c != null && c.JanelaAberta()) {
                Platform.runLater(() -> {
                    c.atualizarStatus(
                            String.format("%.2f", usoCPU),
                            usoCPU,
                            usoRAMf + "GB/" + RAMtf + "GB",
                            RAMp,
                            memUsada + "GB/" + memTotal + "GB",
                            memUsada / memTotal,
                            tempo,
                            tempV
                    );
                });
            }
            //modificar os valores em globais
            coretrackapp.Globais.setRAM(usoRAM);
            coretrackapp.Globais.setHD(memUsada);
            coretrackapp.Globais.setTempoUso(tempo);
            coretrackapp.Globais.incrementarMedia(usoRAM, memUsada);
            // intervalo para cada verificação
            try {
                Thread.sleep(500); // tempo dividido para o oshi ser capaz de verificar o uso da cpu
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
