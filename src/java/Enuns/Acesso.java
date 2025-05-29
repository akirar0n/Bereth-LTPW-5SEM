/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enuns;

/**
 *
 * @author thiagosilva
 */
public enum Acesso {
    Administrador,
    Cliente;

    // ESTE MÉTODO É ESSENCIAL PARA O fromString()
    public static Acesso fromString(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("O texto para Acesso.fromString não pode ser nulo ou vazio.");
        }

        for (Acesso b : Acesso.values()) {
            if (b.name().equalsIgnoreCase(text.trim())) {
                return b;
            }
        }
        throw new IllegalArgumentException("Nenhum Acesso com o texto: '" + text + "' encontrado. Valores esperados: Administrador, Cliente.");
    }
}
