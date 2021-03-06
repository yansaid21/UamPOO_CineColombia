/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.ControladorBoleto;
import Controladores.ControladorFuncion;
import Controladores.ControladorPelicula;
import Controladores.ControladorSala;
import Controladores.ControladorSilla;
import Controladores.ControladorUsuario;
import Modelos.Boleto;
import Modelos.Funcion;
import Modelos.Pelicula;
import Modelos.Sala;
import Modelos.Silla;
import Modelos.Usuario;
import java.awt.event.ItemEvent;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Geraldine Romero
 */
public class IntefarzCliente extends javax.swing.JFrame {

    ControladorBoleto miControladorBoleto;
    ControladorFuncion miControladorFuncion;
    ControladorPelicula miControladorPelicula;
    ControladorSala miControladorSala;
    ControladorSilla miControladorSilla;
    ControladorUsuario miControladorUsuario;
    String urlServidor = "http://127.0.0.1:8080";
    LinkedList<Funcion> misFunciones;
    int indexFuciones;
    LinkedList<Silla> misSillas;

    /**
     * Creates new form IntefarzCliente
     */
    public IntefarzCliente() {
        initComponents();
        boxTipoBoleto();

        this.miControladorSala = new ControladorSala(urlServidor, "/salas");
        this.miControladorPelicula = new ControladorPelicula(urlServidor, "/peliculas");
        this.miControladorFuncion = new ControladorFuncion(urlServidor, "/funciones");
        this.miControladorUsuario = new ControladorUsuario(urlServidor, "/usuarios");
        this.miControladorBoleto = new ControladorBoleto(urlServidor, "/boletos");
        this.miControladorSilla = new ControladorSilla(urlServidor, "/sillas");
        this.boxSillaBoleto.removeAllItems();
        boxTipoFuncion();
        actualizarTablaPeliculas();
        actualizarTablaUsuarios();
        actualizarTablaBoletos();
    }

