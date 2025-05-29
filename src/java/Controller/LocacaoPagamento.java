/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Enuns.FormaPagamento;
import java.time.LocalDateTime;

/**
 *
 * @author thiagosilva
 */
public class LocacaoPagamento {
    
    private int idLocacaoPagamento;
    private int idLocacao;
    private int idUsuario;
    private FormaPagamento formaPagamento;
    private LocalDateTime dataPagamento;
    private int parcelas;
    private float valorLocacao;

    public int getIdLocacaoPagamento() {
        return idLocacaoPagamento;
    }

    public void setIdLocacaoPagamento(int idLocacaoPagamento) {
        this.idLocacaoPagamento = idLocacaoPagamento;
    }

    public int getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(int idLocacao) {
        this.idLocacao = idLocacao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public float getValorLocacao() {
        return valorLocacao;
    }

    public void setValorLocacao(float valorLocacao) {
        this.valorLocacao = valorLocacao;
    }
    
    
}
