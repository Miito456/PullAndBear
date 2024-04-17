package PullAndBear;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import org.json.simple.*;
import javax.swing.JTable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableRowSorter;
import javax.swing.text.View;

public class PullAndBearTrabajador extends JFrame {

    private JPanel contentPane;

    public PullAndBearTrabajador() {
        initUI();
    }

    private Font customFont;

    private void initUI() {
        setTitle("PULL & BEAR");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        customFont = loadCustomFont("FontPAndBearSemi.otf");

        contentPane = new JPanel(null);
        setContentPane(contentPane);
        contentPane.setBackground(Color.decode("#F2F2F2"));

        addLogo();
        addWelcomeLabel();
        addContentPane();

        contentPane.requestFocusInWindow();
    }

//Logo
    private void addLogo() {
        ImageIcon logoIcon = new ImageIcon("logo.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(10, 10, 150, 100);
        contentPane.add(logoLabel);
    }

//Welcome
    private void addWelcomeLabel() {
        JLabel welcomeLabel = new JLabel("Bienvenido");
        welcomeLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 40)); // Ajuste del tamaño de la fuente a 40
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setBounds(170, 10, 860, 100);
        contentPane.add(welcomeLabel);
    }

//Menu
    private void addSideMenu() {
        JPanel sideMenuPanel = new JPanel(new GridBagLayout());
        sideMenuPanel.setBounds(10, 120, 180, 520);
        sideMenuPanel.setBackground(Color.decode("#F2F2F2"));

        String[] menuItems = {"Menú principal", "Inventario", "Ventas", "Apartados", "Salir"};
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        for (String menuItem : menuItems) {
            RoundedButton button = new RoundedButton(menuItem);

            button.setFont(loadCustomFont("FontPAndBearSemi.otf", Font.PLAIN, 14));
            button.setBackground(Color.decode("#262123"));
            button.setForeground(Color.WHITE);
            button.setHorizontalAlignment(SwingConstants.CENTER);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (menuItem) {
                        case "Menú principal":
                            contentPane.removeAll();
                            addLogo();
                            addWelcomeLabel();
                            addSideMenu();
                            addContentPane();
                            contentPane.revalidate();
                            contentPane.repaint();
                            break;
                        case "Inventario":
                            contentPane.removeAll();
                            addLogo();
                            addWelcomeLabel();
                            addSideMenu();
                            addInventoryContent();
                            contentPane.revalidate();
                            contentPane.repaint();
                            break;
                        case "Ventas":
                            contentPane.removeAll();
                            addLogo();
                            addWelcomeLabel();
                            addSideMenu();
                            addVentasContent();
                            contentPane.revalidate();
                            contentPane.repaint();
                            break;
                        case "Apartados":
                            contentPane.removeAll();
                            addLogo();
                            addWelcomeLabel();
                            addSideMenu();
                            addApartadosContent();
                            contentPane.revalidate();
                            contentPane.repaint();
                            break;
                        case "Finanzas Ventas":
                            contentPane.removeAll();
                            addLogo();
                            addWelcomeLabel();
                            addSideMenu();
                            addFinanzasContent();
                            contentPane.revalidate();
                            contentPane.repaint();
                            break;
                        case "Finanzas Apartados":
                            contentPane.removeAll();
                            addLogo();
                            addWelcomeLabel();
                            addSideMenu();
                            addFinanzasApartadosContent();
                            contentPane.revalidate();
                            contentPane.repaint();
                            break;
                        case "Salir":
                            button.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
                                    if (confirm == JOptionPane.YES_OPTION) {
                                        System.exit(0);
                                    }
                                }
                            });
                            break;
                    }
                }
            });

            sideMenuPanel.add(button, gbc);
        }

        contentPane.add(sideMenuPanel);
    }

