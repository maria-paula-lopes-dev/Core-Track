package Runnables;

import Classes.Notificacoes;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import coretrackapp.Globais;
import java.time.LocalDateTime;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

public class NotificadorDesempenho implements Runnable {

    private boolean notifPausa = false; // váriavel para impedir que várias notificações sejam enviadas de uma vez

    @Override
    public void run() {
        String titulo = "";
        DAOs.NotificacoesDAO notificar = new DAOs.NotificacoesDAO();
        Notificacoes n = new Notificacoes();

        SystemInfo si = new SystemInfo();

        GlobalMemory memory = si.getHardware().getMemory();
        OperatingSystem os = si.getOperatingSystem();

        long RAMt = memory.getTotal() / (1024 * 1024 * 1024);
        double memoria = 0;
        FileSystem fs = si.getOperatingSystem().getFileSystem();

        for (OSFileStore disk : fs.getFileStores()) { // roda para cada partição
            long total = disk.getTotalSpace();

            memoria += total / (1024 * 1024 * 1024);
        }

        while (true) {
            while (notifPausa) { // quando uma notificação for emitida o programa espera por 10 segundos antes de continuar
                try {
                    notifPausa = false;
                    Thread.sleep(10000);
                } catch (Exception e) {

                }
            }
            double usoHD = coretrackapp.Globais.getHD();
            double usoRAM = coretrackapp.Globais.getRAM();
            double HDp = usoHD / memoria * 100;
            double RAMp = usoRAM / RAMt * 100;

            if (RAMp > 60 && RAMp <= 90 && !notifPausa) {
                n = new Notificacoes(Globais.getId_usuario(), "Sua RAM passou de 60% de uso", "ALERTA");
                titulo = "Uso RAM elevado";
                notifPausa = true;
                System.out.println("Alô");
            } else if (RAMp > 90 && !notifPausa) {
                n = new Notificacoes(Globais.getId_usuario(), "Sua RAM passou de 90% de uso!", "CRITICO");
                titulo = "Uso RAM crítico";
                notifPausa = true;
            }
            if (HDp > 60 && HDp <= 90 && !notifPausa) {
                n = new Notificacoes(Globais.getId_usuario(), "Seu armazenamento passou de 60% de uso", "ALERTA");
                titulo = "Uso HD elevado";
                notifPausa = true;
            } else if (RAMp > 90 && !notifPausa) {
                n = new Notificacoes(Globais.getId_usuario(), "Sua RAM passou de 90% de  uso", "CRITICO");
                titulo = "Uso HD crítico";
                notifPausa = true;
            }
            if (notifPausa) { 
                notificar.inserir(n, titulo);
            }
            try {
                Thread.sleep(1000); //1 segundo de intervalo entre checagem das notificações
            } catch (Exception e) {
                printStackTrace();
            }
        }
    }

}

