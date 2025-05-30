package br.com.fiap.fireguard.bean;

import javax.swing.*;

public class Clima {
    private float temperatura;
    private float umidade;
    private float velocidadeVento;

    public Clima(float temperatura, float umidade, float velocidadeVento) {
        setTemperatura(temperatura);
        setUmidade(umidade);
        setVelocidadeVento(velocidadeVento);
    }

    public float getTemperatura() {
        return temperatura;
    }

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

    public float getUmidade() {
        return umidade;
    }

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

    public float getVelocidadeVento() {
        return velocidadeVento;
    }

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

    public String verificarCondicoes() {
        return "Temperatura: " + temperatura + "°C, Umidade: " + umidade + "%, Vento: " + velocidadeVento + "km/h";
    }

    public String enviarDadosClimaticos() {
        return temperatura + ";" + umidade + ";" + velocidadeVento;
    }
}
