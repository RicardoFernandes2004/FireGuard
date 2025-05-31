package br.com.fiap.fireguard.bean;

import javax.swing.*;

/**
 * Representa um sistema de inteligência artificial capaz de analisar dados
 * e identificar riscos com base em palavras-chave.
 */
public class SistemaIA {

    /** Versão atual do modelo da IA. */
    protected String versaoModelo;

    /**
     * Construtor da IA.
     *
     * @param versaoModelo versão do modelo utilizado.
     */
    public SistemaIA(String versaoModelo) {
        this.versaoModelo = versaoModelo;
    }

    /**
     * Retorna a versão do modelo da IA.
     *
     * @return versão atual.
     */
    public String getVersaoModelo() {
        return versaoModelo;
    }

    /**
     * Define a versão do modelo da IA.
     *
     * @param versaoModelo nova versão.
     */
    public void setVersaoModelo(String versaoModelo) {
        this.versaoModelo = versaoModelo;
    }

    /**
     * Verifica se a versão do modelo está atualizada.
     *
     * @param modeloAtual versão de referência.
     * @return true se for igual, false se diferente.
     */
    public boolean isVersaoModeloAtualizado(String modeloAtual) {
        return versaoModelo.equals(modeloAtual);
    }

    /**
     * Realiza uma análise genérica dos dados fornecidos.
     *
     * @param dados dados de entrada.
     * @return mensagem indicando que a análise foi feita.
     */
    public String analisarDados(String dados) {
        return "Análise realizada nos dados: " + dados;
    }

    /**
     * Identifica o risco com base nas palavras presentes na análise.
     *
     * @return nível de risco numérico.
     */
    public int identificarRisco() {
        int risco = 0;
        String nivel = "baixo";

        do {
            String palavra = JOptionPane.showInputDialog("Digite o risco identificado (fogo, fumaça, calor, vento):");

            if (palavra != null && !palavra.isEmpty()) {
                if (palavra.equalsIgnoreCase("fogo")) {
                    risco += 5;
                } else if (palavra.equalsIgnoreCase("fumaça")) {
                    risco += 3;
                } else if (palavra.equalsIgnoreCase("calor")) {
                    risco += 2;
                } else if (palavra.equalsIgnoreCase("vento")) {
                    risco += 1;
                } else {
                    JOptionPane.showMessageDialog(null, "Risco não reconhecido.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }

            int continuar = JOptionPane.showConfirmDialog(null, "Deseja adicionar outro risco?", "Continuar?", JOptionPane.YES_NO_OPTION);
            if (continuar != JOptionPane.YES_OPTION) break;

        } while (true);


        if (risco >= 10) {
            nivel = "crítico";
        } else if (risco >= 7) {
            nivel = "alto";
        } else if (risco >= 4) {
            nivel = "médio";
        }

        JOptionPane.showMessageDialog(null, "Nível de risco identificado: " + nivel + " (" + risco + ")");

        if (nivel.equals("alto") || nivel.equals("crítico")) {
            emitirAlerta();
        }

        return risco;
    }

    /**
     * Exibe um alerta em casos de risco elevado.
     */
    public void emitirAlerta() {
        JOptionPane.showMessageDialog(null, "Alerta, situação exige atenção imediata!", null, JOptionPane.WARNING_MESSAGE);
    }
}
