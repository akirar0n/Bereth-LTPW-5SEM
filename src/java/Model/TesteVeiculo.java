/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.Veiculo;
import java.util.List;

/**
 *
 * @author thiagosilva
 */
public class TesteVeiculo {

    public static void main(String[] args) {
        ManterVeiculo dao = new ManterVeiculo();
        try {
            System.out.println("Iniciando teste de listarVeiculos...");
            List<Veiculo> veiculos = dao.listarVeiculos();

            if (veiculos != null && !veiculos.isEmpty()) {
                System.out.println("Veículos encontrados: " + veiculos.size());
                for (Veiculo v : veiculos) {
                    System.out.println("ID: " + v.getIdVeiculo()
                            + ", Marca: " + v.getMarca()
                            + ", Modelo: " + v.getModelo()
                            + ", Placa: " + v.getPlaca());
                }
            } else {
                System.out.println("Nenhum veículo encontrado no banco de dados, ou a lista está nula/vazia.");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado durante o teste: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Teste de listarVeiculos concluído.");
        }
    }

}
