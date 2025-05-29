/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.SQLException;

/**
 *
 * @author thiagosilva
 */
public class TesteCon {
    public static void main(String[] args) throws SQLException {
        DAO c = new DAO();
        c.abrirBanco();
    }
}
