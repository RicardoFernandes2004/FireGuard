package br.com.fiap.fireguard.bean;

import javax.swing.*;

/**
 * Gerencia a operação de drones em uma determinada região.
 * Controla a quantidade disponível, recebe alertas e emite comandos.
 */
public class CentralDeComando {

    /** Quantidade de drones prontos para operação. */
    private int quantidadeDronesDisponiveis;

    /** Região atualmente monitorada pelos drones. */
    private String regiaoMonitorada;

    /**
     * Construtor da Central de Comando.
     *
     * @param quantidadeDronesDisponiveis número inicial de drones disponíveis.
     * @param regiaoMonitorada nome da região sob vigilância.
     */
    public CentralDeComando(int quantidadeDronesDisponiveis, String regiaoMonitorada) {
        setQuantidadeDronesDisponiveis(quantidadeDronesDisponiveis);
        this.regiaoMonitorada = regiaoMonitorada;
    }

    /**
     * Retorna a quantidade de drones disponíveis.
     *
     * @return quantidade de drones prontos para uso.
     */
    public int getQuantidadeDronesDisponiveis() {
        return quantidadeDronesDisponiveis;
    }

    /**
     * Define a quantidade de drones disponíveis com validação.
     *
     * @param quantidadeDronesDisponiveis novo valor.
     */
    public void setQuantidadeDronesDisponiveis(int quantidadeDronesDisponiveis) {
        try {
            if (quantidadeDronesDisponiveis <= 0) {
                throw new Exception("Quantidade de drones disponíveis não pode ser menor que zero");
            }
            this.quantidadeDronesDisponiveis = quantidadeDronesDisponiveis;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            setQuantidadeDronesDisponiveis(Integer.parseInt(JOptionPane.showInputDialog(
                    null, "Insira a quantidade de drones disponíveis novamente: ",
                    "Quantidade de Drones", JOptionPane.QUESTION_MESSAGE)));
        }
    }

    /**
     * Retorna a região monitorada.
     *
     * @return nome da região.
     */
    public String getRegiaoMonitorada() {
        return regiaoMonitorada;
    }

    /**
     * Define a região monitorada.
     *
     * @param regiaoMonitorada novo nome da região.
     */
    public void setRegiaoMonitorada(String regiaoMonitorada) {
        this.regiaoMonitorada = regiaoMonitorada;
    }

    /**
     * Exibe uma mensagem de alerta recebido.
     *
     * @param alerta texto do alerta.
     */
    public void receberAlerta(String alerta) {
        JOptionPane.showMessageDialog(null, "ALERTA RECEBIDO! \n" + alerta);
    }

    /**
     * Envia um drone para patrulhar a região monitorada.
     * Reduz a quantidade disponível se houver drones.
     */
    public void enviarDroneParaPatrulha() {
        if (getQuantidadeDronesDisponiveis() > 0) {
            this.quantidadeDronesDisponiveis--;
            System.out.println("Drone enviado para patrulha em " + getRegiaoMonitorada()
                    + ". Drones restantes: " + getQuantidadeDronesDisponiveis());
        } else {
            System.out.println("Nenhum drone disponível para patrulha");
        }
    }

    /**
     * Atualiza o status de um drone que retornou, aumentando a disponibilidade.
     */
    public void atualizarStatusDrone() {
        quantidadeDronesDisponiveis++;
        System.out.println("Drone retornou. Total disponível: " + getQuantidadeDronesDisponiveis());
    }
}
