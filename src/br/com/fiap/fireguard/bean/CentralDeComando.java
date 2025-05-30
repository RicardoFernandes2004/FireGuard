package br.com.fiap.fireguard.bean;

import javax.swing.*;

public class CentralDeComando {

    // Atributos privados
    private int quantidadeDronesDisponiveis;
    private String regiaoMonitorada;

    // Construtor
    public CentralDeComando(int quantidadeDronesDisponiveis, String regiaoMonitorada) {
        setQuantidadeDronesDisponiveis(quantidadeDronesDisponiveis);
        this.regiaoMonitorada = regiaoMonitorada;
    }

    // Getters e Setters
    public int getQuantidadeDronesDisponiveis() {
        return quantidadeDronesDisponiveis;
    }

    public void setQuantidadeDronesDisponiveis(int quantidadeDronesDisponiveis) {
        try {
            if (quantidadeDronesDisponiveis <= 0) {
                throw new Exception("Quantidade de drones disponiveis não pode ser menor que zero");
            }
            this.quantidadeDronesDisponiveis = quantidadeDronesDisponiveis;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            setQuantidadeDronesDisponiveis(Integer.parseInt(JOptionPane.showInputDialog(null,"Insira a quantidade de drones disponiveis novamente: ", "Quantidade de Drones", JOptionPane.QUESTION_MESSAGE)));
        }

    }

    public String getRegiaoMonitorada() {
        return regiaoMonitorada;
    }

    public void setRegiaoMonitorada(String regiaoMonitorada) {
        this.regiaoMonitorada = regiaoMonitorada;
    }

    public void receberAlerta(String alerta) {
        JOptionPane.showMessageDialog(null,"ALERTA RECEBIDO! \n" + alerta);
    }

    public void enviarDroneParaPatrulha() {
        if (getQuantidadeDronesDisponiveis() > 0) {
            this.quantidadeDronesDisponiveis--;
            System.out.println("Drone enviado para patrulha em " + getRegiaoMonitorada() + ". Drones restantes: " + getQuantidadeDronesDisponiveis());
        } else {
            System.out.println("Nenhum drone disponível para patrulha");
        }
    }

    public void atualizarStatusDrone() {
        quantidadeDronesDisponiveis++;
        System.out.println("Drone retornou. Total disponível: " + getQuantidadeDronesDisponiveis());
    }
}
