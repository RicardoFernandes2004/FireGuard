package br.com.fiap.fireguard.bean;

import javax.swing.*;

public class SensorCamera implements ISensor {

    private String resolucao;
    private String modoDeOperacao;
    private String riscosIdentificados;

    public SensorCamera() {
        this.resolucao = "1080p";
        this.modoDeOperacao = "normal";
        this.riscosIdentificados = "";
    }

    public String getResolucao() {
        return resolucao;
    }

    public void setResolucao(String resolucao) {
        try {
            if (resolucao.equals("720p") || resolucao.equals("1080p") || resolucao.equals("4K")) {
                this.resolucao = resolucao;
            } else {
                throw new Exception("Resolução inválida. Use: 720p, 1080p ou 4K.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            setResolucao(JOptionPane.showInputDialog(null, "Digite novamente a resolução (720p, 1080p ou 4K):", "Resolução", JOptionPane.QUESTION_MESSAGE));
        }
    }

    public String getModoDeOperacao() {
        return modoDeOperacao;
    }

    public void setModoDeOperacao(String modoDeOperacao) {
        try {
            if (modoDeOperacao.equals("normal") || modoDeOperacao.equals("noturno") || modoDeOperacao.equals("economia")) {
                if (modoDeOperacao.equals("noturno") && this.resolucao.equals("720p")) {
                    throw new Exception("Modo noturno requer resolução mínima de 1080p.");
                } else {
                    this.modoDeOperacao = modoDeOperacao;
                }
            } else {
                throw new Exception("Modo inválido. Use: normal, noturno ou economia.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            setModoDeOperacao(JOptionPane.showInputDialog(null, "Digite novamente o modo de operação (normal, noturno ou economia):", "Modo de Operação", JOptionPane.QUESTION_MESSAGE));
        }
    }

    public String getRiscosIdentificados() {
        return riscosIdentificados;
    }

    private void capturarImagem(String resolucao, String modoDeOperacao, String dadosSimulados) {
        setResolucao(resolucao);
        setModoDeOperacao(modoDeOperacao);
        this.riscosIdentificados = dadosSimulados;
    }

    @Override
    public String captarDados() {
        return JOptionPane.showInputDialog(null, "Digite os riscos identificados na imagem (ex: fogo;calor;vento;)", "Riscos", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void processarDados(String dadosBrutos) {
        String palavra = "";
        String riscos = "";

        for (int i = 0; i < dadosBrutos.length(); i++) {
            String c = dadosBrutos.substring(i, i + 1);
            if (c.equals(";")) {
                if (palavra.equals("fogo")) {
                    riscos += "risco_incendio;";
                } else if (palavra.equals("fumaça")) {
                    riscos += "risco_inalacao;";
                } else if (palavra.equals("calor")) {
                    riscos += "risco_sobreaquecimento;";
                } else if (palavra.equals("vento")) {
                    riscos += "instabilidade;";
                }
                palavra = "";
            } else {
                palavra += c;
            }
        }

        this.riscosIdentificados = riscos;
    }
}
