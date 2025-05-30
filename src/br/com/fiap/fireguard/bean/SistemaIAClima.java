package br.com.fiap.fireguard.bean;

import javax.swing.*;

public class SistemaIAClima extends SistemaIA {

    public SistemaIAClima(String versaoModelo) {
        super(versaoModelo);
    }

    public String analisarClima() {
        String temperaturaStr = JOptionPane.showInputDialog(null, "Temperatura em °C:", "Dados Climáticos", JOptionPane.INFORMATION_MESSAGE);
        String umidadeStr = JOptionPane.showInputDialog(null, "Umidade em %:", "Dados Climáticos", JOptionPane.INFORMATION_MESSAGE);
        String ventoStr = JOptionPane.showInputDialog(null, "Velocidade do vento em km/h:", "Dados Climáticos", JOptionPane.INFORMATION_MESSAGE);

        try {
            float temperatura = Float.parseFloat(temperaturaStr);
            float umidade = Float.parseFloat(umidadeStr);
            float vento = Float.parseFloat(ventoStr);

            return temperatura + ";" + umidade + ";" + vento;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao processar dados climáticos. Verifique os valores informados.");
            return "";
        }
    }

    @Override
    public String analisarDados(String dadosAnalisadosClima) {
        // Retorna os dados formatados com nomes
        String dado = "";
        String resposta = "";
        int contador = 0;

        for (int i = 0; i < dadosAnalisadosClima.length(); i++) {
            String c = dadosAnalisadosClima.substring(i, i + 1);
            if (c.equals(";")) {
                if (contador == 0) {
                    resposta += "Temperatura: " + dado + "°C, ";
                } else if (contador == 1) {
                    resposta += "Umidade: " + dado + "%, ";
                } else if (contador == 2) {
                    resposta += "Vento: " + dado + "km/h";
                }
                dado = "";
                contador++;
            } else {
                dado += c;
            }
        }

        return resposta;
    }

    public double calcularRiscoIncendio() {
        try {
            String clima = analisarClima();
            String dado = "";
            int contador = 0;

            float temperatura = 0;
            float umidade = 0;
            float vento = 0;

            for (int i = 0; i < clima.length(); i++) {
                String c = clima.substring(i, i + 1);
                if (c.equals(";")) {
                    if (contador == 0) {
                        temperatura = Float.parseFloat(dado);
                    } else if (contador == 1) {
                        umidade = Float.parseFloat(dado);
                    } else if (contador == 2) {
                        vento = Float.parseFloat(dado);
                    }
                    dado = "";
                    contador++;
                } else {
                    dado += c;
                }
            }

            double risco = (temperatura * 0.4) - (umidade * 0.3) + (vento * 0.2);
            if (risco < 0) risco = 0;
            return risco;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao calcular risco de incêndio.");
            return -1;
        }
    }
}
