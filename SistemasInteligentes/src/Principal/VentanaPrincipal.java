package Principal;

import java.awt.EventQueue;
//import java.awt.Panel;
import javax.swing.JFrame;
//import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import CargaDeImagenes.*;
import Problema.Algoritmos;
import Problema.Estado;
import Problema.Nodo;
import Problema.Problema;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

//import Problema.Estado;
//import Problema.Nodo;
//import treesDSESIUCLM.*;
public class VentanaPrincipal {
	private JFrame frame;
	private PanelImagen panel;
	private PanelImagen panel2;
	private JButton btnCargarImagenPrincipal;
	private JButton btnCargarImagenDesordenada;
	private JButton btnOrdenar;
	private JTextField numFilas;
	private JTextField numColumnas;
	private JLabel lblFilas;
	private JLabel lblColumnas;
	private JButton btnSalir;

	private Puzle original;
	private Puzle desordenado;
	private File carpetaOriginal;
	private File carpetaDesordenado;
	private File carpetaMovimientos;
	private JLabel lblInformacion;
	private JLabel lblEstrategia;
	private JComboBox<String> cmbEstrategia;
	private JLabel lblLimiteProfundidad;
	private JLabel lblProfundidadMxima;
	private JTextField txtLimProfundidad;
	private JTextField txtProfMaxima;

	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public VentanaPrincipal() throws IOException {
		initialize();
	}

