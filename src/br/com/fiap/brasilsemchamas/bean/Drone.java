/**
 * Ricardo Fernandes de Aquino (RM: 554597)
 * Isadora de Morais Meneghetti (RM: 556326)
 * Khadija do Rocio Vieira de Lima (RM: 558971)
 * */
package br.com.fiap.brasilsemchamas.bean;

import javax.swing.*;

/**
 * Representa um drone utilizado para monitoramento ambiental.
 * Contém ID, localização, status e nível de bateria.
 */
public class Drone {

    private int id;
    private String localizacaoAtual;
    private String status;
    private int bateria;

    /**
     * Cria um drone com ID e nível de bateria inicial.
     * A localização padrão é "Base" e o status é "Pronto para operação".
     * @param id ID do drone.
     * @param bateria Nível inicial de bateria.
     */
    public Drone(int id, int bateria) {
        setId(id);
        this.localizacaoAtual = "Base";
        this.status = "Pronto para operação";
        setBateria(bateria);
    }

    /**
     * Retorna o ID do drone.
     * @return ID numérico.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do drone. Deve ser positivo.
     * @param id ID numérico.
     */
    public void setId(int id) {
        try {
            if (id < 0){
                throw new Exception("ID deve ser maior que zero");
            }
            this.id = id;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            setId(Integer.parseInt(JOptionPane.showInputDialog("Digite um ID Válido: ")));
        }
    }

    /**
     * Retorna a localização atual do drone.
     * @return Localização em texto.
     */
    public String getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    /**
     * Atualiza a localização atual do drone via input do usuário.
     */
    private void setLocalizacaoAtual() {
        this.localizacaoAtual = JOptionPane.showInputDialog(null,"Digite a nova localização", "Localização atual", JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Retorna o status atual do drone.
     * @return Status em texto.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Define o status do drone a partir de um menu interativo.
     */
    public void setStatus() {
        try {
            int opcao = Integer.parseInt(JOptionPane.showInputDialog(
                    null,
                    "Selecione o status do drone:\n" +
                            "1 - Pronto para operação\n" +
                            "2 - Operando\n" +
                            "3 - Carregando",
                    "Atualizar Status",
                    JOptionPane.QUESTION_MESSAGE
            ));
            switch (opcao) {
                case 1:
                    this.status = "Pronto para operação";
                    break;
                case 2:
                    this.status = "Operando";
                    break;
                case 3:
                    this.status = "Carregando";
                    break;
                default:
                    throw new Exception("Opção inválida. Digite um número de 1 a 3.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            setStatus();
        }
    }

    /**
     * Retorna o nível de bateria do drone.
     * @return Valor entre 1 e 100.
     */
    public int getBateria() {
        return bateria;
    }

    /**
     * Define o nível de bateria do drone. Deve estar entre 1 e 100.
     * @param bateria Valor da bateria.
     */
    public void setBateria(int bateria) {
        try {
            if (bateria > 1 && bateria < 100) {
                this.bateria = bateria;
            } else {
                throw new Exception("Bateria deve ser entre 1 e 100");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            setBateria(Integer.parseInt(JOptionPane.showInputDialog("Digite a bateria novamente: ")));
        }
    }

    /**
     * Define nova localização e simula monitoramento da área.
     */
    public void monitorarArea() {
        setLocalizacaoAtual();
        JOptionPane.showMessageDialog(null,
                "Drone " + getId() + " está monitorando a área em " + getLocalizacaoAtual());
    }

    /**
     * Simula a detecção de incêndio pelo drone.
     */
    public void detectarIncendio() {
        JOptionPane.showMessageDialog(null,
                "Drone " + getId() + " está analisando possíveis focos de incêndio...");
    }

    /**
     * Retorna uma mensagem de alerta de risco detectado.
     * @return String de alerta.
     */
    public String enviarAlerta() {
        return "Risco detectado pela unidade " + getId();
    }

    /**
     * Simula a patrulha na localização atual.
     */
    public void realizarPatrulha() {
        JOptionPane.showMessageDialog(null,
                "Drone " + getId() + " está realizando patrulha na região de " + getLocalizacaoAtual());
    }

    /**
     * Retorna o drone à base.
     */
    public void retornar() {
        this.localizacaoAtual = "Base";
        JOptionPane.showMessageDialog(null,
                "Drone " + getId() + " retornando à base.");
    }
}
