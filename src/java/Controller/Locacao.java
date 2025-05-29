/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Enuns.SituacaoLocacao;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author thiagosilva
 */
public class Locacao {
    
    private int idLocacao;
    private int idVeiculo;
    private int idUsuario;
    private SituacaoLocacao situacaoLocacao;
    private LocalDateTime dataLocacao;
    private LocalDateTime dataDevolvido;
    private LocalDateTime dataDevolucao;
    private boolean devolvido;

    public int getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(int idLocacao) {
        this.idLocacao = idLocacao;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public SituacaoLocacao getSituacaoLocacao() {
        return situacaoLocacao;
    }

    public void setSituacaoLocacao(SituacaoLocacao situacaoLocacao) {
        this.situacaoLocacao = situacaoLocacao;
    }

    public LocalDateTime getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(LocalDateTime dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public LocalDateTime getDataDevolvido() {
        return dataDevolvido;
    }

    public void setDataDevolvido(LocalDateTime dataDevolvido) {
        this.dataDevolvido = dataDevolvido;
    }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDateTime dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }


}