	private void initialize() throws IOException {

		carpetaOriginal = new File("original");
		carpetaDesordenado = new File("desordenado");
		carpetaMovimientos = new File("movimientos");

		if (!carpetaOriginal.isFile()) {
			carpetaOriginal.mkdir();

		}

		if (!carpetaDesordenado.isFile()) {
			carpetaDesordenado.mkdir();
		}

		if (!carpetaMovimientos.isFile()) {
			carpetaMovimientos.mkdir();
		}

		frame = new JFrame();
		frame.setResizable(false);
		frame.addWindowListener(new FrameWindowListener());
		frame.setBounds(100, 100, 947, 616);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new PanelImagen();
		panel.setBounds(12, 48, 448, 287);
		frame.getContentPane().add(panel);

		panel2 = new PanelImagen();
		panel2.setBounds(484, 48, 433, 287);
		frame.getContentPane().add(panel2);

		btnCargarImagenPrincipal = new JButton("Cargar Imagen principal");
		btnCargarImagenPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String file = "";
				JFileChooser dlg = new JFileChooser();
				int option = dlg.showOpenDialog(null);
				if (option == JFileChooser.APPROVE_OPTION) {
					file = dlg.getSelectedFile().getAbsolutePath();
					panel.setImagen(file);
					panel.setRuta(file);
				}
			}
		});

		btnCargarImagenPrincipal.setBounds(119, 13, 191, 25);
		frame.getContentPane().add(btnCargarImagenPrincipal);

		btnCargarImagenDesordenada = new JButton("Cargar imagen desordenada");
		btnCargarImagenDesordenada.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String file = "";
				JFileChooser dlg = new JFileChooser();
				int option = dlg.showOpenDialog(null);
				if (option == JFileChooser.APPROVE_OPTION) {
					file = dlg.getSelectedFile().getAbsolutePath();
					panel2.setImagen(file);
					panel2.setRuta(file);
				}
			}
		});
		btnCargarImagenDesordenada.setBounds(601, 13, 221, 25);
		frame.getContentPane().add(btnCargarImagenDesordenada);

		btnOrdenar = new JButton("Ordenar");

		btnOrdenar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				File imagen1 = new File(panel.getRuta());
				File imagen2 = new File(panel2.getRuta());
				FileWriter fichero = null;
				PrintWriter pw = null;
				Estado inicial;
				Estado objetivo;
				ArrayList<Nodo> soluciones;

				try {
					int filas = Integer.parseInt(numFilas.getText());
					int columnas = Integer.parseInt(numColumnas.getText());
					String estrategia = (String) cmbEstrategia.getSelectedItem();
					int limiteProfundidad = Integer.parseInt(txtLimProfundidad.getText());
					int profundidadMaxima = Integer.parseInt(txtProfMaxima.getText());
					;
					original = Operaciones.generarPuzle(imagen1, filas, columnas, "original");
					desordenado = Operaciones.generarPuzle(imagen2, filas, columnas, "desordenado");
					boolean iguales = Operaciones.esIgualInicial(original, desordenado);

					if (iguales) {
						lblInformacion.setText("Buscando solucion...");
						inicial = Operaciones.generarEstadoInicial(desordenado);
						objetivo = Operaciones.generarEstadoInicial(original);
						Problema p = new Problema(inicial, objetivo);
						
						soluciones = Algoritmos.Busqueda(p, estrategia, limiteProfundidad, profundidadMaxima);
						if (soluciones.size() > 0) {

							try {
								lblInformacion.setText("Solución encontrada! Número de movimientos: "
										+ String.valueOf(soluciones.size() - 1));
								fichero = new FileWriter("FicheroResultado.txt");
								pw = new PrintWriter(fichero);

								for (int i = soluciones.size() - 1; i >= 0; i--) {
									pw.println(soluciones.get(i).getAccion());
								}
							} catch (Exception e1) {
								e1.printStackTrace();

							} finally {
								try {
									if (null != fichero)
										fichero.close();
								} catch (Exception e2) {
									e2.printStackTrace();
								}
							}
							for (int i = soluciones.size() - 1; i >= 0; i--) {
								Puzle puzleSolucion = Operaciones.generarPuzle("original",
										soluciones.get(i).getEstado());
								puzleSolucion.unir("movimientos");

							}
						} else {
							lblInformacion.setText("Error. No se pudo encontrar solución");
						}
					} else {
						lblInformacion.setText("Las imágenes no coinciden o no se pueden dividir");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnOrdenar.setBounds(294, 486, 97, 25);
		frame.getContentPane().add(btnOrdenar);

		numFilas = new JTextField();
		numFilas.setText("4");
		numFilas.setBounds(185, 410, 35, 22);
		frame.getContentPane().add(numFilas);
		numFilas.setColumns(10);

		numColumnas = new JTextField();
		numColumnas.setText("4");
		numColumnas.setBounds(185, 439, 35, 22);
		frame.getContentPane().add(numColumnas);
		numColumnas.setColumns(10);

		lblFilas = new JLabel("Filas:");
		lblFilas.setBounds(138, 413, 35, 16);
		frame.getContentPane().add(lblFilas);

		lblColumnas = new JLabel("Columnas:");
		lblColumnas.setBounds(104, 442, 69, 16);
		frame.getContentPane().add(lblColumnas);

		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new BtnSalirActionListener());
		btnSalir.setBounds(471, 486, 97, 25);
		frame.getContentPane().add(btnSalir);

		lblInformacion = new JLabel("Carga las imágenes y pulsa en el botón \"ordenar\"");
		lblInformacion.setForeground(Color.RED);
		lblInformacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblInformacion.setBounds(185, 348, 516, 25);
		frame.getContentPane().add(lblInformacion);

		lblEstrategia = new JLabel("Selecciona estrategia");
		lblEstrategia.setBounds(652, 413, 142, 16);
		frame.getContentPane().add(lblEstrategia);

		cmbEstrategia = new JComboBox<String>();
		cmbEstrategia.setModel(new DefaultComboBoxModel<String>(new String[] { "A*", "ANCHURA", "PROFUNDIDAD", "PROFUNDIDAD",
				"PROFUNDIDADACOTADA", "PROFUNDIDADITERATIVA", "COSTOUNIFORME" }));
		cmbEstrategia.setSelectedIndex(0);
		cmbEstrategia.setBounds(624, 439, 191, 22);
		frame.getContentPane().add(cmbEstrategia);

		lblLimiteProfundidad = new JLabel("Limite profundidad:");
		lblLimiteProfundidad.setBounds(325, 413, 111, 16);
		frame.getContentPane().add(lblLimiteProfundidad);

		lblProfundidadMxima = new JLabel("Profundidad máxima:");
		lblProfundidadMxima.setBounds(308, 442, 128, 16);
		frame.getContentPane().add(lblProfundidadMxima);

		txtLimProfundidad = new JTextField();
		txtLimProfundidad.setText("1000");
		txtLimProfundidad.setBounds(448, 410, 89, 22);
		frame.getContentPane().add(txtLimProfundidad);
		txtLimProfundidad.setColumns(10);

		txtProfMaxima = new JTextField();
		txtProfMaxima.setText("30");
		txtProfMaxima.setBounds(448, 439, 89, 22);
		frame.getContentPane().add(txtProfMaxima);
		txtProfMaxima.setColumns(10);
	}

	private class BtnSalirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			File[] ficherosOriginal = carpetaOriginal.listFiles();
			File[] ficherosDesordenado = carpetaDesordenado.listFiles();

			for (int i = 0; i < ficherosOriginal.length; i++) {
				ficherosOriginal[i].setWritable(true);
				ficherosOriginal[i].delete();

			}

			for (int i = 0; i < ficherosDesordenado.length; i++) {
				ficherosDesordenado[i].setWritable(true);
				ficherosDesordenado[i].delete();
			}
			carpetaOriginal.delete();
			carpetaDesordenado.delete();
			System.exit(0);
		}

	}

	private class FrameWindowListener extends WindowAdapter {
		@Override
		public void windowClosed(WindowEvent e) {
			File[] ficherosOriginal = carpetaOriginal.listFiles();
			File[] ficherosDesordenado = carpetaDesordenado.listFiles();

			for (int i = 0; i < ficherosOriginal.length; i++) {
				ficherosOriginal[i].setWritable(true);
				ficherosOriginal[i].delete();

			}

			for (int i = 0; i < ficherosDesordenado.length; i++) {
				ficherosDesordenado[i].setWritable(true);
				ficherosDesordenado[i].delete();
			}
			carpetaOriginal.delete();
			carpetaDesordenado.delete();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			File[] ficherosOriginal = carpetaOriginal.listFiles();
			File[] ficherosDesordenado = carpetaDesordenado.listFiles();

			for (int i = 0; i < ficherosOriginal.length; i++) {
				ficherosOriginal[i].setWritable(true);
				ficherosOriginal[i].delete();

			}

			for (int i = 0; i < ficherosDesordenado.length; i++) {
				ficherosDesordenado[i].setWritable(true);
				ficherosDesordenado[i].delete();
			}
			carpetaOriginal.delete();
			carpetaDesordenado.delete();
		}
	}
}
