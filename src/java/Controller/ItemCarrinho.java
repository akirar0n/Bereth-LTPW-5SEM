/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author bryan
 */
public class ItemCarrinho {
    private Veiculo veiculo;
    private int quantidade;
    
    public ItemCarrinho(Veiculo veiculo, int quantidade){
        this.veiculo = veiculo;
        this.quantidade = quantidade;
    
    }
    
    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public double getSubtotal() {
        return veiculo.getPreco() * quantidade;
    }
    
}
