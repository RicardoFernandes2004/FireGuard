package br.com.fiap.fireguard.bean;

import javax.swing.*;

public class SistemaIA {
    protected String versaoModelo;

    public SistemaIA(String versaoModelo) {
        this.versaoModelo = versaoModelo;
    }

    public String getVersaoModelo() {
        return versaoModelo;
    }

    public void setVersaoModelo(String versaoModelo) {
        this.versaoModelo = versaoModelo;
    }

    public boolean isVersaoModeloAtualizado(String modeloAtual) {
        return versaoModelo.equals(modeloAtual);
    }

    public String analisarDados(String dados) {
        return "Análise realizada nos dados: " + dados;
    }

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

    public void emitirAlerta() {
        JOptionPane.showMessageDialog(null, "Alerta, situação exige atenção imediata!", null, JOptionPane.WARNING_MESSAGE);
    }
}
