package br.com.fiap.fireguard.main;

import br.com.fiap.fireguard.bean.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SensorCamera sensorCamera = new SensorCamera();
        SensorFLIR sensorFLIR = new SensorFLIR();
        Drone drone = new Drone(1, 75);
        SistemaIAClima iaClima = new SistemaIAClima("1.0");
        CentralDeComando central = new CentralDeComando(3, "Mata Atlântica");

        while (true) {
            String opcao = JOptionPane.showInputDialog(null,
                    "Menu Principal:\n" +
                            "1 - Usar Sensor Camera\n" +
                            "2 - Usar Sensor FLIR\n" +
                            "3 - Operar Drone\n" +
                            "4 - Usar IA Clima\n" +
                            "5 - Ações da Central de Comando\n" +
                            "0 - Sair",
                    "Sistema FireGuard", JOptionPane.QUESTION_MESSAGE);

            if (opcao == null || opcao.equals("0")) break;

            switch (opcao) {
                case "1": {
                    String resolucao = JOptionPane.showInputDialog("Digite a resolução (720p, 1080p, 4K):");
                    String modo = JOptionPane.showInputDialog("Digite o modo (normal, noturno, economia):");
                    String brutos = sensorCamera.captarDados();
                    sensorCamera.processarDados(brutos);
                    JOptionPane.showMessageDialog(null, "Riscos processados: " + sensorCamera.getRiscosIdentificados());
                    break;
                }
                case "2": {
                    sensorFLIR.setResolucao(JOptionPane.showInputDialog("Digite a resolução (320x240, 640x480, 1280x1024):"));
                    sensorFLIR.setTemperatura(Integer.parseInt(JOptionPane.showInputDialog("Digite a temperatura:")));
                    sensorFLIR.setSensibilidadeTermica(Double.parseDouble(JOptionPane.showInputDialog("Digite a sensibilidade térmica:")));
                    sensorFLIR.setCoordenadaLocalCaptado(JOptionPane.showInputDialog("Digite a coordenada (x,y):"));
                    JOptionPane.showMessageDialog(null, sensorFLIR.captarDados());
                    break;
                }
                case "3": {
                    String acao = JOptionPane.showInputDialog(null,
                            "Ações do Drone:\n" +
                                    "1 - Monitorar área\n" +
                                    "2 - Detectar incêndio\n" +
                                    "3 - Realizar patrulha\n" +
                                    "4 - Retornar à base\n" +
                                    "5 - Atualizar status",
                            "Drone", JOptionPane.QUESTION_MESSAGE);
                    switch (acao) {
                        case "1":
                            drone.monitorarArea();
                            break;
                        case "2":
                            drone.detectarIncendio();
                            break;
                        case "3":
                            drone.realizarPatrulha();
                            break;
                        case "4":
                            drone.retornar();
                            break;
                        case "5":
                            drone.setStatus();
                            break;
                        default: JOptionPane.showMessageDialog(null, "Opção inválida.");
                    }
                    break;
                }
                case "4": {
                    String dados = iaClima.analisarClima();
                    if (!dados.equals("")) {
                        String analise = iaClima.analisarDados(dados);
                        double risco = iaClima.calcularRiscoIncendio();
                        JOptionPane.showMessageDialog(null, analise + "\nRisco calculado: " + risco);
                    }
                    break;
                }
                case "5": {
                    String acao = JOptionPane.showInputDialog(null,
                            "Central de Comando:\n" +
                                    "1 - Receber Alerta\n" +
                                    "2 - Enviar Drone para Patrulha\n" +
                                    "3 - Atualizar Status de Drone",
                            "Central", JOptionPane.QUESTION_MESSAGE);
                    switch (acao) {
                        case "1":
                            central.receberAlerta(drone.enviarAlerta());
                            break;
                        case "2":
                            central.enviarDroneParaPatrulha();
                            break;
                        case "3":
                            central.atualizarStatusDrone();
                            break;
                        default: JOptionPane.showMessageDialog(null, "Opção inválida.");
                    }
                    break;
                }
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        }
        JOptionPane.showMessageDialog(null, "Encerrando o sistema. Até logo!");
    }
}
