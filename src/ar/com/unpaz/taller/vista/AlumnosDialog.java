package ar.com.unpaz.taller.vista;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import ar.com.unpaz.modelo.Alumno;
import ar.com.unpaz.modelo.AlumnoTableModel;

import ar.com.unpaz.taller.db.AlumnoDAO;

import ar.com.unpaz.taller.listener.TableAlumnoHeaderMouseListener;

import javax.swing.JScrollPane;

public class AlumnosDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AlumnosDialog dialog = new AlumnosDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	public AlumnosDialog() {
		setModal(true);

		setTitle("Alumnos");
		setBounds(100, 100, 505, 270);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblAlumnos = new JLabel("Alumnos: ");
			lblAlumnos.setBounds(10, 11, 67, 14);
			contentPanel.add(lblAlumnos);
		}
		{
			JButton modificarButton = new JButton("Modificar");
			modificarButton.setIcon(
					new ImageIcon(AlumnosDialog.class.getResource("/ar/com/unpaz/taller/vista/images/Modificar.png")));
			modificarButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// *Metodo Modificar Alumnos*//
					modificarAlumnos(arg0);
				}
			});
			modificarButton.setBounds(105, 202, 116, 29);
			contentPanel.add(modificarButton);
			modificarButton.setActionCommand("Modificar");
			getRootPane().setDefaultButton(modificarButton);
		}

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(
				new ImageIcon(AlumnosDialog.class.getResource("/ar/com/unpaz/taller/vista/images/Agregar.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// *Metodo Agregar Alumnos*//
				agregar(e);
			}
		});
		btnAgregar.setBounds(231, 203, 116, 28);
		contentPanel.add(btnAgregar);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//*Metodo Borrar Alumnos*//
				borrar(arg0);
			}
		});
		btnBorrar.setIcon(
				new ImageIcon(AlumnosDialog.class.getResource("/ar/com/unpaz/taller/vista/images/Borrar.png")));
		btnBorrar.setBounds(357, 203, 116, 28);
		contentPanel.add(btnBorrar);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(20, 34, 453, 157);
			contentPanel.add(scrollPane);

			table = new JTable();
			scrollPane.setViewportView(table);
			table.setModel(new AlumnoTableModel((new AlumnoDAO()).getALUMNO()));

			table.getTableHeader().setReorderingAllowed(false);
			// Ordenar tabla
//			table.setAutoCreateRowSorter(true);

		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(null);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBounds(444, 5, 65, 23);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JTableHeader header = table.getTableHeader();
		header.addMouseListener(new TableAlumnoHeaderMouseListener(table));

		setLocationRelativeTo(null);
		setResizable(false);

	}

	private void agregar(ActionEvent e) {
		AlumnoDialog altaAlumno = new AlumnoDialog();
		altaAlumno.setOperacion("A");
		altaAlumno.setLocationRelativeTo(this);
		altaAlumno.setVisible(true);
		if (altaAlumno.isGrabo()) {
			AlumnoTableModel mtm = new AlumnoTableModel((new AlumnoDAO()).getALUMNO());

			table.setModel(mtm);
		}

	}

	private void modificarAlumnos(ActionEvent e) {

		AlumnoDialog alumnoDialog;

		int row = table.getSelectedRow();
		if (row != -1) {
			Alumno alumno = new Alumno();

			alumno.setDNI((int) table.getModel().getValueAt(row, 0));
			alumno.setNombre((String) table.getModel().getValueAt(row, 1).toString());
			alumno.setApellido((String) table.getModel().getValueAt(row, 2).toString());
			alumno.setEmail((String) table.getModel().getValueAt(row, 3));

			alumnoDialog = new AlumnoDialog(alumno);
			alumnoDialog.setOperacion("M");
			alumnoDialog.setLocationRelativeTo(this);
			alumnoDialog.setVisible(true);
			if (alumnoDialog.isGrabo()) {

				AlumnoTableModel mtm = new AlumnoTableModel((new AlumnoDAO()).getALUMNO());

				table.setModel(mtm);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Debe seleccionar una Alumno");
		}

	}

	private void borrar(java.awt.event.ActionEvent evt) {
		int row = table.getSelectedRow();
		if (row != -1) {
			Alumno alumno = new Alumno();
			// COLUMNAS={"id", "Materia"}

			alumno.setDNI((int) table.getModel().getValueAt(row, 0));
			alumno.setNombre((String) table.getModel().getValueAt(row, 1).toString());
			alumno.setApellido((String) table.getModel().getValueAt(row, 2).toString());
			alumno.setEmail((String) table.getModel().getValueAt(row, 3));

			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(null,
					"¿Confirma la eliminación del alumno \"" + alumno.getNombre() + "\"? ", "Borrar Alumno",
					dialogButton);
			if (dialogResult == JOptionPane.YES_OPTION) {
				if ((new AlumnoDAO()).BorrarAlumno(alumno) == 1) {

					AlumnoTableModel mtm = null;

					mtm = new AlumnoTableModel((new AlumnoDAO()).getALUMNO());

					table.setModel(mtm);
				}

			}
		} else {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un Alumno");
		}
	}

}
