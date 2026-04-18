package views;

import domain.*;
import data.Persistencia;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AltaVehiculo extends JFrame {
    
    private JComboBox<String> comboTipo;
    private JTextField txtPatente, txtMarcaNom, txtMarcaPais, txtModelo, txtAnio, txtCapacidad;
    private JComboBox<String> comboSucursal;
    private JTextField txtDatoEspecífico1, txtDatoEspecífico2; 
    private JLabel lblDato1, lblDato2;
    private JButton btnGuardar;

    public AltaVehiculo() {
        setTitle("Registro de Nuevo Vehículo");
        setSize(450, 550);
        setLayout(new GridLayout(12, 2, 10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Agregando los campos a la ventana
        add(new JLabel(" Tipo de Vehículo:"));
        comboTipo = new JComboBox<>(new String[]{"ELECTRICO", "COMBUSTIBLE"});
        add(comboTipo);

        add(new JLabel(" Patente:"));
        txtPatente = new JTextField();
        add(txtPatente);

        add(new JLabel(" Nombre de Marca:"));
        txtMarcaNom = new JTextField();
        add(txtMarcaNom);

        add(new JLabel(" País de Marca:"));
        txtMarcaPais = new JTextField();
        add(txtMarcaPais);

        add(new JLabel(" Modelo:"));
        txtModelo = new JTextField();
        add(txtModelo);

        add(new JLabel(" Año:"));
        txtAnio = new JTextField();
        add(txtAnio);

        add(new JLabel(" Capacidad de Carga (kg):"));
        txtCapacidad = new JTextField();
        add(txtCapacidad);

        // Llenamos el combo con las sucursales reales del sistema
        add(new JLabel(" Sucursal:"));
        comboSucursal = new JComboBox<>();
        for (Sucursal s : Persistencia.getSucursales()) {
            comboSucursal.addItem(s.getCodigo());
        }
        add(comboSucursal);

        // Campos variables que cambian según el tipo de vehículo
        lblDato1 = new JLabel(" kWh Base:");
        txtDatoEspecífico1 = new JTextField();
        add(lblDato1);
        add(txtDatoEspecífico1);

        lblDato2 = new JLabel(" (No aplica):");
        lblDato2.setVisible(false);
        txtDatoEspecífico2 = new JTextField();
        txtDatoEspecífico2.setVisible(false);
        add(lblDato2);
        add(txtDatoEspecífico2);

        comboTipo.addActionListener(e -> ajustarCampos());

        // Botón de guardar
        btnGuardar = new JButton("Guardar Vehículo");
        add(new JLabel("")); 
        add(btnGuardar);

        btnGuardar.addActionListener(this::guardarAccion);
        
        setLocationRelativeTo(null); 
    }

    // Método para mostrar/ocultar campos según el tipo
    private void ajustarCampos() {
        if (comboTipo.getSelectedItem().equals("ELECTRICO")) {
            lblDato1.setText(" kWh Base:");
            lblDato2.setVisible(false);
            txtDatoEspecífico2.setVisible(false);
        } else {
            lblDato1.setText(" Km por Litro:");
            lblDato2.setText(" Litros Extra:");
            lblDato2.setVisible(true);
            txtDatoEspecífico2.setVisible(true);
        }
    }

    private void guardarAccion(ActionEvent e) {
        try {
       
            String tipo = comboTipo.getSelectedItem().toString();
            String patente = txtPatente.getText();
            String marcaNom = txtMarcaNom.getText();
            String marcaPais = txtMarcaPais.getText();
            String modelo = txtModelo.getText();
            int anio = Integer.parseInt(txtAnio.getText());
            double capacidad = Double.parseDouble(txtCapacidad.getText());
            

            String codSuc = comboSucursal.getSelectedItem().toString();
            Sucursal suc = Persistencia.getSucursales().stream()
                    .filter(s -> s.getCodigo().equals(codSuc))
                    .findFirst().orElse(null);


            double kwh = 0;
            double kmL = 0;
            double extra = 0;

            if (tipo.equals("ELECTRICO")) {
                kwh = Double.parseDouble(txtDatoEspecífico1.getText());
            } else {
                kmL = Double.parseDouble(txtDatoEspecífico1.getText());
                extra = Double.parseDouble(txtDatoEspecífico2.getText());
            }

  
            Controlador.registrarNuevoVehiculo(tipo, patente, marcaNom, marcaPais, modelo, anio, capacidad, suc, kwh, kmL, extra);

            JOptionPane.showMessageDialog(this, "¡Vehículo registrado con éxito!");
            this.dispose(); 

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Asegurate de ingresar números válidos en Año, Capacidad y Consumos.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error inesperado al guardar el vehículo.");
        }
    }
}