// Apartados
    private void addApartadosContent() {
        JPanel ventasPanel = new JPanel(null);
        ventasPanel.setBounds(200, 120, 980, 520);
        ventasPanel.setBackground(Color.decode("#F2F2F2"));
        contentPane.add(ventasPanel);

        JLabel tituloLabel = new JLabel("Apartados");
        tituloLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 24));
        tituloLabel.setBounds(410, 0, 200, 30);
        ventasPanel.add(tituloLabel);

        JLabel clienteLabel = new JLabel("Nombre del Cliente:");
        clienteLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 16));
        clienteLabel.setBounds(20, 60, 200, 30);
        ventasPanel.add(clienteLabel);

        JTextField clienteTextField = new JTextField();
        clienteTextField.setBounds(190, 60, 200, 30);
        clienteTextField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        clienteTextField.setBackground(Color.WHITE);
        clienteTextField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        ventasPanel.add(clienteTextField);

        JLabel catLabel = new JLabel("Catálogo:");
        catLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 16));
        catLabel.setBounds(20, 100, 150, 30);
        ventasPanel.add(catLabel);

        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 130, 400, 180);
        ventasPanel.add(scrollPane);
        table.setDefaultEditor(Object.class, null);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Font customFont = loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 12);
        table.setFont(customFont);
        Font customFont2 = loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 15);
        table.getTableHeader().setFont(customFont2);
        table.getTableHeader().setBackground(Color.decode("#262123"));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setRowHeight(30);

        JLabel capacidadLabel = new JLabel("Cantidad:");
        capacidadLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 16));
        capacidadLabel.setBounds(20, 340, 80, 30);
        ventasPanel.add(capacidadLabel);

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 100, 1);
        JSpinner spinner = new JSpinner(spinnerModel);
        spinner.setBounds(110, 340, 60, 30);
        spinner.setEditor(new JSpinner.DefaultEditor(spinner));
        ventasPanel.add(spinner);

        JLabel precioLabel = new JLabel("Precio unitario $MXN:");
        precioLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 16));
        precioLabel.setBounds(180, 340, 200, 30);
        ventasPanel.add(precioLabel);

        JTextField precioTextField = new JTextField();
        precioTextField.setBounds(350, 340, 100, 30);
        precioTextField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        precioTextField.setBackground(Color.WHITE);
        precioTextField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        ventasPanel.add(precioTextField);
        precioTextField.setEnabled(false);

        JLabel precioTotalLabel = new JLabel("Precio Total $MXN:");
        precioTotalLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 16));
        precioTotalLabel.setBounds(20, 390, 150, 30);
        ventasPanel.add(precioTotalLabel);

        JTextField precioTotalTextField = new JTextField();
        precioTotalTextField.setBounds(175, 390, 100, 30);
        precioTotalTextField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        precioTotalTextField.setBackground(Color.WHITE);
        precioTotalTextField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        ventasPanel.add(precioTotalTextField);
        precioTotalTextField.setEnabled(false);

        JLabel cantidadLabel = new JLabel("Cantidad a abonar $MXN:");
        cantidadLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 16));
        cantidadLabel.setBounds(280, 390, 250, 30);
        ventasPanel.add(cantidadLabel);

        JTextField cantidadTextField = new JTextField();
        cantidadTextField.setBounds(490, 390, 100, 30);
        cantidadTextField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        cantidadTextField.setBackground(Color.WHITE);
        cantidadTextField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        ventasPanel.add(cantidadTextField);

        RoundedButton agregarButton = new RoundedButton("Realizar apartado");
        agregarButton.setBounds(20, 440, 200, 40);
        agregarButton.setBackground(Color.red);
        ventasPanel.add(agregarButton);

        RoundedButton cancelarButton = new RoundedButton("Limpiar");
        cancelarButton.setBounds(250, 440, 150, 40);
        ventasPanel.add(cancelarButton);

        JLabel carritoLabel = new JLabel("Gestionar apartados");
        carritoLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 20));
        carritoLabel.setBounds(600, 60, 200, 30);
        ventasPanel.add(carritoLabel);

        DefaultTableModel carritoModel = new DefaultTableModel();
        carritoModel.addColumn("Nombre del cliente");
        carritoModel.addColumn("Producto");
        carritoModel.addColumn("Cantidad");
        carritoModel.addColumn("Deuda");
        carritoModel.addColumn("Pagado");
        carritoModel.addColumn("Total a pagar");

        JTable carritoTable = new JTable(carritoModel);
        JScrollPane carritoScrollPane = new JScrollPane(carritoTable);
        carritoScrollPane.setBounds(490, 110, 465, 200);
        ventasPanel.add(carritoScrollPane);

        carritoTable.setDefaultEditor(Object.class, null);
        carritoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        carritoTable.setFont(customFont);
        carritoTable.getTableHeader().setFont(customFont2);
        carritoTable.getTableHeader().setBackground(Color.decode("#262123"));
        carritoTable.getTableHeader().setForeground(Color.WHITE);
        carritoTable.setRowHeight(30);

        RoundedButton confirmarButton = new RoundedButton("Realizar Abono");
        confirmarButton.setBounds(690, 440, 225, 40);
        ventasPanel.add(confirmarButton);

        JSONArray jsonArray = readInventoryJSON();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Código");
        model.addColumn("Producto");
        model.addColumn("Stock");
        model.addColumn("Precio");

        for (Object obj : jsonArray) {
            JSONObject producto = (JSONObject) obj;
            String codigo = (String) producto.get("Codigo");
            String nombre = (String) producto.get("Producto");
            String stock = (String) producto.get("Stock");
            String precio = (String) producto.get("Precio venta");

            model.addRow(new Object[]{codigo, nombre, stock, precio});
        }

        JSONArray apartadosArray;
        try (FileReader fileReader = new FileReader("apartados.json")) {
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(fileReader);
            if (obj instanceof JSONArray) {
                apartadosArray = (JSONArray) obj;

                for (Object apartadoObj : apartadosArray) {
                    JSONObject apartado = (JSONObject) apartadoObj;
                    String producto = (String) apartado.get("Producto");
                    int cantidad = ((Long) apartado.get("Cantidad")).intValue();
                    double deuda = (double) apartado.get("Deuda");
                    double pagado = (double) apartado.get("Pagado");
                    double totalAPagar = (double) apartado.get("Total a pagar");
                    String cliente = (String) apartado.get("Nombre del cliente");

                    carritoModel.addRow(new Object[]{cliente, producto, cantidad, deuda, pagado, totalAPagar});
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cantidadAbonarText = cantidadTextField.getText();
                if (cantidadAbonarText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese la cantidad a abonar.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    double cantidadAbonar = Double.parseDouble(cantidadAbonarText);
                    if (cantidadAbonar <= 0) {
                        JOptionPane.showMessageDialog(null, "La cantidad a abonar debe ser un número positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    double precioTotal = Double.parseDouble(precioTotalTextField.getText());
                    if (cantidadAbonar > precioTotal) {
                        JOptionPane.showMessageDialog(null, "La cantidad a abonar no puede ser mayor que el precio total.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "La cantidad a abonar debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String clienteNombre = clienteTextField.getText();
                if (clienteNombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese el nombre del cliente.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //Inventario
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String codigoProducto = (String) table.getValueAt(selectedRow, 0);
                    String nombreProducto = (String) table.getValueAt(selectedRow, 1);
                    int cantidadSeleccionada = (int) spinner.getValue();

                    try {
                        JSONParser parser = new JSONParser();
                        JSONArray inventario = (JSONArray) parser.parse(new FileReader("inventario.JSON"));

                        for (Object obj : inventario) {
                            JSONObject producto = (JSONObject) obj;
                            String codigo = (String) producto.get("Codigo");

                            if (codigo.equals(codigoProducto)) {
                                int stockActual = Integer.parseInt((String) producto.get("Stock"));
                                int nuevoStock = stockActual - cantidadSeleccionada;
                                producto.put("Stock", String.valueOf(nuevoStock));
                                break;
                            }
                        }

                        FileWriter fileWriter = new FileWriter("inventario.JSON");
                        fileWriter.write(inventario.toJSONString());
                        fileWriter.flush();
                        fileWriter.close();
                    } catch (IOException | ParseException e1) {
                        e1.printStackTrace();
                    }

                    double precioTotal = Double.parseDouble(precioTotalTextField.getText());
                    double cantidadAbonar = Double.parseDouble(cantidadTextField.getText());

                    double deuda = precioTotal - cantidadAbonar;

//Apratdos.json
                    JSONArray apartadosArray;

                    try (FileReader fileReader = new FileReader("apartados.json")) {
                        JSONParser jsonParser = new JSONParser();
                        Object obj = jsonParser.parse(fileReader);

                        if (obj instanceof JSONArray) {
                            apartadosArray = (JSONArray) obj;
                        } else {
                            apartadosArray = new JSONArray();
                        }
                    } catch (IOException | ParseException ex) {
                        ex.printStackTrace();
                        apartadosArray = new JSONArray();
                    }

                    JSONObject apartadoObject = new JSONObject();
                    apartadoObject.put("Nombre del cliente", clienteNombre);
                    apartadoObject.put("Producto", nombreProducto);
                    apartadoObject.put("Cantidad", cantidadSeleccionada);
                    apartadoObject.put("Deuda", deuda);
                    apartadoObject.put("Pagado", cantidadAbonar);
                    apartadoObject.put("Total a pagar", precioTotal);

                    apartadosArray.add(apartadoObject);

                    try (FileWriter fileWriter = new FileWriter("apartados.json")) {
                        fileWriter.write(apartadosArray.toJSONString());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    JSONArray finanzasArray;

                    try (FileReader fileReader = new FileReader("finanzasApartados.json")) {
                        JSONParser jsonParser = new JSONParser();
                        Object obj = jsonParser.parse(fileReader);

                        if (obj instanceof JSONArray) {
                            finanzasArray = (JSONArray) obj;
                        } else {
                            finanzasArray = new JSONArray();
                        }
                    } catch (IOException | ParseException ex) {
                        ex.printStackTrace();
                        finanzasArray = new JSONArray();
                    }

                    JSONObject finanzasObject = new JSONObject();
                    finanzasObject.put("Cliente", clienteTextField.getText());
                    finanzasObject.put("Codigo", codigoProducto);
                    finanzasObject.put("Producto", nombreProducto);
                    finanzasObject.put("Cantidad", cantidadSeleccionada);
                    finanzasObject.put("Precio por pagar", deuda);
                    finanzasObject.put("Pagado", cantidadAbonar);
                    finanzasObject.put("Total a pagar", precioTotal);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String fecha = dateFormat.format(new Date());
                    finanzasObject.put("Fecha", fecha);
                    finanzasArray.add(finanzasObject);

                    try (FileWriter fileWriter = new FileWriter("finanzasApartados.json")) {
                        fileWriter.write(finanzasArray.toJSONString());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }

                clienteTextField.setText("");
                spinner.setValue(1);
                precioTextField.setText("");
                precioTotalTextField.setText("");
                cantidadTextField.setText("");
                JOptionPane.showMessageDialog(null, "Apartado realizado con éxito.");
                actualizarTablas();
            }

            private void actualizarTablas() {
                cargarDatosCatalogo();

                cargarDatosGestionApartados();
            }

            private void cargarDatosCatalogo() {
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Código");
                model.addColumn("Producto");
                model.addColumn("Stock");
                model.addColumn("Precio");

                JSONArray jsonArray = readInventoryJSON();

                for (Object obj : jsonArray) {
                    JSONObject producto = (JSONObject) obj;
                    String codigo = (String) producto.get("Codigo");
                    String nombre = (String) producto.get("Producto");
                    String stock = (String) producto.get("Stock");
                    String precio = (String) producto.get("Precio venta");

                    model.addRow(new Object[]{codigo, nombre, stock, precio});
                }

                table.setModel(model);
            }

            private void cargarDatosGestionApartados() {
                DefaultTableModel carritoModel = new DefaultTableModel();
                carritoModel.addColumn("Nombre del cliente");
                carritoModel.addColumn("Producto");
                carritoModel.addColumn("Cantidad");
                carritoModel.addColumn("Deuda");
                carritoModel.addColumn("Pagado");
                carritoModel.addColumn("Total a pagar");

                JSONArray apartadosArray;
                try (FileReader fileReader = new FileReader("apartados.json")) {
                    JSONParser jsonParser = new JSONParser();
                    Object obj = jsonParser.parse(fileReader);
                    if (obj instanceof JSONArray) {
                        apartadosArray = (JSONArray) obj;

                        for (Object apartadoObj : apartadosArray) {
                            JSONObject apartado = (JSONObject) apartadoObj;
                            String producto = (String) apartado.get("Producto");
                            int cantidad = ((Long) apartado.get("Cantidad")).intValue();
                            double deuda = (double) apartado.get("Deuda");
                            double pagado = (double) apartado.get("Pagado");
                            double totalAPagar = (double) apartado.get("Total a pagar");
                            String cliente = (String) apartado.get("Nombre del cliente");

                            carritoModel.addRow(new Object[]{cliente, producto, cantidad, deuda, pagado, totalAPagar});
                        }
                    }
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }

                carritoTable.setModel(carritoModel);
            }

        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpiar los campos
                clienteTextField.setText("");
                cantidadTextField.setText("");
                precioTextField.setText("");
                precioTotalTextField.setText("");
                cantidadTextField.setText("");
            }
        });

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = carritoTable.getSelectedRow();
                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un registro para abonar.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double deuda = (double) carritoTable.getValueAt(filaSeleccionada, 3);
                double pagado = (double) carritoTable.getValueAt(filaSeleccionada, 4);

                String cantidadAbonarText = JOptionPane.showInputDialog(null, "Cantidad para abonar:");

                if (cantidadAbonarText == null || cantidadAbonarText.isEmpty()) {
                    return;
                }

                try {
                    double cantidadAbonar = Double.parseDouble(cantidadAbonarText);

                    if (cantidadAbonar <= 0 || cantidadAbonar > deuda) {
                        JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor que 0 y menor o igual a la deuda.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    double nuevaDeuda = deuda - cantidadAbonar;
                    double nuevoPagado = pagado + cantidadAbonar;

                    carritoTable.setValueAt(String.valueOf(nuevaDeuda), filaSeleccionada, 3);
                    carritoTable.setValueAt(String.valueOf(nuevoPagado), filaSeleccionada, 4);

                    JSONArray apartadosArray;
                    try (FileReader fileReader = new FileReader("apartados.json")) {
                        JSONParser jsonParser = new JSONParser();
                        Object obj = jsonParser.parse(fileReader);

                        if (obj instanceof JSONArray) {
                            apartadosArray = (JSONArray) obj;

                            JSONObject apartadoObject = (JSONObject) apartadosArray.get(filaSeleccionada);
                            apartadoObject.put("Deuda", nuevaDeuda);
                            apartadoObject.put("Pagado", nuevoPagado);

                            try (FileWriter fileWriter = new FileWriter("apartados.json")) {
                                fileWriter.write(apartadosArray.toJSONString());
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    } catch (IOException | ParseException ex) {
                        ex.printStackTrace();
                    }

                    // Actualizar finanzasApartados.json
                    JSONArray finanzasApartadosArray;
                    try (FileReader fileReader = new FileReader("finanzasApartados.json")) {
                        JSONParser jsonParser = new JSONParser();
                        Object obj = jsonParser.parse(fileReader);

                        if (obj instanceof JSONArray) {
                            finanzasApartadosArray = (JSONArray) obj;

                            JSONObject apartadoObject = (JSONObject) finanzasApartadosArray.get(filaSeleccionada);
                            apartadoObject.put("Precio por pagar", nuevaDeuda);
                            apartadoObject.put("Pagado", nuevoPagado);

                            try (FileWriter fileWriter = new FileWriter("finanzasApartados.json")) {
                                fileWriter.write(finanzasApartadosArray.toJSONString());
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    } catch (IOException | ParseException ex) {
                        ex.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(null, "Abono realizado con éxito.");
                    carritoTable.clearSelection();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                spinner.setValue(1);

                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String precioProducto = (String) table.getValueAt(selectedRow, 3);
                    precioTotalTextField.setText(precioProducto);
                    precioTextField.setText(precioProducto);

                    String stockDisponibleStr = (String) table.getValueAt(selectedRow, 2);
                    int stockDisponible = Integer.parseInt(stockDisponibleStr);
                    SpinnerNumberModel spinnerModel;

                    if (stockDisponible > 0) {
                        spinnerModel = new SpinnerNumberModel(1, 1, stockDisponible, 1);
                        spinner.setEnabled(true);
                    } else {
                        spinnerModel = new SpinnerNumberModel(0, 0, 0, 0);
                        spinner.setEnabled(false);
                    }

                    spinner.setModel(spinnerModel);
                }
            }
        });

        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double precioUnitario = Double.parseDouble(precioTextField.getText());
                int cantidad = (int) spinner.getValue();
                double precioTotal = precioUnitario * cantidad;
                precioTotalTextField.setText(String.valueOf(precioTotal));
            }
        });

        table.setModel(model);

        ventasPanel.add(scrollPane);

    }

    private void addFinanzasApartadosContent() {
        JPanel finanzasApartadosPanel = new JPanel(null);
        finanzasApartadosPanel.setBounds(200, 120, 980, 520);
        finanzasApartadosPanel.setBackground(Color.decode("#F2F2F2"));
        contentPane.add(finanzasApartadosPanel);

        JLabel tituloLabel = new JLabel("Finanzas de Apartados");
        tituloLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 24));
        tituloLabel.setBounds(345, 0, 300, 30);
        finanzasApartadosPanel.add(tituloLabel);

        JLabel gananciasGlobalesLabel = new JLabel("Ganancias Totales $MXN:");
        gananciasGlobalesLabel.setBounds(670, 40, 200, 30);
        gananciasGlobalesLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 16));
        finanzasApartadosPanel.add(gananciasGlobalesLabel);

        JTextField gananciasGlobalesTextField = new JTextField();
        gananciasGlobalesTextField.setBounds(870, 40, 150, 30);
        gananciasGlobalesTextField.setText(calcularGananciasGlobales()); // Implementar este método
        gananciasGlobalesTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#F2F2F2")));
        gananciasGlobalesTextField.setBackground(Color.decode("#F2F2F2"));
        gananciasGlobalesTextField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        gananciasGlobalesTextField.setEditable(false);
        finanzasApartadosPanel.add(gananciasGlobalesTextField);

        JLabel gananciasLabel = new JLabel("Ganancias en Apartados $MXN:");
        gananciasLabel.setBounds(20, 40, 290, 30);
        gananciasLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 16));
        finanzasApartadosPanel.add(gananciasLabel);

        JTextField gananciasTextField = new JTextField();
        gananciasTextField.setBounds(285, 40, 150, 30);
        gananciasTextField.setText(calcularGananciasApartados());
        gananciasTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#F2F2F2")));
        gananciasTextField.setBackground(Color.decode("#F2F2F2"));
        gananciasTextField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        gananciasTextField.setEditable(false);
        finanzasApartadosPanel.add(gananciasTextField);

        JLabel historialLabel = new JLabel("Historial de Apartados");
        historialLabel.setBounds(20, 80, 230, 30);
        historialLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 16));
        finanzasApartadosPanel.add(historialLabel);

        DefaultTableModel historialTableModel = new DefaultTableModel();
        historialTableModel.addColumn("Cliente");
        historialTableModel.addColumn("Producto");
        historialTableModel.addColumn("Cantidad");
        historialTableModel.addColumn("Deuda");
        historialTableModel.addColumn("Pagado");
        historialTableModel.addColumn("Total a pagar");
        historialTableModel.addColumn("Fecha");

        JSONArray historialArray = leerHistorialApartados();
        for (Object obj : historialArray) {
            JSONObject apartado = (JSONObject) obj;
            String cliente = (String) apartado.get("Cliente");
            String producto = (String) apartado.get("Producto");
            long cantidad = (long) apartado.get("Cantidad");
            double deuda = (double) apartado.get("Precio por pagar");
            double pagado = (double) apartado.get("Pagado");
            double totalAPagar = (double) apartado.get("Total a pagar");
            String fecha = (String) apartado.get("Fecha");

            historialTableModel.addRow(new Object[]{cliente, producto, cantidad, deuda, pagado, totalAPagar, fecha});
        }

        JTable historialTable = new JTable(historialTableModel);
        JScrollPane historialScrollPane = new JScrollPane(historialTable);
        historialScrollPane.setBounds(20, 120, 940, 380);
        finanzasApartadosPanel.add(historialScrollPane);

        historialTable.setDefaultEditor(Object.class, null);
        historialTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Font customFont = loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 12);
        historialTable.setFont(customFont);
        Font customFont2 = loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 15);
        historialTable.getTableHeader().setFont(customFont2);
        historialTable.getTableHeader().setBackground(Color.decode("#262123"));
        historialTable.getTableHeader().setForeground(Color.WHITE);
        historialTable.setRowHeight(30);
    }

    private String calcularGananciasApartados() {
        JSONArray historialArray = leerHistorialApartados(); // Debes implementar este método para leer el historial de apartados
        double gananciasApartados = 0.0;
        for (Object obj : historialArray) {
            JSONObject apartado = (JSONObject) obj;
            Object pagadoObj = apartado.get("Pagado");
            if (pagadoObj != null) {
                double pagado = Double.parseDouble(pagadoObj.toString());
                gananciasApartados += pagado;
            }
        }
        return String.valueOf(gananciasApartados);
    }

    private JSONArray leerHistorialApartados() {
        JSONArray historialArray = new JSONArray();

        try (FileReader fileReader = new FileReader("finanzasApartados.json")) {
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(fileReader);

            if (obj instanceof JSONArray) {
                historialArray = (JSONArray) obj;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return historialArray;
    }

//Finanzas
    private void addFinanzasContent() {
        JPanel finanzasPanel = new JPanel(null);
        finanzasPanel.setBounds(200, 120, 980, 520);
        finanzasPanel.setBackground(Color.decode("#F2F2F2"));
        contentPane.add(finanzasPanel);

        JLabel tituloLabel = new JLabel("Ventas");
        tituloLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 24));
        tituloLabel.setBounds(410, 0, 200, 30);
        finanzasPanel.add(tituloLabel);

        JLabel gananciasGlobalesLabel = new JLabel("Ganancias Totales:");
        gananciasGlobalesLabel.setBounds(690, 40, 200, 30);
        gananciasGlobalesLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 16));
        finanzasPanel.add(gananciasGlobalesLabel);

        JTextField gananciasGlobalesTextField = new JTextField();
        gananciasGlobalesTextField.setBounds(850, 40, 150, 30);
        gananciasGlobalesTextField.setText(calcularGananciasGlobales());
        gananciasGlobalesTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#F2F2F2")));
        gananciasGlobalesTextField.setBackground(Color.decode("#F2F2F2"));
        gananciasGlobalesTextField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        gananciasGlobalesTextField.setEditable(false);
        finanzasPanel.add(gananciasGlobalesTextField);

        JLabel gananciasLabel = new JLabel("Ganancias en Ventas:");
        gananciasLabel.setBounds(20, 40, 200, 30);
        gananciasLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 16));
        finanzasPanel.add(gananciasLabel);

        JTextField gananciasTextField = new JTextField();
        gananciasTextField.setBounds(200, 40, 150, 30);
        gananciasTextField.setText(calcularGanancias());
        gananciasTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#F2F2F2")));
        gananciasTextField.setBackground(Color.decode("#F2F2F2"));
        gananciasTextField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        gananciasTextField.setEditable(false);
        finanzasPanel.add(gananciasTextField);

        JLabel historialLabel = new JLabel("Historial de Ventas");
        historialLabel.setBounds(20, 80, 230, 30);
        historialLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 16));
        finanzasPanel.add(historialLabel);

        DefaultTableModel historialTableModel = new DefaultTableModel();
        historialTableModel.addColumn("Cliente");
        historialTableModel.addColumn("Producto");
        historialTableModel.addColumn("Cantidad");
        historialTableModel.addColumn("Precio Total");
        historialTableModel.addColumn("Fecha Venta");

        JSONArray historialArray = leerHistorialVentas();
        for (Object obj : historialArray) {
            JSONObject venta = (JSONObject) obj;
            String cliente = (String) venta.get("Cliente");
            String producto = (String) venta.get("Producto");
            long cantidad = (long) venta.get("Cantidad");
            double precioTotal = (double) venta.get("PrecioTotal");
            String fechaVenta = (String) venta.get("FechaVenta");

            historialTableModel.addRow(new Object[]{cliente, producto, cantidad, precioTotal, fechaVenta});
        }

        JTable historialTable = new JTable(historialTableModel);
        JScrollPane historialScrollPane = new JScrollPane(historialTable);
        historialScrollPane.setBounds(20, 120, 940, 380);
        finanzasPanel.add(historialScrollPane);

        historialTable.setDefaultEditor(Object.class, null);
        historialTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Font customFont = loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 12);
        historialTable.setFont(customFont);
        Font customFont2 = loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 15);
        historialTable.getTableHeader().setFont(customFont2);
        historialTable.getTableHeader().setBackground(Color.decode("#262123"));
        historialTable.getTableHeader().setForeground(Color.WHITE);
        historialTable.setRowHeight(30);

    }

    private String calcularGanancias() {
        JSONArray historialArray = leerHistorialVentas();
        double ganancias = 0.0;
        for (Object obj : historialArray) {
            JSONObject venta = (JSONObject) obj;
            Object precioTotalObj = venta.get("PrecioTotal");
            if (precioTotalObj != null) {
                double precioTotal = Double.parseDouble(precioTotalObj.toString());
                ganancias += precioTotal;
            }
        }
        return String.valueOf(ganancias);
    }

    private String calcularGananciasGlobales() {
        double gananciasVentas = Double.parseDouble(calcularGanancias());
        double gananciasApartados = Double.parseDouble(calcularGananciasApartados());
        double gananciasGlobales = gananciasVentas + gananciasApartados;
        return String.valueOf(gananciasGlobales);
    }

    private JSONArray leerHistorialVentas() {
        JSONArray historialArray = new JSONArray();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("historial.json"));
            historialArray = (JSONArray) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return historialArray;
    }

