package br.com.fiap.brasilsemchamas.bean;

/**
 * Interface para sensores do sistema.
 * Define métodos para captura e processamento de dados.
 */
public interface ISensor {

    /**
     * Captura os dados brutos do sensor.
     *
     * @return String com os dados captados.
     */
    String captarDados();

    /**
     * Processa os dados brutos captados pelo sensor.
     *
     * @param dadosBrutos Dados a serem processados.
     */
    void processarDados(String dadosBrutos);
}
