package br.com.fiap.fireguard.bean;

import javax.swing.*;

public class Drone {

    // Atributos privados
    private int id;
    private String localizacaoAtual;
    private String status;
    private int bateria;

    // Construtor
    public Drone(int id, int bateria) {
        setId(id);
        this.localizacaoAtual = "Base";
        this.status = "Pronto para operação";
        setBateria(bateria);
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        try {
            if (id < 0){
                throw new Exception("ID deve ser maior que zero");
            }
            this.id = id;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            setId(Integer.parseInt(JOptionPane.showInputDialog("Digite um ID Válido: ")));
        }

    }

    public String getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    private void setLocalizacaoAtual() {
        this.localizacaoAtual = JOptionPane.showInputDialog(null,"Digite a nova localização", "Localização atual", JOptionPane.QUESTION_MESSAGE);
    }

    public String getStatus() {
        return status;
    }

    // setter sem parâmetro pq ele precisa de um menu próprio
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

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        try {
            if (bateria > 1 && bateria < 100) {
                this.bateria = bateria;
            }else{
                throw new Exception("Bateria deve ser entre 1 e 100");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            setBateria(Integer.parseInt(JOptionPane.showInputDialog("Digite a bateria novamente: ")));
        }


    }

    public void monitorarArea() {
        setLocalizacaoAtual();
        JOptionPane.showMessageDialog(null,
                "Drone " + getId() + " está monitorando a área em " + getLocalizacaoAtual());
    }

    public void detectarIncendio() {
        JOptionPane.showMessageDialog(null,
                "Drone " + getId() + " está analisando possíveis focos de incêndio...");
    }

    public String enviarAlerta() {
        return "Risco detectado pela unidade " + getId();
    }

    public void realizarPatrulha() {
        JOptionPane.showMessageDialog(null,
                "Drone " + getId() + " está realizando patrulha na região de " + getLocalizacaoAtual());
    }

    public void retornar() {
        this.localizacaoAtual = "Base";
        JOptionPane.showMessageDialog(null,
                "Drone " + getId() + " retornando à base.");
    }

}