    public void actualizarTablaPeliculas() {
        String nombresColumnas[] = {"Nombre", "A??o", "Tipo"};
        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);
        this.tbPelicula.setModel(miModelo);
        LinkedList<Pelicula> estudiantes = this.miControladorPelicula.listar();
        for (Pelicula actual : estudiantes) {
            String fila[] = new String[nombresColumnas.length];
            fila[0] = actual.getNombre();
            fila[1] = "" + actual.getAno();
            fila[2] = actual.getTipo();
            miModelo.addRow(fila);
        }
    }

    public void actualizarTablaUsuarios() {
        String nombresColumnas[] = {"Cedula", "Nombre", "Email", "A??o Nacimiento"};
        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);
        this.tbUsuarios.setModel(miModelo);
        LinkedList<Usuario> usuarios = this.miControladorUsuario.listar();
        for (Usuario actual : usuarios) {
            String fila[] = new String[nombresColumnas.length];
            fila[0] = actual.getCedula();
            fila[1] = actual.getNombre();
            fila[2] = actual.getEmail();
            fila[3] = "" + actual.getAnoNacimiento();
            miModelo.addRow(fila);
        }
    }

    public void actualizarTablaBoletos() {
        String nombresColumnas[] = {"Nombre Usuario", "Tipo", "Valor", "Funci??n", "silla"};
        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);
        this.tbBoletos.setModel(miModelo);
        LinkedList<Boleto> boletos = this.miControladorBoleto.listar();
        for (Boleto actual : boletos) {
            String numeroSilla = " " + actual.getMiSilla().getNumero();
            String letraSilla = actual.getMiSilla().getLetra();
            System.out.println("ID Boleto " + actual.getId());
            Funcion funcionAux = actual.getMiFuncion();
            System.out.println("Hora funcion " + funcionAux.getHora());
            String salaHora = datosFuncion(funcionAux);
            String fila[] = new String[nombresColumnas.length];
            fila[0] = actual.getMiUsuario().getNombre();
            fila[1] = actual.getTipo();
            fila[2] = "" + actual.getValor();
            fila[3] = salaHora;
            fila[4] = letraSilla + numeroSilla;
            miModelo.addRow(fila);
        }
    }

    public void boxTipoBoleto() {
        this.boxTipoBoleto.removeAllItems();
        this.boxTipoBoleto.addItem("Nino");
        this.boxTipoBoleto.addItem("Adulto");
    }

    public void boxTipoFuncion() {
        this.boxFuncionBoleta.removeAllItems();
        this.misFunciones = this.miControladorFuncion.listar();

        for (Funcion funcionActual : this.misFunciones) {
            String resultado = datosFuncion(funcionActual);
            this.boxFuncionBoleta.addItem(resultado);

        }
    }

    public String datosFuncion(Funcion funcionActual) {
        String nombreSala = funcionActual.getMiSala().getNombre();
        String nombrePelicula = funcionActual.getMiPelicula().getNombre();
        String laFecha = "" + funcionActual.getDia() + "-" + funcionActual.getMes() + "-" + funcionActual.getAno();
        String salaHora = nombreSala + ": " + nombrePelicula + " Hora: " + funcionActual.getHora() + " fecha: " + laFecha;
        return salaHora;
    }

    public void boxTipoSilla() {
        this.boxSillaBoleto.removeAllItems();

        System.out.println("selected item " + this.boxFuncionBoleta.getSelectedItem());
        Funcion funcionAux = this.misFunciones.get(this.indexFuciones);
        this.boxSillaBoleto.removeAllItems();
        Sala SalaAux = funcionAux.getMiSala();
        System.out.println("nombre sala " + SalaAux.getNombre());
        this.misSillas = this.miControladorSilla.listarPorSala(SalaAux.getId());
        for (Silla sillaActual : misSillas) {
            this.boxSillaBoleto.addItem(sillaActual.getLetra() + " " + sillaActual.getNumero());

        }

    }
    
    public void LimpiarCamposBoleto(){
        this.txtIdBoleto.setText("");
        this.txtValorBoleto.setText("");
        this.txtCedulaUsuarioBoleto.setText("");
    }
    
    public void LimpiarCamposUsuario(){
        this.txtIdUsuario.setText("");
        this.txtNombreUsuario.setText("");
        this.txtEmailUsuario.setText("");
        this.txtA??oNacimientoUsuario.setText("");
        this.txtCedulaUsuario.setText("");
    }
    
    public void LimpiarCamposPelicula(){
        this.txtIdPelicula.setText("");
        this.txtNombrePelicula.setText("");
        this.txtTipoPelicula.setText("");
        this.txtA??oPelicula.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtValorBoleto = new javax.swing.JTextField();
        txtIdBoleto = new javax.swing.JTextField();
        txtCedulaUsuarioBoleto = new javax.swing.JTextField();
        boxFuncionBoleta = new javax.swing.JComboBox<>();
        boxTipoBoleto = new javax.swing.JComboBox<>();
        boxSillaBoleto = new javax.swing.JComboBox<>();
        btnCrearBoleto = new javax.swing.JButton();
        btnBuscarBoleto = new javax.swing.JButton();
        btnEditarBoleto = new javax.swing.JButton();
        btnEliminarBoleto = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbBoletos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtIdPelicula = new javax.swing.JTextField();
        txtNombrePelicula = new javax.swing.JTextField();
        txtA??oPelicula = new javax.swing.JTextField();
        txtTipoPelicula = new javax.swing.JTextField();
        btnCrearPelicula = new javax.swing.JButton();
        btnBuscarPelicula = new javax.swing.JButton();
        btnEditarPelicula = new javax.swing.JButton();
        btnEliminarPelicula = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPelicula = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtIdUsuario = new javax.swing.JTextField();
        txtCedulaUsuario = new javax.swing.JTextField();
        txtNombreUsuario = new javax.swing.JTextField();
        txtEmailUsuario = new javax.swing.JTextField();
        txtA??oNacimientoUsuario = new javax.swing.JTextField();
        btnCrearUsuario = new javax.swing.JButton();
        btnBuscarUsuario = new javax.swing.JButton();
        btnEditarUsuario = new javax.swing.JButton();
        btnEliminarUsuario = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbUsuarios = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setFont(new java.awt.Font("Wide Latin", 0, 18)); // NOI18N
        jLabel1.setText("CineColombia");

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Id");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Valor");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Funci??n");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tipo");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("C??dula Usuario");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Silla");

        txtValorBoleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorBoletoActionPerformed(evt);
            }
        });

        txtIdBoleto.setEnabled(false);
        txtIdBoleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdBoletoActionPerformed(evt);
            }
        });

        txtCedulaUsuarioBoleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaUsuarioBoletoActionPerformed(evt);
            }
        });

        boxFuncionBoleta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        boxFuncionBoleta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxFuncionBoletaItemStateChanged(evt);
            }
        });

        boxTipoBoleto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        boxSillaBoleto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnCrearBoleto.setBackground(new java.awt.Color(153, 153, 255));
        btnCrearBoleto.setText("Crear");
        btnCrearBoleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearBoletoActionPerformed(evt);
            }
        });

        btnBuscarBoleto.setBackground(new java.awt.Color(153, 153, 255));
        btnBuscarBoleto.setText("Buscar");
        btnBuscarBoleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarBoletoActionPerformed(evt);
            }
        });

        btnEditarBoleto.setBackground(new java.awt.Color(153, 153, 255));
        btnEditarBoleto.setText("Editar");
        btnEditarBoleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarBoletoActionPerformed(evt);
            }
        });

        btnEliminarBoleto.setBackground(new java.awt.Color(153, 153, 255));
        btnEliminarBoleto.setText("Eliminar");
        btnEliminarBoleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarBoletoActionPerformed(evt);
            }
        });

        tbBoletos.setBackground(new java.awt.Color(204, 204, 255));
        tbBoletos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tbBoletos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtValorBoleto, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                    .addComponent(txtIdBoleto, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                    .addComponent(boxFuncionBoleta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCedulaUsuarioBoleto, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                    .addComponent(boxTipoBoleto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(boxSillaBoleto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(btnCrearBoleto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarBoleto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditarBoleto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarBoleto))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boxTipoBoleto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(txtIdBoleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtCedulaUsuarioBoleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(boxSillaBoleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtValorBoleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(boxFuncionBoleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrearBoleto)
                    .addComponent(btnBuscarBoleto)
                    .addComponent(btnEditarBoleto)
                    .addComponent(btnEliminarBoleto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Boletos", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 153, 153));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Id");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Nombre");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("A??o");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Tipo");

        txtIdPelicula.setEnabled(false);

        btnCrearPelicula.setBackground(new java.awt.Color(255, 51, 51));
        btnCrearPelicula.setText("Crear");
        btnCrearPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearPeliculaActionPerformed(evt);
            }
        });

        btnBuscarPelicula.setBackground(new java.awt.Color(255, 51, 51));
        btnBuscarPelicula.setText("Buscar");
        btnBuscarPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPeliculaActionPerformed(evt);
            }
        });

        btnEditarPelicula.setBackground(new java.awt.Color(255, 51, 51));
        btnEditarPelicula.setText("Editar");
        btnEditarPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarPeliculaActionPerformed(evt);
            }
        });

        btnEliminarPelicula.setBackground(new java.awt.Color(255, 51, 51));
        btnEliminarPelicula.setText("Eliminar");
        btnEliminarPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPeliculaActionPerformed(evt);
            }
        });

        tbPelicula.setBackground(new java.awt.Color(255, 204, 204));
        tbPelicula.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tbPelicula);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTipoPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtA??oPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNombrePelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(79, 79, 79)
                                    .addComponent(txtIdPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(btnCrearPelicula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarPelicula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditarPelicula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarPelicula))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtNombrePelicula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtA??oPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtTipoPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrearPelicula)
                    .addComponent(btnBuscarPelicula)
                    .addComponent(btnEditarPelicula)
                    .addComponent(btnEliminarPelicula))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Pel??culas", jPanel2);

        jPanel3.setBackground(new java.awt.Color(153, 255, 153));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Id");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("C??dula");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Nombre");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Email");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("A??o Nacimiento");

        txtIdUsuario.setEnabled(false);
        txtIdUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdUsuarioActionPerformed(evt);
            }
        });

        txtCedulaUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaUsuarioActionPerformed(evt);
            }
        });

        txtNombreUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreUsuarioActionPerformed(evt);
            }
        });

        txtEmailUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailUsuarioActionPerformed(evt);
            }
        });

        txtA??oNacimientoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtA??oNacimientoUsuarioActionPerformed(evt);
            }
        });

        btnCrearUsuario.setBackground(new java.awt.Color(51, 255, 51));
        btnCrearUsuario.setText("Crear");
        btnCrearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearUsuarioActionPerformed(evt);
            }
        });

        btnBuscarUsuario.setBackground(new java.awt.Color(51, 255, 51));
        btnBuscarUsuario.setText("Buscar");
        btnBuscarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarUsuarioActionPerformed(evt);
            }
        });

        btnEditarUsuario.setBackground(new java.awt.Color(51, 255, 51));
        btnEditarUsuario.setText("Editar");
        btnEditarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarUsuarioActionPerformed(evt);
            }
        });

        btnEliminarUsuario.setBackground(new java.awt.Color(51, 255, 51));
        btnEliminarUsuario.setText("Eliminar");
        btnEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUsuarioActionPerformed(evt);
            }
        });

        tbUsuarios.setBackground(new java.awt.Color(204, 255, 204));
        tbUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbUsuarios);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtA??oNacimientoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmailUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCedulaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(btnCrearUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditarUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarUsuario)))
                .addContainerGap(283, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtCedulaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtEmailUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtA??oNacimientoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrearUsuario)
                    .addComponent(btnBuscarUsuario)
                    .addComponent(btnEditarUsuario)
                    .addComponent(btnEliminarUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Usuarios", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(280, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(231, 231, 231))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtValorBoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorBoletoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorBoletoActionPerformed

    private void txtIdBoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdBoletoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdBoletoActionPerformed

    private void txtCedulaUsuarioBoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaUsuarioBoletoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaUsuarioBoletoActionPerformed

    private void txtIdUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdUsuarioActionPerformed

    private void txtCedulaUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaUsuarioActionPerformed

    private void txtNombreUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreUsuarioActionPerformed

    private void txtEmailUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailUsuarioActionPerformed

    private void txtA??oNacimientoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtA??oNacimientoUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtA??oNacimientoUsuarioActionPerformed

    private void btnCrearBoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearBoletoActionPerformed
        // TODO add your handling code here:
        String stringValor = txtValorBoleto.getText();
        String tipo = "" + this.boxTipoBoleto.getSelectedItem();
        Usuario usuarioaux = this.miControladorUsuario.buscarPorCedula(txtCedulaUsuarioBoleto.getText());
        double valor = Double.parseDouble(stringValor);
        if (usuarioaux == null) {
            JOptionPane.showMessageDialog(this, "Usuario no encotrado");
        } else {
            System.out.println("encontrado con exito");
            Silla miSilla = this.misSillas.get(this.boxSillaBoleto.getSelectedIndex());

            Boleto NuevoBoleto = new Boleto(valor, tipo);
            Funcion funcionAux = this.misFunciones.get(this.indexFuciones);

            NuevoBoleto.setMiSilla(miSilla);
            NuevoBoleto.setMiFuncion(funcionAux);
            NuevoBoleto.setMiUsuario(usuarioaux);
//            miSilla.setMiBoleto(NuevoBoleto);
//            usuarioaux.getMisBoletos().add(NuevoBoleto);

            NuevoBoleto = this.miControladorBoleto.crear(NuevoBoleto);
            NuevoBoleto = this.miControladorBoleto.actualizarRelaciones(NuevoBoleto, miSilla.getId(), "silla");
            NuevoBoleto = this.miControladorBoleto.actualizarRelaciones(NuevoBoleto, funcionAux.getId(), "funcion");
            NuevoBoleto = this.miControladorBoleto.actualizarRelaciones(NuevoBoleto, usuarioaux.getId(), "usuario");
            this.txtIdBoleto.setText(NuevoBoleto.getId());
            actualizarTablaBoletos();
            JOptionPane.showMessageDialog(this, "Boleto creado con id "+NuevoBoleto.getId());
        }


    }//GEN-LAST:event_btnCrearBoletoActionPerformed

    private void btnCrearPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearPeliculaActionPerformed
        // TODO add your handling code here:
        String nombre = this.txtNombrePelicula.getText();
        int ano = Integer.parseInt(this.txtA??oPelicula.getText());
        String tipo = this.txtTipoPelicula.getText();

        Pelicula nuevaPelicula = new Pelicula(nombre, ano, tipo);
        nuevaPelicula = this.miControladorPelicula.crear(nuevaPelicula);

        if (nuevaPelicula == null) {
            JOptionPane.showMessageDialog(null, "Problemas al crear la pelicula");
        } else {
            JOptionPane.showMessageDialog(null, "Pelicula creada exitosamente con id " + nuevaPelicula.getId());
            this.txtIdPelicula.setText(nuevaPelicula.getId());
            actualizarTablaPeliculas();
        }

    }//GEN-LAST:event_btnCrearPeliculaActionPerformed

    private void btnBuscarPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPeliculaActionPerformed
        // TODO add your handling code here:
        String nombre = this.txtNombrePelicula.getText();
        Pelicula encontrado = this.miControladorPelicula.buscarPorNombre(nombre);
        if (encontrado != null) {
            this.txtIdPelicula.setText(encontrado.getId());
            this.txtNombrePelicula.setText(encontrado.getNombre());
            this.txtA??oPelicula.setText(""+encontrado.getAno());
            this.txtTipoPelicula.setText(encontrado.getTipo());
        } else {
            JOptionPane.showMessageDialog(null, "No se encontr?? la pel??cula");
        }
    }//GEN-LAST:event_btnBuscarPeliculaActionPerformed

    private void btnEditarPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPeliculaActionPerformed
        // TODO add your handling code here:
        try {
            String id = this.txtIdPelicula.getText();
            String nombre = this.txtNombrePelicula.getText();
            int ano = Integer.parseInt(this.txtA??oPelicula.getText());
            String tipo = this.txtTipoPelicula.getText();

            Pelicula peliculaActualizada = new Pelicula(nombre, ano, tipo);
            peliculaActualizada.setId(id);

            Pelicula actualizado = this.miControladorPelicula.actualizar(peliculaActualizada);

            this.txtIdPelicula.setText(actualizado.getId());
            this.txtNombrePelicula.setText(actualizado.getNombre());
            this.txtA??oPelicula.setText("" + actualizado.getAno());
            this.txtTipoPelicula.setText(actualizado.getTipo());
            JOptionPane.showMessageDialog(this, "Pelicula actualizada con ??xito");
            actualizarTablaPeliculas();
            LimpiarCamposPelicula();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la pelicula " + e);
        }

    }//GEN-LAST:event_btnEditarPeliculaActionPerformed

    private void btnEliminarPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPeliculaActionPerformed
        // TODO add your handling code here:
        try {
            String id = this.txtIdPelicula.getText();
            this.miControladorPelicula.eliminar(id);

            JOptionPane.showMessageDialog(null, "Eliminaci??n exitosa");
            actualizarTablaPeliculas();
            LimpiarCamposPelicula();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Eliminaci??n sin ??xito " + e);
        }

    }//GEN-LAST:event_btnEliminarPeliculaActionPerformed

    private void btnCrearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearUsuarioActionPerformed
        // TODO add your handling code here:
        String cedula = this.txtCedulaUsuario.getText();
        String nombre = this.txtNombreUsuario.getText();
        String email = this.txtEmailUsuario.getText();
        int anoNacimiento = Integer.parseInt(this.txtA??oNacimientoUsuario.getText());

        Usuario nuevoUsuario = new Usuario(cedula, nombre, email, anoNacimiento);
        nuevoUsuario = this.miControladorUsuario.crear(nuevoUsuario);

        if (nuevoUsuario == null) {
            JOptionPane.showMessageDialog(null, "Problemas al crear el usuario");
        } else {
            JOptionPane.showMessageDialog(null, "Usuario creado exitosamente con id " + nuevoUsuario.getId());
            this.txtIdUsuario.setText(nuevoUsuario.getId());
            actualizarTablaUsuarios();
        }
    }//GEN-LAST:event_btnCrearUsuarioActionPerformed

    private void btnBuscarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarUsuarioActionPerformed
        // TODO add your handling code here:
        String cedula = this.txtCedulaUsuario.getText();
        Usuario encontrado = this.miControladorUsuario.buscarPorCedula(cedula);
        if (encontrado != null) {
            this.txtIdUsuario.setText(encontrado.getId());
            this.txtCedulaUsuario.setText(encontrado.getCedula());
            this.txtNombreUsuario.setText(encontrado.getNombre());
            this.txtEmailUsuario.setText(encontrado.getEmail());
            this.txtA??oNacimientoUsuario.setText("" + encontrado.getAnoNacimiento());

        } else {
            JOptionPane.showMessageDialog(null, "No se encontr?? el usuario");
        }
    }//GEN-LAST:event_btnBuscarUsuarioActionPerformed

    private void btnEditarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarUsuarioActionPerformed
        // TODO add your handling code here:
        try {
            String id = this.txtIdUsuario.getText();
            String cedula = this.txtCedulaUsuario.getText();
            String nombre = this.txtNombreUsuario.getText();
            String email = this.txtEmailUsuario.getText();
            int anoNacimiento = Integer.parseInt(this.txtA??oNacimientoUsuario.getText());

            Usuario usuarioActualizado = new Usuario(cedula, nombre, email, anoNacimiento);
            usuarioActualizado.setId(id);

            Usuario actualizado = this.miControladorUsuario.actualizar(usuarioActualizado);

            this.txtIdUsuario.setText(actualizado.getId());
            this.txtCedulaUsuario.setText(actualizado.getCedula());
            this.txtNombreUsuario.setText(actualizado.getNombre());
            this.txtEmailUsuario.setText(actualizado.getEmail());
            this.txtA??oNacimientoUsuario.setText("" + actualizado.getAnoNacimiento());
            JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente");
            actualizarTablaUsuarios();
            LimpiarCamposUsuario();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el usuario " + e);
        }
    }//GEN-LAST:event_btnEditarUsuarioActionPerformed

    private void btnEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUsuarioActionPerformed
        // TODO add your handling code here:
        try {
            String id = this.txtIdUsuario.getText();
            this.miControladorUsuario.eliminar(id);

            JOptionPane.showMessageDialog(null, "Eliminaci??n exitosa");
            actualizarTablaUsuarios();
            LimpiarCamposUsuario();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Eliminaci??n sin ??xito " + e);
        }
    }//GEN-LAST:event_btnEliminarUsuarioActionPerformed

    private void boxFuncionBoletaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxFuncionBoletaItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!this.boxFuncionBoleta.getSelectedItem().equals("elige uno...")) {
                this.indexFuciones = this.boxFuncionBoleta.getSelectedIndex();
                boxTipoSilla();

            }
        }
    }//GEN-LAST:event_boxFuncionBoletaItemStateChanged

    private void btnBuscarBoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarBoletoActionPerformed
        // TODO add your handling code here:
        String cedulaUsuario = this.txtCedulaUsuarioBoleto.getText();
        LinkedList<Boleto> boletos = this.miControladorBoleto.listar();
        Boleto encontrado = new Boleto();
        for (Boleto boletoActual : boletos) {
            Usuario actual = boletoActual.getMiUsuario();
            if (cedulaUsuario.equals(actual.getCedula())) {
                encontrado = boletoActual;
                System.out.println("boletoencontrado **********" + encontrado);
            } else {
                System.out.println("boleto no encontrado");
            }

        }
        if (encontrado != null) {
            this.txtIdBoleto.setText(encontrado.getId());
            this.txtValorBoleto.setText("" + encontrado.getValor());
            this.txtCedulaUsuarioBoleto.setText(encontrado.getMiUsuario().getCedula());
            this.boxTipoBoleto.removeAllItems();
            this.boxTipoBoleto.addItem(encontrado.getTipo());
            this.boxFuncionBoleta.removeAllItems();
            String funcionEncontrado = datosFuncion(encontrado.getMiFuncion());
            this.boxFuncionBoleta.addItem(funcionEncontrado);
            this.boxSillaBoleto.removeAllItems();
            String numeroSilla = "" + encontrado.getMiSilla().getNumero();
            String sillaEncontrado = encontrado.getMiSilla().getLetra() + numeroSilla;
            this.boxSillaBoleto.addItem(sillaEncontrado);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontr?? el boleto");
        }

    }//GEN-LAST:event_btnBuscarBoletoActionPerformed

    private void btnEditarBoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarBoletoActionPerformed
        // TODO add your handling code here:
        try {
            String id = this.txtIdBoleto.getText();
            double valor = Double.parseDouble(this.txtValorBoleto.getText());
            String tipo = "" + this.boxTipoBoleto.getSelectedItem();
            Usuario usuarioaux = this.miControladorUsuario.buscarPorCedula(txtCedulaUsuarioBoleto.getText());
//            Silla sillaaux=""+this.boxSillaBoleto.getSelectedItem();
            if (usuarioaux == null) {
                JOptionPane.showMessageDialog(this, "Usuario no encotrado");
            } else {
                System.out.println("encontrado con exito");
                Silla miSilla = this.misSillas.get(this.boxSillaBoleto.getSelectedIndex());

                Boleto boletoActualizado = new Boleto(valor, tipo);
                Funcion funcionAux = this.misFunciones.get(this.indexFuciones);

                boletoActualizado.setId(id);

                boletoActualizado.setMiSilla(miSilla);
                boletoActualizado.setMiFuncion(funcionAux);
                boletoActualizado.setMiUsuario(usuarioaux);
                //            miSilla.setMiBoleto(NuevoBoleto);
                //            usuarioaux.getMisBoletos().add(NuevoBoleto);
                Boleto BoletoActualizado = this.miControladorBoleto.actualizar(boletoActualizado);
                boletoActualizado = this.miControladorBoleto.actualizarRelaciones(boletoActualizado, miSilla.getId(), "silla");
                System.out.println("000000,,,,,--------antes de " + boletoActualizado.getValor());
                boletoActualizado = this.miControladorBoleto.actualizarRelaciones(boletoActualizado, funcionAux.getId(), "funcion");
                boletoActualizado = this.miControladorBoleto.actualizarRelaciones(boletoActualizado, usuarioaux.getId(), "usuario");
                System.out.println("000000,,,,,--------" + boletoActualizado.getValor());

                System.out.println("hasta aqui nice");
                this.txtIdBoleto.setText(BoletoActualizado.getId());
                this.txtValorBoleto.setText("" + BoletoActualizado.getValor());
                this.txtCedulaUsuario.setText(BoletoActualizado.getMiUsuario().getCedula());
                this.boxTipoBoleto.removeAllItems();
                this.boxTipoBoleto.addItem(BoletoActualizado.getTipo());
                this.boxFuncionBoleta.removeAllItems();
                String funcionEncontrado = datosFuncion(BoletoActualizado.getMiFuncion());
                this.boxFuncionBoleta.addItem(funcionEncontrado);
                this.boxSillaBoleto.removeAllItems();
                String numeroSilla = "" + BoletoActualizado.getMiSilla().getNumero();
                String sillaEncontrado = BoletoActualizado.getMiSilla().getLetra() + numeroSilla;
                this.boxSillaBoleto.addItem(sillaEncontrado);
                JOptionPane.showMessageDialog(this, "Boleto actualizado");
                actualizarTablaBoletos();
                LimpiarCamposBoleto();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el boleto " + e);
        }
    }//GEN-LAST:event_btnEditarBoletoActionPerformed

    private void btnEliminarBoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarBoletoActionPerformed
        // TODO add your handling code here:
        try {
            String id = this.txtIdBoleto.getText();
            this.miControladorBoleto.eliminar(id);

            JOptionPane.showMessageDialog(null, "Eliminaci??n exitosa");
            actualizarTablaBoletos();
            LimpiarCamposBoleto();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Eliminaci??n sin ??xito " + e);
        }
    }//GEN-LAST:event_btnEliminarBoletoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IntefarzCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IntefarzCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IntefarzCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IntefarzCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IntefarzCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxFuncionBoleta;
    private javax.swing.JComboBox<String> boxSillaBoleto;
    private javax.swing.JComboBox<String> boxTipoBoleto;
    private javax.swing.JButton btnBuscarBoleto;
    private javax.swing.JButton btnBuscarPelicula;
    private javax.swing.JButton btnBuscarUsuario;
    private javax.swing.JButton btnCrearBoleto;
    private javax.swing.JButton btnCrearPelicula;
    private javax.swing.JButton btnCrearUsuario;
    private javax.swing.JButton btnEditarBoleto;
    private javax.swing.JButton btnEditarPelicula;
    private javax.swing.JButton btnEditarUsuario;
    private javax.swing.JButton btnEliminarBoleto;
    private javax.swing.JButton btnEliminarPelicula;
    private javax.swing.JButton btnEliminarUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable tbBoletos;
    private javax.swing.JTable tbPelicula;
    private javax.swing.JTable tbUsuarios;
    private javax.swing.JTextField txtA??oNacimientoUsuario;
    private javax.swing.JTextField txtA??oPelicula;
    private javax.swing.JTextField txtCedulaUsuario;
    private javax.swing.JTextField txtCedulaUsuarioBoleto;
    private javax.swing.JTextField txtEmailUsuario;
    private javax.swing.JTextField txtIdBoleto;
    private javax.swing.JTextField txtIdPelicula;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtNombrePelicula;
    private javax.swing.JTextField txtNombreUsuario;
    private javax.swing.JTextField txtTipoPelicula;
    private javax.swing.JTextField txtValorBoleto;
    // End of variables declaration//GEN-END:variables
}
