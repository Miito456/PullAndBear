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

public class EditarProductoDialog extends JDialog {

    private JTextField codigoField;
    private JTextField categoriaField;
    private JTextField productoField;
    private JComboBox<String> tallaComboBox;
    private JTextField precioCompraField;
    private JTextField precioVentaField;
    private JTextField utilidadField;
    private JTextField stockField;
    private JTextField ventaField;

    private JSONObject producto;
    private JTable inventoryTable;
    private int rowIndex;

    public EditarProductoDialog(JSONObject producto, JTable inventoryTable, int rowIndex) {
        super((JDialog) null, "Editar producto", ModalityType.APPLICATION_MODAL);
        this.producto = producto;
        this.inventoryTable = inventoryTable;
        this.rowIndex = rowIndex;
        initComponents();
        fillFields();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        codigoField = new JTextField(20);
        categoriaField = new JTextField(20);
        productoField = new JTextField(20);
        tallaComboBox = new JComboBox<>();
        precioCompraField = new JTextField(20);
        precioVentaField = new JTextField(20);
        utilidadField = new JTextField(20);
        stockField = new JTextField(20);
        ventaField = new JTextField(20);

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
        categoriaLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
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
        panel.add(tallaComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel precioCompraLabel = new JLabel("Precio Compra:");
        precioCompraLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        panel.add(precioCompraLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        precioCompraField = new JTextField(20);
        precioCompraField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        panel.add(precioCompraField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel precioVentaLabel = new JLabel("Precio Venta:");
        precioVentaLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
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
        utilidadField = new JTextField(20);
        utilidadField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        panel.add(utilidadField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        JLabel stockLabel = new JLabel("Stock:");
        stockLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
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
        ventaField = new JTextField(20);
        ventaField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        panel.add(ventaField, gbc);

        ventaField.setEnabled(false);
        utilidadField.setEnabled(false);

        RoundedButton guardarButton = new RoundedButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        panel.add(guardarButton, gbc);

        RoundedButton cancelarButton = new RoundedButton("Cancelar");
        cancelarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelarEdicion();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        panel.add(cancelarButton, gbc);

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
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

    private void fillFields() {
        codigoField.setText((String) producto.get("Codigo"));
        categoriaField.setText((String) producto.get("Categoria"));
        productoField.setText((String) producto.get("Producto"));

        String talla = (String) producto.get("Talla");
        tallaComboBox.setSelectedItem(talla);

        precioCompraField.setText(String.valueOf(producto.get("Precio compra")));
        precioVentaField.setText(String.valueOf(producto.get("Precio venta")));
        utilidadField.setText(String.valueOf(producto.get("Utilidad")));
        stockField.setText(String.valueOf(producto.get("Stock")));
        ventaField.setText(String.valueOf(producto.get("Ventas")));

        Object venta = producto.get("Ventas");
        if (venta != null) {
            ventaField.setText(String.valueOf(venta));
        } else {
            ventaField.setText("0"); 
        }
    }

    private void guardarCambios() {
        int rowIndex = inventoryTable.getSelectedRow();
        if (rowIndex == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un producto para editar.", "Sin Selección", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String codigo = codigoField.getText();
        String categoria = categoriaField.getText();
        String producto = productoField.getText();
        String talla = (String) tallaComboBox.getSelectedItem();
        String precioCompraStr = precioCompraField.getText();
        String precioVentaStr = precioVentaField.getText();
        String stockStr = stockField.getText();
        String ventaStr = ventaField.getText();

        if (codigo.isEmpty() || categoria.isEmpty() || producto.isEmpty() || talla.isEmpty() || precioCompraStr.isEmpty() || precioVentaStr.isEmpty() || stockStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Campos Vacíos", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double precioCompra = Double.parseDouble(precioCompraStr);
        double precioVenta = Double.parseDouble(precioVentaStr);

        double utilidad = precioVenta - precioCompra;

        if (precioVenta <= precioCompra) {
            JOptionPane.showMessageDialog(this, "El precio de venta debe ser mayor que el precio de compra.", "Precio Incorrecto", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JSONObject productoActualizado = new JSONObject();
        productoActualizado.put("Codigo", codigo);
        productoActualizado.put("Categoria", categoria);
        productoActualizado.put("Producto", producto);
        productoActualizado.put("Talla", talla);
        productoActualizado.put("Precio compra", precioCompraStr); 
        productoActualizado.put("Precio venta", precioVentaStr); 
        productoActualizado.put("Utilidad", Double.toString(utilidad));
        productoActualizado.put("Stock", stockStr); 
        productoActualizado.put("Ventas", ventaStr); 

        JSONArray jsonArray = JSONHandler.readJSON();

        jsonArray.set(rowIndex, productoActualizado);

        JSONHandler.writeJSON(jsonArray);

        DefaultTableModel model = (DefaultTableModel) inventoryTable.getModel();
        model.setValueAt(codigo, rowIndex, 0);
        model.setValueAt(categoria, rowIndex, 1);
        model.setValueAt(producto, rowIndex, 2);
        model.setValueAt(talla, rowIndex, 3);
        model.setValueAt(precioCompraStr, rowIndex, 4); 
        model.setValueAt(precioVentaStr, rowIndex, 5); 
        model.setValueAt(Double.toString(utilidad), rowIndex, 6); 
        model.setValueAt(stockStr, rowIndex, 7);
        model.setValueAt(ventaStr, rowIndex, 8);

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

    private boolean esNumeroEntero(String cadena) {
        try {
            int numero = Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void cancelarEdicion() {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea cancelar los cambios?", "Cancelar Cambios", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            dispose();
        }
    }
}
