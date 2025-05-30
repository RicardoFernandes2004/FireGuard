package br.com.fiap.fireguard.bean;

public interface ISensor {
    String captarDados();
    void processarDados(String dadosBrutos);
}
