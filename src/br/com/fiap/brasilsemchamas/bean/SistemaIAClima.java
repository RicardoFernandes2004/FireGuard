package br.com.fiap.brasilsemchamas.bean;

import javax.swing.*;

/**
 * Classe que representa um sistema de IA especializado na análise de clima.
 * Herda comportamento da classe abstrata SistemaIA.
 */
public class SistemaIAClima extends SistemaIA {

    private Clima clima;

    /**
     * Construtor que inicializa a versão do modelo e os dados climáticos.
     *
     * @param versaoModelo     versão do modelo da IA
     * @param temperatura      valor da temperatura atual
     * @param umidade          valor da umidade atual
     * @param velocidadeVento  valor da velocidade do vento atual
     */
    public SistemaIAClima(String versaoModelo, float temperatura, float umidade, float velocidadeVento) {
        super(versaoModelo);
        clima = new Clima(temperatura, umidade, velocidadeVento);
    }

    /**
     * Retorna uma análise textual simples sobre o clima atual.
     *
     * @return String com análise geral do clima
     */
    public String analisarClima() {
        double temperatura = clima.getTemperatura();
        double umidade = clima.getUmidade();
        double vento = clima.getVelocidadeVento();

        String analise = "Análise Preliminar do Clima:\n";

        if (temperatura < 10) {
            analise += "Clima frio. ";
        } else if (temperatura <= 30) {
            analise += "Temperatura agradável. ";
        } else {
            analise += "Clima quente. ";
        }

        if (umidade < 30) {
            analise += "Ar seco. ";
        } else if (umidade <= 70) {
            analise += "Umidade moderada. ";
        } else {
            analise += "Alta umidade. ";
        }

        if (vento < 20) {
            analise += "Ventos calmos.";
        } else if (vento <= 60) {
            analise += "Ventos moderados.";
        } else {
            analise += "Ventos fortes! Alerta de segurança.";
        }

        return analise;
    }

    /**
     * Avalia o risco de incêndio com base nas condições climáticas.
     *
     * @return String indicando o nível de risco: ALTO, MODERADO ou BAIXO
     */
    public String analisarDados() {
        float temperatura = clima.getTemperatura();
        float umidade = clima.getUmidade();
        float vento = clima.getVelocidadeVento();

        String risco = "Risco: ";

        if (temperatura > 35 && umidade < 30 && vento > 30) {
            risco += "ALTO";
        } else if (temperatura > 25 && umidade < 50) {
            risco += "MODERADO";
        } else {
            risco += "BAIXO";
        }

        return risco;
    }

    /**
     * Calcula o risco numérico de incêndio com base nos dados climáticos.
     *
     * @return valor de 0.0 a 3.0 indicando o risco
     */
    public double calcularRiscoIncendio() {
        double risco = 0.0;

        if (clima.getTemperatura() > 30) {
            risco += 1.0;
        }
        if (clima.getUmidade() < 40) {
            risco += 1.0;
        }
        if (clima.getVelocidadeVento() > 25) {
            risco += 1.0;
        }

        return risco;
    }

    /**
     * Emite alerta visual com base no risco calculado.
     * Exibe mensagens diferentes dependendo do nível de risco.
     */
    @Override
    public void emitirAlerta() {
        double risco = calcularRiscoIncendio();

        if (risco == 3.0) {
            JOptionPane.showMessageDialog(null, "alerta: Risco de incêndio alto.", "ALERTA", JOptionPane.WARNING_MESSAGE);
        } else if (risco == 2.0) {
            JOptionPane.showMessageDialog(null, "Atenção: Risco moderado de incêndio.", "ALERTA", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Sem risco de incêndio.", "Alerta", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
