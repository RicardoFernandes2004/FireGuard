/**
 * Ricardo Fernandes de Aquino (RM: 554597)
 * Isadora de Morais Meneghetti (RM: 556326)
 * Khadija do Rocio Vieira de Lima (RM: 558971)
 * */
package br.com.fiap.brasilsemchamas.bean;

import javax.swing.*;

/**
 * Implementação de sensor térmico FLIR.
 * Captura dados como temperatura, sensibilidade térmica e coordenadas.
 */
public class SensorFLIR implements ISensor {

    private String resolucao;
    private int temperatura;
    private double sensibilidadeTermica;
    private String coordenadaLocalCaptado;

    /**
     * Construtor padrão.
     * Define valores iniciais padrão para os atributos.
     */
    public SensorFLIR() {
        this.resolucao = "640x480";
        this.temperatura = 0;
        this.sensibilidadeTermica = 0.0;
        this.coordenadaLocalCaptado = "0,0";
    }

    /**
     * Retorna a resolução da imagem térmica.
     * @return Resolução (320x240, 640x480 ou 1280x1024).
     */
    public String getResolucao() {
        return resolucao;
    }

    /**
     * Define a resolução da imagem térmica.
     * @param resolucao Valor desejado.
     */
    public void setResolucao(String resolucao) {
        try {
            if (resolucao.equals("320x240") || resolucao.equals("640x480") || resolucao.equals("1280x1024")) {
                this.resolucao = resolucao;
            } else {
                throw new Exception("Resolução inválida. Use: 320x240, 640x480 ou 1280x1024.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            setResolucao(JOptionPane.showInputDialog(null, "Digite novamente a resolução válida:", "Resolução FLIR", JOptionPane.QUESTION_MESSAGE));
        }
    }

    /**
     * Retorna a temperatura captada pelo sensor.
     * @return Temperatura em graus Celsius.
     */
    public int getTemperatura() {
        return temperatura;
    }

    /**
     * Define a temperatura captada.
     * @param temperatura Valor entre -50 e 1000.
     */
    public void setTemperatura(int temperatura) {
        try {
            if (temperatura < -50 || temperatura > 1000) {
                throw new Exception("Temperatura fora dos limites suportados (-50 a 1000°C).");
            }
            this.temperatura = temperatura;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            setTemperatura(Integer.parseInt(JOptionPane.showInputDialog(null, "Digite novamente a temperatura (-50 a 1000):", "Temperatura", JOptionPane.QUESTION_MESSAGE)));
        }
    }

    /**
     * Retorna a sensibilidade térmica do sensor.
     * @return Sensibilidade em graus Celsius.
     */
    public double getSensibilidadeTermica() {
        return sensibilidadeTermica;
    }

    /**
     * Define a sensibilidade térmica.
     * @param sensibilidadeTermica Valor entre 0.1 e 5.0.
     */
    public void setSensibilidadeTermica(double sensibilidadeTermica) {
        try {
            if (sensibilidadeTermica <= 0.0 || sensibilidadeTermica > 5.0) {
                throw new Exception("Sensibilidade térmica deve estar entre 0.1 e 5.0°C.");
            }
            this.sensibilidadeTermica = sensibilidadeTermica;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            setSensibilidadeTermica(Double.parseDouble(JOptionPane.showInputDialog(null, "Digite novamente a sensibilidade térmica (0.1 a 5.0):", "Sensibilidade", JOptionPane.QUESTION_MESSAGE)));
        }
    }

    /**
     * Retorna a coordenada onde o dado foi captado.
     * @return Coordenada em formato "x,y".
     */
    public String getCoordenadaLocalCaptado() {
        return coordenadaLocalCaptado;
    }

    /**
     * Define a coordenada do ponto analisado.
     * @param coordenadaLocalCaptado Coordenada no formato "x,y".
     */
    public void setCoordenadaLocalCaptado(String coordenadaLocalCaptado) {
        if (coordenadaLocalCaptado != null && coordenadaLocalCaptado.contains(",")) {
            this.coordenadaLocalCaptado = coordenadaLocalCaptado;
        } else {
            JOptionPane.showMessageDialog(null, "Coordenada inválida. Use o formato: x,y", "Erro", JOptionPane.ERROR_MESSAGE);
            setCoordenadaLocalCaptado(JOptionPane.showInputDialog(null, "Digite novamente a coordenada (ex: 23.5,-46.6):", "Coordenada", JOptionPane.QUESTION_MESSAGE));
        }
    }

    /**
     * Define os dados captados em uma simulação de imagem térmica.
     * @param temperatura Temperatura captada.
     * @param sensibilidadeTermica Sensibilidade térmica.
     * @param coordenadaLocalCaptado Local captado.
     */
    public void capturarImagemTermica(int temperatura, double sensibilidadeTermica, String coordenadaLocalCaptado) {
        setTemperatura(temperatura);
        setSensibilidadeTermica(sensibilidadeTermica);
        setCoordenadaLocalCaptado(coordenadaLocalCaptado);
    }

    /**
     * Retorna uma string com os dados captados pelo sensor.
     * @return Dados térmicos e coordenada.
     */
    @Override
    public String captarDados() {
        return "Dados captados: Temperatura=" + getTemperatura() + "°C, Sensibilidade=" + getSensibilidadeTermica() + "°C, Local=" + getCoordenadaLocalCaptado();
    }

    /**
     * Simula o processamento de dados recebidos pelo sensor.
     * @param dadosBrutos Dados de entrada a serem processados.
     */
    @Override
    public void processarDados(String dadosBrutos) {
        System.out.println("Processando dados brutos: " + dadosBrutos);
    }
}
