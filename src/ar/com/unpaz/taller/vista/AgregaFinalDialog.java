package ar.com.unpaz.taller.vista;

import ar.com.unpaz.modelo.Alumno;
import ar.com.unpaz.modelo.Finales;
import ar.com.unpaz.modelo.Materia;
import ar.com.unpaz.taller.db.AlumnoDAO;
import ar.com.unpaz.taller.db.FinalesDAO;
import ar.com.unpaz.taller.db.MateriaDAO;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AgregaFinalDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton butGrabar;
	private JButton cancelButton;
	private boolean grabo = false;
	Finales finales = new Finales();
	JComboBox comboBoxAlumno = new JComboBox();
	JComboBox comboBoxMaterias = new JComboBox();
	JSpinner spinner;
	JDateChooser dateChooser = new JDateChooser();
	AlumnoDAO alumnoDAO = new AlumnoDAO();
	MateriaDAO materiaDAO = new MateriaDAO();

	public boolean isGrabo() {
		return grabo;
	}

	public void setGrabo(boolean grabo) {
		this.grabo = grabo;
	}

	public Finales getFinales() {
		return finales;
	}

	public void setFinales(Finales finales) {
		this.finales = finales;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	String operacion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AgregaFinalDialog dialog = new AgregaFinalDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AgregaFinalDialog() {

		initialize();

		alumnoDAO.listarAlumnoComboBox(comboBoxAlumno);

	}

	public AgregaFinalDialog(Finales Dialog) {

		this.setFinales(finales);

	}

	public void initialize() {
		setModal(true);
		setBounds(100, 100, 402, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		comboBoxAlumno.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

				// automaticamente cuando se selecciona un alumno en el combo de materias
				// apareceran aquellas que no aprobo el alumno y las que no realizo
				Alumno a = (Alumno) comboBoxAlumno.getSelectedItem();
				int dni = a.getDNI();
				materiaDAO.listarMaterias(comboBoxMaterias, dni);

			}
		});

		comboBoxAlumno.setBounds(122, 34, 232, 20);
		contentPanel.add(comboBoxAlumno);

		comboBoxMaterias.setBounds(122, 71, 232, 20);
		contentPanel.add(comboBoxMaterias);

		dateChooser.setBounds(122, 118, 232, 20);
		contentPanel.add(dateChooser);

		/* Converir a decimales - Spinn */

		SpinnerNumberModel m_numberSpinnerModel;
		Float current = new Float(5.50);
		Float min = new Float(0.00);
		Float max = new Float(10.00);
		Float step = new Float(0.25);
		m_numberSpinnerModel = new SpinnerNumberModel(current, min, max, step);
		spinner = new JSpinner(m_numberSpinnerModel);

		spinner.setBounds(122, 150, 46, 20);
		contentPanel.add(spinner);

		/*-------------------------*/

		JLabel lblAlumno = new JLabel("Alumno:");
		lblAlumno.setBounds(45, 37, 72, 14);
		contentPanel.add(lblAlumno);

		JLabel lblNewLabel = new JLabel("Materia:");
		lblNewLabel.setBounds(45, 74, 88, 14);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Fecha: ");
		lblNewLabel_1.setBounds(45, 118, 72, 14);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNota = new JLabel("Nota:");
		lblNota.setBounds(44, 153, 46, 14);
		contentPanel.add(lblNota);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				butGrabar = new JButton("Grabar");
				butGrabar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						grabarFinales(arg0);
					}
				});
				butGrabar.setIcon(new ImageIcon(
						AgregaFinalDialog.class.getResource("/ar/com/unpaz/taller/vista/images/Guardar.png")));
				butGrabar.setActionCommand("Grabar");
				getRootPane().setDefaultButton(butGrabar);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setIcon(new ImageIcon(
						AgregaFinalDialog.class.getResource("/ar/com/unpaz/taller/vista/images/Cancelar.png")));
				cancelButton.setActionCommand("Cancel");
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup().addGap(25)
							.addComponent(butGrabar, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
							.addGap(57)));
			gl_buttonPane.setVerticalGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup().addGap(5).addGroup(gl_buttonPane
							.createParallelGroup(Alignment.BASELINE).addComponent(butGrabar).addComponent(cancelButton))
							.addContainerGap()));
			buttonPane.setLayout(gl_buttonPane);

//			AlumnoDAO aDao = new AlumnoDAO();
//			ArrayList<Alumno> alumnos = aDao.getALUMNO();
//			DefaultComboBoxModel modelo = new DefaultComboBoxModel(alumnos.toArray());
//			comboBoxAlumno.setModel(modelo);
//
//			MateriaDAO mDao = new MateriaDAO();
//			ArrayList<Materia> materia = mDao.getMaterias();
//			DefaultComboBoxModel modelo2 = new DefaultComboBoxModel(materia.toArray());
//			comboBoxMaterias.setModel(modelo2);

		}

	}

	// toma los valores seleccionados y automaticamente cuando se ejecuta el metodo
	// se guarda en la BD
	private void grabarFinales(java.awt.event.ActionEvent evt) {

		Alumno a = (Alumno) comboBoxAlumno.getSelectedItem();
		Materia m = (Materia) comboBoxMaterias.getSelectedItem();

		int dniAlumno = a.getDNI();
		int idMateria = m.getIdMateria();

		// Pasos para poder obtener el formato de la fecha elegida a SQL

		if (dateChooser.getDate() != null) {
			java.util.Date date = dateChooser.getDate();
			long d = date.getTime();
			java.sql.Date fecha = new java.sql.Date(d);

			Finales finales = new Finales();

			finales.setAlumno(dniAlumno);
			finales.setMateria(idMateria);
			finales.setNota((float) spinner.getValue());
			finales.setFechafinal(fecha);

			if ((new FinalesDAO()).insertarFinales(finales) == 1) {

				this.setGrabo(true);
				this.dispose();
			}
		}

		else {

			JOptionPane.showMessageDialog(this, "Ingrese la fecha");

		}

	}

}
