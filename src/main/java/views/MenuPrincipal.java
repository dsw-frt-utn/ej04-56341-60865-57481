package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JFrame {
    
    private JButton btnListar;
    private JButton btnAgregar;

    public MenuPrincipal() {
        // Configuración básica de la ventana
        setTitle("Sistema de Logística - Menú Principal");
        setSize(350, 200);
        setLayout(new GridLayout(2, 1, 20, 20)); // Grilla simple de 2 filas
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Esto cierra todo el programa al salir

        // Inicializamos los botones
        btnListar = new JButton("Listar Vehículos");
        btnAgregar = new JButton("Agregar Vehículo");

        btnListar.setFont(new Font("Arial", Font.BOLD, 14));
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 14));

        // Agregamos los botones a la ventana
        add(btnListar);
        add(btnAgregar);

        btnListar.addActionListener((ActionEvent e) -> {
            new ListarVehiculosView().setVisible(true);
        });

        btnAgregar.addActionListener((ActionEvent e) -> {
            new AltaVehiculo().setVisible(true);
        });

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null); 
    }
}

