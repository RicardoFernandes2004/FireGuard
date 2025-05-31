package br.com.fiap.fireguard.bean;

import javax.swing.*;

/**
 * Representa um sistema de inteligência artificial capaz de analisar dados
 * e identificar riscos com base em palavras-chave.
 */
public abstract class SistemaIA {

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
     * @param analise texto a ser avaliado.
     * @return nível de risco numérico.
     */
    public int identificarRisco(String analise) {
        int risco = 0;
        String palavra = "";

        for (int i = 0; i < analise.length(); i++) {
            String c = analise.substring(i, i + 1);
            if (c.equals(";")) {
                if (palavra.equals("fogo")) {
                    risco += 5;
                } else if (palavra.equals("fumaça")) {
                    risco += 3;
                } else if (palavra.equals("calor")) {
                    risco += 2;
                } else if (palavra.equals("vento")) {
                    risco += 1;
                }
                palavra = "";
            } else {
                palavra += c;
            }
        }

        if (!palavra.equals("")) {
            if (palavra.equals("fogo")) {
                risco += 5;
            } else if (palavra.equals("fumaça")) {
                risco += 3;
            } else if (palavra.equals("calor")) {
                risco += 2;
            } else if (palavra.equals("vento")) {
                risco += 1;
            }
        }

        String nivel = "baixo";
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