//Ventas
    private void addVentasContent() {
        JPanel ventasPanel = new JPanel(null);
        ventasPanel.setBounds(200, 120, 980, 520);
        ventasPanel.setBackground(Color.decode("#F2F2F2"));
        contentPane.add(ventasPanel);

        JLabel tituloLabel = new JLabel("Ventas");
        tituloLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 24));
        tituloLabel.setBounds(410, 0, 200, 30);
        ventasPanel.add(tituloLabel);

        JLabel clienteLabel = new JLabel("Nombre del Cliente:");
        clienteLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 16));
        clienteLabel.setBounds(20, 60, 200, 30);
        ventasPanel.add(clienteLabel);

        JTextField clienteTextField = new JTextField();
        clienteTextField.setBounds(190, 60, 200, 30);
        clienteTextField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        clienteTextField.setBackground(Color.WHITE);
        clienteTextField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        ventasPanel.add(clienteTextField);

        JLabel catLabel = new JLabel("Catálogo:");
        catLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 16));
        catLabel.setBounds(20, 100, 150, 30);
        ventasPanel.add(catLabel);

        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.setBounds(20, 130, 400, 180);
        ventasPanel.add(scrollPane);
        table.setDefaultEditor(Object.class, null);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Font customFont = loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 12);
        table.setFont(customFont);
        Font customFont2 = loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 15);
        table.getTableHeader().setFont(customFont2);
        table.getTableHeader().setBackground(Color.decode("#262123"));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setRowHeight(30);

        JLabel capacidadLabel = new JLabel("Cantidad:");
        capacidadLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 16));
        capacidadLabel.setBounds(20, 340, 80, 30);
        ventasPanel.add(capacidadLabel);

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 100, 1);
        JSpinner spinner = new JSpinner(spinnerModel);
        spinner.setBounds(110, 340, 60, 30);
        spinner.setEditor(new JSpinner.DefaultEditor(spinner));
        ventasPanel.add(spinner);

        JLabel precioLabel = new JLabel("Precio unitario $MXN:");
        precioLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 16));
        precioLabel.setBounds(180, 340, 200, 30);
        ventasPanel.add(precioLabel);

        JTextField precioTextField = new JTextField();
        precioTextField.setBounds(350, 340, 100, 30);
        precioTextField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        precioTextField.setBackground(Color.WHITE);
        precioTextField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        ventasPanel.add(precioTextField);
        precioTextField.setEnabled(false);

        JLabel totalAPagarLabel = new JLabel("Total a pagar:");
        totalAPagarLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 16));
        totalAPagarLabel.setBounds(550, 340, 180, 30);
        ventasPanel.add(totalAPagarLabel);

        JTextField totalAPagarTextField = new JTextField();
        totalAPagarTextField.setBounds(675, 340, 150, 30);
        totalAPagarTextField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        totalAPagarTextField.setBackground(Color.decode("#EAEAEA"));
        totalAPagarTextField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        totalAPagarTextField.setEditable(false);
        ventasPanel.add(totalAPagarTextField);

        JLabel precioTotalLabel = new JLabel("Precio Total $MXN:");
        precioTotalLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 16));
        precioTotalLabel.setBounds(20, 390, 150, 30);
        ventasPanel.add(precioTotalLabel);

        JTextField precioTotalTextField = new JTextField();
        precioTotalTextField.setBounds(175, 390, 100, 30);
        precioTotalTextField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        precioTotalTextField.setBackground(Color.WHITE);
        precioTotalTextField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        ventasPanel.add(precioTotalTextField);
        precioTotalTextField.setEnabled(false);

        RoundedButton agregarButton = new RoundedButton("Agregar al Carrito");
        agregarButton.setBounds(20, 440, 200, 40);
        agregarButton.setBackground(Color.red);
        ventasPanel.add(agregarButton);

        RoundedButton cancelarButton = new RoundedButton("Limpiar");
        cancelarButton.setBounds(250, 440, 150, 40);
        ventasPanel.add(cancelarButton);

        JLabel carritoLabel = new JLabel("Carrito");
        carritoLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 20));
        carritoLabel.setBounds(490, 60, 100, 30);
        ventasPanel.add(carritoLabel);

        DefaultTableModel carritoModel = new DefaultTableModel();
        carritoModel.addColumn("Producto");
        carritoModel.addColumn("Cantidad");
        carritoModel.addColumn("Precio");

        JTable carritoTable = new JTable(carritoModel);
        JScrollPane carritoScrollPane = new JScrollPane(carritoTable);
        carritoScrollPane.setBounds(490, 110, 465, 200);
        ventasPanel.add(carritoScrollPane);

        carritoTable.setDefaultEditor(Object.class, null);
        carritoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        carritoTable.setFont(customFont);
        carritoTable.getTableHeader().setFont(customFont2);
        carritoTable.getTableHeader().setBackground(Color.decode("#262123"));
        carritoTable.getTableHeader().setForeground(Color.WHITE);
        carritoTable.setRowHeight(30);

        RoundedButton confirmarButton = new RoundedButton("Confirmar");
        confirmarButton.setBounds(490, 440, 125, 40);
        ventasPanel.add(confirmarButton);

        RoundedButton editarButton = new RoundedButton("Editar");
        editarButton.setBounds(640, 440, 125, 40);
        ventasPanel.add(editarButton);

        RoundedButton eliminarButton = new RoundedButton("Eliminar");
        eliminarButton.setBounds(790, 440, 125, 40);
        ventasPanel.add(eliminarButton);

        JSONArray jsonArray = readInventoryJSON();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Código");
        model.addColumn("Producto");
        model.addColumn("Stock");
        model.addColumn("Precio");

        for (Object obj : jsonArray) {
            JSONObject producto = (JSONObject) obj;
            String codigo = (String) producto.get("Codigo");
            String nombre = (String) producto.get("Producto");
            String stock = (String) producto.get("Stock");
            String precio = (String) producto.get("Precio venta");

            model.addRow(new Object[]{codigo, nombre, stock, precio});
        }

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String clienteNombre = clienteTextField.getText();
                if (clienteNombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese el nombre del cliente.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un producto.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int cantidad = (int) spinner.getValue();
                if (cantidad <= 0) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione al menos una cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String nombreProducto = (String) table.getValueAt(selectedRow, 1);
                boolean productoExistente = false;
                for (int i = 0; i < carritoTable.getRowCount(); i++) {
                    if (nombreProducto.equals(carritoTable.getValueAt(i, 0))) {
                        JOptionPane.showMessageDialog(null, "El producto ya está en el carrito. Edita los datos con el botón editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                        productoExistente = true;
                        break;
                    }
                }

                if (!productoExistente) {
                    try {
                        double precioUnitario = Double.parseDouble(precioTextField.getText());
                        double precioTotal = precioUnitario * cantidad;

                        String mensajeConfirmacion = "Cliente: " + clienteNombre + "\n"
                                + "Producto: " + table.getValueAt(selectedRow, 1) + "\n"
                                + "Cantidad: " + cantidad + "\n"
                                + "Precio a Pagar: $" + precioTotal + "\n\n"
                                + "¿Desea agregar este producto al carrito?";
                        int confirmacion = JOptionPane.showConfirmDialog(null, mensajeConfirmacion, "Confirmar", JOptionPane.YES_NO_OPTION);

                        if (confirmacion == JOptionPane.YES_OPTION) {
                            spinner.setValue(1);
                            precioTextField.setText("");
                            precioTotalTextField.setText("");

                            int stockDisponible = Integer.parseInt((String) table.getValueAt(selectedRow, 2));
                            if (cantidad > stockDisponible) {
                                JOptionPane.showMessageDialog(null, "No hay suficiente stock disponible.", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            table.setValueAt(stockDisponible - cantidad, selectedRow, 2);
                            carritoModel.addRow(new Object[]{nombreProducto, cantidad, precioTotal});
                            carritoTable.setModel(carritoModel);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El precio unitario no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                clienteTextField.setEnabled(false);
                actualizarTotalAPagar();
            }

            private void actualizarTotalAPagar() {
                double total = 0.0;
                for (int i = 0; i < carritoModel.getRowCount(); i++) {
                    total += (double) carritoModel.getValueAt(i, 2);
                }
                totalAPagarTextField.setText(String.valueOf(total));
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = carritoTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un producto del carrito.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String nombreProducto = (String) carritoModel.getValueAt(selectedRow, 0);
                int cantidadActual = (int) carritoModel.getValueAt(selectedRow, 1);

                String nuevaCantidadStr = JOptionPane.showInputDialog(null, "Ingrese la nueva cantidad de \"" + nombreProducto + "\":", String.valueOf(cantidadActual));

                if (nuevaCantidadStr != null && !nuevaCantidadStr.isEmpty()) {
                    try {
                        int nuevaCantidad = Integer.parseInt(nuevaCantidadStr);

                        int stockMaximo = obtenerStockMaximo(nombreProducto);

                        if (nuevaCantidad <= 0 || nuevaCantidad > stockMaximo) {
                            JOptionPane.showMessageDialog(null, "La cantidad debe ser un número entero positivo y no puede exceder el stock máximo disponible.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        carritoModel.setValueAt(nuevaCantidad, selectedRow, 1);

                        double precioUnitario = obtenerPrecioUnitario(nombreProducto);
                        double nuevoPrecioTotal = nuevaCantidad * precioUnitario;
                        carritoModel.setValueAt(nuevoPrecioTotal, selectedRow, 2);

                        actualizarCantidadEnCatalogo(nombreProducto, stockMaximo - nuevaCantidad);
                        actualizarTotalAPagar();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingrese un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            private void actualizarCantidadEnCatalogo(String nombreProducto, int nuevaCantidad) {
                for (int i = 0; i < table.getRowCount(); i++) {
                    String nombre = (String) table.getValueAt(i, 1);
                    if (nombreProducto.equals(nombre)) {
                        table.setValueAt(nuevaCantidad, i, 2);
                        break;
                    }
                }
            }

            private int obtenerStockMaximo(String nombreProducto) {
                int stockMaximo = 0;
                JSONArray inventario = readInventoryJSON();

                for (Object obj : inventario) {
                    JSONObject producto = (JSONObject) obj;
                    String nombre = (String) producto.get("Producto");
                    if (nombreProducto.equals(nombre)) {
                        stockMaximo = Integer.parseInt((String) producto.get("Stock"));
                        break;
                    }
                }

                return stockMaximo;
            }

            private JSONArray readInventoryJSON() {
                JSONArray jsonArray = new JSONArray();
                JSONParser parser = new JSONParser();

                try {
                    Object obj = parser.parse(new FileReader("inventario.JSON"));
                    jsonArray = (JSONArray) obj;
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }

                return jsonArray;
            }

            private void actualizarTotalAPagar() {
                double total = 0.0;
                for (int i = 0; i < carritoModel.getRowCount(); i++) {
                    double precioTotal = (double) carritoModel.getValueAt(i, 2);
                    total += precioTotal;
                }
                totalAPagarTextField.setText(String.valueOf(total));
            }

            private double obtenerPrecioUnitario(String nombreProducto) {
                JSONArray inventario = readInventoryJSON();

                for (Object obj : inventario) {
                    JSONObject producto = (JSONObject) obj;
                    String nombre = (String) producto.get("Producto");

                    if (nombreProducto.equals(nombre)) {
                        String precioStr = (String) producto.get("Precio venta");
                        return Double.parseDouble(precioStr);
                    }
                }
                return 0.0;
            }

        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                spinner.setValue(1);

                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {

                    String nombreProducto = (String) table.getValueAt(selectedRow, 1);
                    boolean productoEnCarrito = false;

                    for (int i = 0; i < carritoTable.getRowCount(); i++) {
                        if (nombreProducto.equals(carritoTable.getValueAt(i, 0))) {
                            productoEnCarrito = true;
                            break;
                        }
                    }

                    if (productoEnCarrito) {
                        return;
                    }

                    String precioProducto = (String) table.getValueAt(selectedRow, 3);
                    precioTotalTextField.setText(precioProducto);
                    precioTextField.setText(precioProducto);

                    String stockDisponibleStr = (String) table.getValueAt(selectedRow, 2);
                    int stockDisponible = Integer.parseInt(stockDisponibleStr);
                    SpinnerNumberModel spinnerModel;

                    if (stockDisponible > 0) {
                        spinnerModel = new SpinnerNumberModel(1, 1, stockDisponible, 1);
                        spinner.setEnabled(true);
                    } else {
                        spinnerModel = new SpinnerNumberModel(0, 0, 0, 0);
                        spinner.setEnabled(false);
                    }

                    spinner.setModel(spinnerModel);
                }
            }
        });

        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double precioUnitario = Double.parseDouble(precioTextField.getText());

                int cantidad = (int) spinner.getValue();

                double precioTotal = precioUnitario * cantidad;

                precioTotalTextField.setText(String.valueOf(precioTotal));
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = carritoTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un producto del carrito.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String nombreProducto = (String) carritoModel.getValueAt(selectedRow, 0);
                int cantidadEliminada = (int) carritoModel.getValueAt(selectedRow, 1);

                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar este producto del carrito?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    carritoModel.removeRow(selectedRow);

                    actualizarCantidadEnCatalogo(nombreProducto, cantidadEliminada);

                    actualizarTotalAPagar();
                }
            }

            private void actualizarCantidadEnCatalogo(String nombreProducto, int cantidadEliminada) {
                for (int i = 0; i < table.getRowCount(); i++) {
                    String nombre = (String) table.getValueAt(i, 1);
                    if (nombreProducto.equals(nombre)) {
                        int stockActual = (int) table.getValueAt(i, 2);
                        table.setValueAt(stockActual + cantidadEliminada, i, 2);
                        break;
                    }
                }
            }

            private void actualizarTotalAPagar() {
                double total = 0.0;
                for (int i = 0; i < carritoModel.getRowCount(); i++) {
                    double precioTotal = (double) carritoModel.getValueAt(i, 2);
                    total += precioTotal;
                }
                totalAPagarTextField.setText(String.valueOf(total));
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spinner.setValue(1);
                precioTextField.setText("");
                precioTotalTextField.setText("");
            }
        });

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String clienteNombre = clienteTextField.getText();

                if (clienteNombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese el nombre del cliente.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int rowCount = carritoModel.getRowCount();

                if (rowCount == 0) {
                    JOptionPane.showMessageDialog(null, "No hay productos en el carrito.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Confirmación de la compra
                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea confirmar la compra?", "Confirmar compra", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    JSONArray historialVentas = new JSONArray();
                    try (FileReader reader = new FileReader("historial.json")) {
                        JSONParser parser = new JSONParser();
                        Object obj = parser.parse(reader);
                        if (obj instanceof JSONArray) {
                            historialVentas = (JSONArray) obj;
                        }
                    } catch (IOException | ParseException ex) {
                        ex.printStackTrace();
                    }

                    for (int i = 0; i < rowCount; i++) {
                        String nombreProducto = (String) carritoModel.getValueAt(i, 0);
                        int cantidad = (int) carritoModel.getValueAt(i, 1);
                        double precioTotal = (double) carritoModel.getValueAt(i, 2);

                        for (int j = 0; j < model.getRowCount(); j++) {
                            Object stockValue = model.getValueAt(j, 2);
                            Object ventasValue = model.getValueAt(j, 3);

                            if (stockValue != null && ventasValue != null && stockValue instanceof Integer && ventasValue instanceof Integer) {
                                int stockActual = (int) stockValue;
                                int ventas = (int) ventasValue;

                                model.setValueAt(stockActual - cantidad, j, 2);
                                model.setValueAt(ventas + 1, j, 4);
                            } else {

                            }
                        }

                        JSONObject venta = new JSONObject();
                        venta.put("Cliente", clienteNombre);
                        venta.put("Producto", nombreProducto);
                        venta.put("Cantidad", cantidad);
                        venta.put("PrecioTotal", precioTotal);

                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date();
                        String fechaVenta = dateFormat.format(date);
                        venta.put("FechaVenta", fechaVenta);

                        historialVentas.add(venta);
                    }

                    JSONArray inventario = new JSONArray();
                    try (FileReader reader = new FileReader("inventario.json")) {
                        JSONParser parser = new JSONParser();
                        Object obj = parser.parse(reader);
                        if (obj instanceof JSONArray) {
                            inventario = (JSONArray) obj;
                        }
                    } catch (IOException | ParseException ex) {
                        ex.printStackTrace();
                    }

                    for (int i = 0; i < rowCount; i++) {
                        String nombreProducto = (String) carritoModel.getValueAt(i, 0);

                        for (Object obj : inventario) {
                            JSONObject producto = (JSONObject) obj;
                            String nombre = (String) producto.get("Producto");
                            if (nombreProducto.equals(nombre)) {
                                int ventas = Integer.parseInt((String) producto.get("Ventas"));
                                producto.put("Ventas", String.valueOf(ventas + 1));
                                break;
                            }
                        }
                    }

                    for (int i = 0; i < carritoModel.getRowCount(); i++) {
                        String nombreProducto = (String) carritoModel.getValueAt(i, 0);
                        for (Object obj : inventario) {
                            JSONObject producto = (JSONObject) obj;
                            String nombre = (String) producto.get("Producto");

                            if (nombreProducto.equals(nombre)) {
                                int stockActual = Integer.parseInt((String) producto.get("Stock"));
                                int cantidadVendida = (int) carritoModel.getValueAt(i, 1);

                                producto.put("Stock", String.valueOf(stockActual - cantidadVendida));

                                break;
                            }
                        }
                    }

                    try (FileWriter file = new FileWriter("inventario.json")) {
                        file.write(inventario.toJSONString());
                        file.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    try (FileWriter file = new FileWriter("historial.json")) {
                        file.write(historialVentas.toJSONString());
                        file.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    carritoModel.setRowCount(0);
                    clienteTextField.setEnabled(true);
                    clienteTextField.setText("");
                    JOptionPane.showMessageDialog(null, "Venta realizada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {

                }
            }

        });

        table.setModel(model);

        ventasPanel.add(scrollPane);
    }

    private JSONArray readInventoryJSON() {
        JSONArray jsonArray = new JSONArray();
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("inventario.JSON"));
            jsonArray = (JSONArray) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

//Menu principal
    private void addContentPane() {
        JPanel contentPane2 = new JPanel(new GridLayout(2, 3, 10, 10));
        contentPane2.setBounds(200, 120, 980, 520);
        contentPane.add(contentPane2);

        String[] opciones = {"Inventario", "Ventas", "Apartados",  "Salir"};
        for (String opcion : opciones) {
            RoundedButton boton = new RoundedButton(opcion);
            boton.setBackground(Color.decode("#262123"));
            boton.setForeground(Color.WHITE);
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (opcion) {
                        case "Inventario":
                            contentPane.removeAll();
                            addLogo();
                            addWelcomeLabel();
                            addSideMenu();
                            addInventoryContent();
                            contentPane.revalidate();
                            contentPane.repaint();
                            break;
                        case "Ventas":
                            contentPane.removeAll();
                            addLogo();
                            addWelcomeLabel();
                            addSideMenu();
                            addVentasContent();
                            contentPane.revalidate();
                            contentPane.repaint();
                            break;
                        case "Apartados":
                            contentPane.removeAll();
                            addLogo();
                            addWelcomeLabel();
                            addSideMenu();
                            addApartadosContent();
                            contentPane.revalidate();
                            contentPane.repaint();
                            break;
                        case "Finanzas Ventas":
                            contentPane.removeAll();
                            addLogo();
                            addWelcomeLabel();
                            addSideMenu();
                            addFinanzasContent();
                            contentPane.revalidate();
                            contentPane.repaint();
                            break;
                        case "Finanzas Apartados":
                            contentPane.removeAll();
                            addLogo();
                            addWelcomeLabel();
                            addSideMenu();
                            addFinanzasApartadosContent();
                            contentPane.revalidate();
                            contentPane.repaint();
                            break;
                        case "Salir":
                            int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
                            if (confirm == JOptionPane.YES_OPTION) {
                                System.exit(0);
                            }
                            break;
                    }
                }
            });
            contentPane2.add(boton);
        }
    }

//Inventario
    public JTable inventoryTable;

    private void search(String searchTerm) {
        DefaultTableModel model = (DefaultTableModel) inventoryTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        inventoryTable.setRowSorter(sorter);

        RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + searchTerm);
        sorter.setRowFilter(rowFilter);
    }

    private void addInventoryContent() {
        JPanel inventoryPanel = new JPanel(null);
        inventoryPanel.setBounds(200, 120, 980, 520);
        inventoryPanel.setBackground(Color.decode("#F2F2F2"));
        contentPane.add(inventoryPanel);

        JLabel tituloLabel = new JLabel("Inventario");
        tituloLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 24));
        tituloLabel.setBounds(410, 0, 200, 30);
        inventoryPanel.add(tituloLabel);

        JLabel buscarLabel = new JLabel("Buscador");
        buscarLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        buscarLabel.setBounds(745, 32, 200, 30);
        inventoryPanel.add(buscarLabel);

        JTextField searchField = new JTextField();
        searchField.setBounds(815, 30, 150, 30);
        searchField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        searchField.setBackground(Color.WHITE);
        searchField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));

        inventoryPanel.add(searchField);

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(searchField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(searchField.getText());
            }

        });

        DefaultTableModel model = new DefaultTableModel();
        inventoryTable = new JTable();

        JSONArray jsonArray = JSONHandler.readJSON();

        model.addColumn("Codigo");
        model.addColumn("Categoria");
        model.addColumn("Producto");
        model.addColumn("Talla");
        model.addColumn("Precio compra");
        model.addColumn("Precio venta");
        model.addColumn("Utilidad");
        model.addColumn("Stock");
        model.addColumn("Ventas");

        inventoryTable.setDefaultEditor(Object.class, null);
        inventoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Font customFont = loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 12);
        inventoryTable.setFont(customFont);
        Font customFont2 = loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14);
        inventoryTable.getTableHeader().setFont(customFont2);
        inventoryTable.getTableHeader().setBackground(Color.decode("#262123"));
        inventoryTable.getTableHeader().setForeground(Color.WHITE);
        inventoryTable.setRowHeight(30);

        for (Object obj : jsonArray) {
            JSONObject producto = (JSONObject) obj;
            String codigo = (String) producto.get("Codigo");
            String categoria = (String) producto.get("Categoria");
            String productos = (String) producto.get("Producto");
            String talla = (String) producto.get("Talla");
            String precioCompra = String.valueOf(producto.get("Precio compra"));
            String precioVenta = String.valueOf(producto.get("Precio venta"));
            String utilidad = String.valueOf(producto.get("Utilidad"));
            String stock = String.valueOf(parseInteger((String) producto.get("Stock")));
            String ventas = String.valueOf(parseInteger((String) producto.get("Ventas")));

            model.addRow(new Object[]{codigo, categoria, productos, talla, precioCompra, precioVenta, utilidad, stock, ventas});
        }

        inventoryTable.setModel(model);

        JScrollPane scrollPane = new JScrollPane(inventoryTable);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.setBounds(20, 80, 940, 370);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#262123"), 2));
        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());

        scrollPane.setBorder(new LineBorder(Color.BLACK, 1) {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                super.paintBorder(c, g, x, y, width, height);
                Graphics2D graphics = (Graphics2D) g;
                graphics.drawRoundRect(x, y, width - 1, height - 1, 20, 20);
            }
        });
        inventoryPanel.add(scrollPane);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < inventoryTable.getColumnCount(); i++) {
            inventoryTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        int buttonY = 460;
        int buttonWidth = 100;
        int buttonHeight = 40;

        RoundedButton btnEliminar = new RoundedButton("Eliminar");
        btnEliminar.setBounds(980 - 20 - buttonWidth, buttonY, buttonWidth, buttonHeight);
        inventoryPanel.add(btnEliminar);

        RoundedButton btnEditar = new RoundedButton("Editar");
        btnEditar.setBounds(980 - 20 - 2 * buttonWidth - 10, buttonY, buttonWidth, buttonHeight);
        inventoryPanel.add(btnEditar);

        RoundedButton btnAgregar = new RoundedButton("Agregar");
        btnAgregar.setBounds(980 - 20 - 3 * buttonWidth - 20, buttonY, buttonWidth, buttonHeight);
        inventoryPanel.add(btnAgregar);

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarProductoDialog dialog = new AgregarProductoDialog(PullAndBearTrabajador.this, model, inventoryTable);
                dialog.setVisible(true);
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = inventoryTable.getSelectedRow();
                if (selectedRow != -1) {
                    JSONObject selectedProduct = getSelectedProduct(selectedRow);
                    if (selectedProduct != null) {
                        EditarProductoDialog dialog = new EditarProductoDialog(selectedProduct, inventoryTable, selectedRow);
                        dialog.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(PullAndBearTrabajador.this, "Por favor, seleccione un producto para editar.", "Editar Producto", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(PullAndBearTrabajador.this, "Por favor, seleccione un producto para editar.", "Editar Producto", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });

    }

    private int parseInteger(String value) {
        if (value == null || "null".equals(value)) {
            return 1;
        } else {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return 1;
            }
        }
    }

    private void eliminarProducto() {
        int selectedRow = inventoryTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar.", "Seleccione Producto", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String codigoProducto = (String) inventoryTable.getValueAt(selectedRow, 0);

        int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este producto?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) inventoryTable.getModel();
            model.removeRow(selectedRow);

            JSONArray jsonArray = JSONHandler.readJSON();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject producto = (JSONObject) jsonArray.get(i);
                String codigo = (String) producto.get("Codigo");
                if (codigo.equals(codigoProducto)) {
                    jsonArray.remove(i);
                    break;
                }
            }

            JSONHandler.writeJSON(jsonArray);
        }
    }

    private JSONObject getSelectedProduct(int rowIndex) {
        DefaultTableModel model = (DefaultTableModel) inventoryTable.getModel();

        String codigo = (String) model.getValueAt(rowIndex, 0);
        String categoria = (String) model.getValueAt(rowIndex, 1);
        String producto = (String) model.getValueAt(rowIndex, 2);
        String talla = (String) model.getValueAt(rowIndex, 3);
        double precioCompra = Double.parseDouble((String) model.getValueAt(rowIndex, 4));
        double precioVenta = Double.parseDouble((String) model.getValueAt(rowIndex, 5));
        double utilidad = precioVenta - precioCompra;
        int stock = Integer.parseInt((String) model.getValueAt(rowIndex, 7));

        JSONObject productoJSON = new JSONObject();
        productoJSON.put("Codigo", codigo);
        productoJSON.put("Categoria", categoria);
        productoJSON.put("Producto", producto);
        productoJSON.put("Talla", talla);
        productoJSON.put("Precio compra", precioCompra);
        productoJSON.put("Precio venta", precioVenta);
        productoJSON.put("Utilidad", utilidad);
        productoJSON.put("Stock", stock);

        return productoJSON;
    }

//Fonts
    private Font loadCustomFont(String fontName) {
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontName));
            return customFont.deriveFont(Font.PLAIN, 16);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("Arial", Font.PLAIN, 16);
        }
    }

    private Font loadCustomFont(String fontName, int style, int size) {
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontName));
            return customFont.deriveFont(style, size);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("Arial", style, size);

        }
    }

//Botones
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

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            PullAndBearTrabajador frame = new PullAndBearTrabajador();
            frame.setVisible(true);
        });
    }

}
