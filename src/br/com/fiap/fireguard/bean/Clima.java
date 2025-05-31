package br.com.fiap.fireguard.bean;

import javax.swing.*;

/**
 * Representa as condições climáticas monitoradas por sensores.
 * Inclui temperatura, umidade e velocidade do vento.
 */
public class Clima {
    private float temperatura;
    private float umidade;
    private float velocidadeVento;

    /**
     * Construtor que inicializa todos os parâmetros do clima.
     *
     * @param temperatura     Temperatura em graus Celsius
     * @param umidade         Umidade relativa do ar (%)
     * @param velocidadeVento Velocidade do vento (km/h)
     */
    public Clima(float temperatura, float umidade, float velocidadeVento) {
        setTemperatura(temperatura);
        setUmidade(umidade);
        setVelocidadeVento(velocidadeVento);
    }

    /**
     * Retorna a temperatura atual.
     *
     * @return temperatura em °C
     */
    public float getTemperatura() {
        return temperatura;
    }

    /**
     * Define a temperatura após validação.
     *
     * @param temperatura Temperatura em °C (-50 a 60)
     */
    public void setTemperatura(float temperatura) {
        try {
            if (temperatura < -50 || temperatura > 60) {
                throw new Exception("Temperatura fora da faixa permitida (-50°C a 60°C).");
            }
            this.temperatura = temperatura;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            setTemperatura(Float.parseFloat(JOptionPane.showInputDialog("Digite a temperatura novamente: ")));
        }
    }

    /**
     * Retorna a umidade atual.
     *
     * @return umidade relativa do ar (%)
     */
    public float getUmidade() {
        return umidade;
    }

    /**
     * Define a umidade após validação.
     *
     * @param umidade Umidade (%)
     */
    public void setUmidade(float umidade) {
        try {
            if (umidade < 0 || umidade > 100) {
                throw new Exception("Umidade deve estar entre 0% e 100%.");
            }
            this.umidade = umidade;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            setUmidade(Float.parseFloat(JOptionPane.showInputDialog("Digite a umidade novamente: ")));
        }
    }

    /**
     * Retorna a velocidade do vento atual.
     *
     * @return velocidade do vento (km/h)
     */
    public float getVelocidadeVento() {
        return velocidadeVento;
    }

    /**
     * Define a velocidade do vento após validação.
     *
     * @param velocidadeVento Velocidade do vento (km/h)
     */
    public void setVelocidadeVento(float velocidadeVento) {
        try {
            if (velocidadeVento < 0 || velocidadeVento > 150) {
                throw new Exception("Velocidade do vento deve estar entre 0 e 150 km/h.");
            }
            this.velocidadeVento = velocidadeVento;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            setVelocidadeVento(Float.parseFloat(JOptionPane.showInputDialog("Digite a velocidade do vento novamente: ")));
        }
    }

    /**
     * Retorna uma string com os valores atuais das condições climáticas.
     *
     * @return resumo das condições
     */
    public String verificarCondicoes() {
        return "Temperatura: " + temperatura + "°C, Umidade: " + umidade + "%, Vento: " + velocidadeVento + "km/h";
    }

    /**
     * Retorna os dados climáticos em formato serializado (separado por ponto e vírgula).
     *
     * @return string com os dados
     */
    public String enviarDadosClimaticos() {
        return temperatura + ";" + umidade + ";" + velocidadeVento;
    }
}
