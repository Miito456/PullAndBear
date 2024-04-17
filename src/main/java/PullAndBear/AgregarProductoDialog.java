package PullAndBear;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.swing.JTable;

public class AgregarProductoDialog extends JDialog {

    private JTextField codigoField;
    private JTextField categoriaField;
    private JTextField productoField;
    private JComboBox<String> tallaComboBox;
    private JTextField precioCompraField;
    private JTextField precioVentaField;
    private JTextField utilidadField;
    private JTextField stockField;
    private JTextField ventaField;

    private DefaultTableModel tableModel;
    private JTable inventoryTable;

    public AgregarProductoDialog(Frame parent, DefaultTableModel tableModel, JTable inventoryTable) {
        super(parent, "Agregar Producto", true);
        this.tableModel = tableModel;
        this.inventoryTable = inventoryTable;
        this.setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel codigoLabel = new JLabel("Código:");
        codigoLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        panel.add(codigoLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        codigoField = new JTextField(20);
        codigoField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        panel.add(codigoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel categoriaLabel = new JLabel("Categoría:");
        categoriaLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN,14));
        panel.add(categoriaLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        categoriaField = new JTextField(20);
        categoriaField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        panel.add(categoriaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel productoLabel = new JLabel("Producto:");
        productoLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        panel.add(productoLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        productoField = new JTextField(20);
        productoField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        panel.add(productoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel tallaLabel = new JLabel("Talla:");
        tallaLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        panel.add(tallaLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        String[] tallas = {"CH", "M", "L", "XL"};
        tallaComboBox = new JComboBox<>(tallas);
        tallaComboBox.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        panel.add(tallaComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel precioCompraLabel = new JLabel("Precio Compra:");
        precioCompraLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN,14));
        panel.add(precioCompraLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        precioCompraField = new JTextField(20);
        precioCompraField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        panel.add(precioCompraField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel precioVentaLabel = new JLabel("Precio Venta:");
        precioVentaLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN,14));
        panel.add(precioVentaLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        precioVentaField = new JTextField(20);
        precioVentaField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        panel.add(precioVentaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel utilidadLabel = new JLabel("Utilidad:");
        utilidadLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        panel.add(utilidadLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        utilidadField = new JTextField("0", 20);
        utilidadField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        panel.add(utilidadField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        JLabel stockLabel = new JLabel("Stock:");
        stockLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN,14));
        panel.add(stockLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        stockField = new JTextField(20);
        stockField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        panel.add(stockField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        JLabel ventaLabel = new JLabel("Venta:");
        ventaLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        panel.add(ventaLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        ventaField = new JTextField("0", 20);
        ventaField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        panel.add(ventaField, gbc);

        ventaField.setEnabled(false); 
        utilidadField.setEnabled(false); 

        RoundedButton addButton = new RoundedButton("Agregar");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        panel.add(addButton, gbc);

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(parent);
    }

    private Font loadCustomFont(String fontName) {
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontName));
            return customFont.deriveFont(Font.PLAIN, 16);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("Arial", Font.PLAIN, 16);
        }
    }

    class RoundedButton extends JButton {

        public RoundedButton(String text) {
            super(text);
            setPreferredSize(new Dimension(160, 40));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setForeground(Color.WHITE);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setOpaque(false);
            setFont(loadCustomFont("FontPAndBearSemi.otf")); 

            setVerticalTextPosition(SwingConstants.CENTER);
            setHorizontalTextPosition(SwingConstants.CENTER);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (getModel().isPressed()) {
                g2d.setColor(Color.decode("#6D6D6D"));
            } else {
                g2d.setColor(Color.decode("#262123"));
            }

            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            g2d.setColor(getForeground());
            g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            g2d.dispose();

            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
        }
    }

    private void agregarProducto() {
        String codigo = codigoField.getText();
        JSONArray jsonArray = JSONHandler.readJSON();
        for (Object obj : jsonArray) {
            JSONObject producto = (JSONObject) obj;
            String codigoExistente = (String) producto.get("Codigo");
            if (codigoExistente.equals(codigo)) {
                JOptionPane.showMessageDialog(this, "El código ya existe. Ingrese un código único.", "Código Duplicado", JOptionPane.ERROR_MESSAGE);
                return; 
            }
        }

        String categoria = categoriaField.getText();
        String producto = productoField.getText();
        String tallaSeleccionada = (String) tallaComboBox.getSelectedItem();
        String precioCompra = precioCompraField.getText();
        String precioVenta = precioVentaField.getText();
        String stock = stockField.getText();
        String ventas = ventaField.getText();

        if (codigo.isEmpty() || categoria.isEmpty() || producto.isEmpty()
                || precioCompra.isEmpty() || precioVenta.isEmpty() || stock.isEmpty() || ventas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Campos Vacíos", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        if (!codigo.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "El campo 'Código' solo debe contener números enteros.", "Formato Incorrecto", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        if (!esNumeroDecimal(precioCompra) || !esNumeroDecimal(precioVenta) || !esNumeroDecimal(stock) || !esNumeroDecimal(ventas)) {
            JOptionPane.showMessageDialog(this, "Los campos numéricos deben contener números decimales válidos.", "Formato Incorrecto", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        double utilidad = Double.parseDouble(precioVenta) - Double.parseDouble(precioCompra);

        JSONObject productoJSON = new JSONObject();
        productoJSON.put("Codigo", codigo);
        productoJSON.put("Categoria", categoria);
        productoJSON.put("Producto", producto);
        productoJSON.put("Talla", tallaSeleccionada);
        productoJSON.put("Precio compra", precioCompra);
        productoJSON.put("Precio venta", precioVenta);
        productoJSON.put("Utilidad", utilidad); 
        productoJSON.put("Stock", stock);
        productoJSON.put("Ventas", ventas);

        jsonArray.add(productoJSON);

        JSONHandler.writeJSON(jsonArray);

        JOptionPane.showMessageDialog(this, "Producto agregado exitosamente", "Agregado", JOptionPane.INFORMATION_MESSAGE);

        Object[] newRow = {codigo, categoria, producto, tallaSeleccionada, precioCompra, precioVenta, utilidad, stock, ventas};
        tableModel.addRow(newRow);

        tableModel.fireTableDataChanged();

        dispose();
    }

    private boolean esNumeroDecimal(String cadena) {
        try {
            double numero = Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
