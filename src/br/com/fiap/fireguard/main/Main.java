package br.com.fiap.fireguard.main;

import br.com.fiap.fireguard.bean.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Drone drone = new Drone(1, 75);
        CentralDeComando central = new CentralDeComando(3, JOptionPane.showInputDialog("Onde está localizada a central? "));
        SensorCamera sensorCamera = new SensorCamera();
        SensorFLIR sensorFLIR = new SensorFLIR();
        SistemaIA sistemaIA = new SistemaIA("3.6");
        SistemaIAClima sistemaIAClima = new SistemaIAClima("v1.0", 36, 25, 40);


        boolean continuar = true;
        while (continuar) {
            String menu = """
                     MENU PRINCIPAL:
                    1 - Ver clima atual
                    2 - Alterar clima (temperatura, umidade e vento)
                    3 - Fazer a identificação de riscos manualmente
                    4 - Ver status do drone
                    5 - Atualizar localização do drone
                    6 - Atualizar status do drone
                    7 - Enviar drone para patrulha
                    8 - Monitorar área com drone
                    9 - Sensor CAMERA - captar e processar dados
                    10 - Sensor FLIR - captar dados térmicos
                    11 - Sair
                    """;

            String escolha = JOptionPane.showInputDialog(null, menu, "FIREGUARD SYSTEM", JOptionPane.QUESTION_MESSAGE);
            if (escolha == null) break;

            switch (escolha) {
                case "1":
                    JOptionPane.showMessageDialog(null,
                            sistemaIAClima.analisarClima() +
                                    "\n\n(" + sistemaIAClima.calcularRiscoIncendio() + " ponto(s) de risco)");
                    break;

                case "2":
                    try {
                        float temp = Float.parseFloat(JOptionPane.showInputDialog("Temperatura atual:"));
                        float umi = Float.parseFloat(JOptionPane.showInputDialog("Umidade atual:"));
                        float vento = Float.parseFloat(JOptionPane.showInputDialog("Velocidade do vento:"));

                        sistemaIA = new SistemaIAClima("v1.0", temp, umi, vento);
                        JOptionPane.showMessageDialog(null, "Clima atualizado com sucesso.");
                    } catch (Exception e) {
                        // tenta pegar alguma exception fora do escopo das regras de negocio
                        JOptionPane.showMessageDialog(null, "Erro ao atualizar clima.");
                    }
                    break;


                case "3":
                    JOptionPane.showMessageDialog(null, sistemaIA.identificarRisco());
                    break;

                case "4":
                    JOptionPane.showMessageDialog(null,
                        "Drone #" + drone.getId() +
                                "\nLocal: " + drone.getLocalizacaoAtual() +
                                "\nStatus: " + drone.getStatus() +
                                "\nBateria: " + drone.getBateria() + "%");
                    break;

                case "5":
                    drone.monitorarArea();
                    break;

                case "6":
                    drone.setStatus();
                    break;

                case "7":
                    central.enviarDroneParaPatrulha();
                    drone.realizarPatrulha();
                    break;

                case "8":
                    drone.monitorarArea();
                    drone.detectarIncendio();
                    String alerta = drone.enviarAlerta();
                    central.receberAlerta(alerta);
                    break;

                case "9":
                    String dados = sensorCamera.captarDados();
                    sensorCamera.processarDados(dados);
                    JOptionPane.showMessageDialog(null, "Riscos detectados: " + sensorCamera.getRiscosIdentificados());
                    break;

                case "10":
                    int temperatura = Integer.parseInt(JOptionPane.showInputDialog("Temperatura captada:"));
                    double sensibilidade = Double.parseDouble(JOptionPane.showInputDialog("Sensibilidade térmica:"));
                    String coordenada = JOptionPane.showInputDialog("Coordenada captada (ex: 23.5,-46.6):");

                    sensorFLIR.capturarImagemTermica(temperatura, sensibilidade, coordenada);
                    JOptionPane.showMessageDialog(null, sensorFLIR.captarDados());
                    break;

                case "11":
                    int resposta = JOptionPane.showConfirmDialog(null, "Deseja sair?", "Sair", JOptionPane.YES_NO_OPTION);
                    if (resposta != JOptionPane.YES_OPTION) continuar = false;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
            }

            int resposta = JOptionPane.showConfirmDialog(null, "Deseja continuar?", "Continuar", JOptionPane.YES_NO_OPTION);
            if (resposta != JOptionPane.YES_OPTION) continuar = false;
        }

        JOptionPane.showMessageDialog(null, "Sistema encerrado.");
    }
}
