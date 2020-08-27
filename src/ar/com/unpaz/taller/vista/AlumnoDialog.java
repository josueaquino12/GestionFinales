package ar.com.unpaz.taller.vista;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.com.unpaz.modelo.Alumno;

import ar.com.unpaz.taller.db.AlumnoDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AlumnoDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldEmail;
	private boolean grabo = false;
	Alumno alumno = new Alumno();
	String operacion;

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public boolean isGrabo() {
		return grabo;
	}

	public void setGrabo(boolean grabo) {
		this.grabo = grabo;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AlumnoDialog dialog = new AlumnoDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AlumnoDialog() {

		initialize();

	}

	public AlumnoDialog(Alumno alumno) {
		initialize();
		this.setAlumno(alumno);
		this.textFieldNombre.setText(alumno.getNombre());
		this.textFieldNombre.selectAll();
		this.textFieldApellido.setText(alumno.getApellido());
		this.textFieldApellido.selectAll();
		this.textFieldEmail.setText(alumno.getEmail());
		this.textFieldEmail.selectAll();

	}

	private void initialize() {

		setResizable(false);
		setModal(true);
		setTitle("ABM");
		setBounds(100, 100, 344, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 29, 59, 14);
		contentPanel.add(lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(66, 26, 233, 20);
		contentPanel.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(10, 69, 59, 14);
		contentPanel.add(lblApellido);

		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(66, 66, 233, 20);
		contentPanel.add(textFieldApellido);
		textFieldApellido.setColumns(10);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(66, 111, 233, 20);
		contentPanel.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		JLabel lblNewLabelEmail = new JLabel("Email:");
		lblNewLabelEmail.setBounds(10, 114, 67, 14);
		contentPanel.add(lblNewLabelEmail);

		JLabel labeloblig = new JLabel("*");
		labeloblig.setForeground(Color.RED);
		labeloblig.setBounds(303, 29, 25, 14);
		contentPanel.add(labeloblig);

		JLabel labeloblig2 = new JLabel("*");
		labeloblig2.setForeground(Color.RED);
		labeloblig2.setBounds(303, 69, 25, 14);
		contentPanel.add(labeloblig2);

		JLabel lblCamposObligatorios = new JLabel("* Campos obligatorios para llenar");
		lblCamposObligatorios.setForeground(Color.RED);
		lblCamposObligatorios.setBounds(51, 142, 220, 14);
		contentPanel.add(lblCamposObligatorios);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JButton grabarButton = new JButton("Grabar");
				grabarButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						altamodificargrabar(arg0);

					}
				});
				grabarButton.setIcon(
						new ImageIcon(AlumnoDialog.class.getResource("/ar/com/unpaz/taller/vista/images/Guardar.png")));
				grabarButton.setActionCommand("OK");
				buttonPane.add(grabarButton);
				getRootPane().setDefaultButton(grabarButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setIcon(
						new ImageIcon(AlumnoDialog.class.getResource("/ar/com/unpaz/taller/vista/images/Borrar.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

	}

	// Metodo para ejecutar el alta o modificar el alumno
	private void altamodificargrabar(java.awt.event.ActionEvent evt) {
		String nombre = textFieldNombre.getText();
		String apellido = textFieldApellido.getText();
		String email = textFieldEmail.getText();
		nombre = nombre.replaceAll(" ", "");
		apellido = apellido.replaceAll(" ", "");
		email = email.replaceAll(" ", "");
		if (nombre.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe llenar los campos obligatorios");
			this.textFieldNombre.requestFocus();
			this.textFieldNombre.selectAll();
		} else

		if (apellido.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe llenar los campos obligatorios");
			this.textFieldApellido.requestFocus();
			this.textFieldApellido.selectAll();
		}

		else {
		// si la operacion que desea realizar es modificar
		if (this.getOperacion().equals("M")) {

			this.getAlumno().setNombre(textFieldNombre.getText().toString());
			this.getAlumno().setApellido(textFieldApellido.getText().toString());
			this.getAlumno().setEmail(textFieldEmail.getText().toString());
			if ((new AlumnoDAO()).ActualizarAlumno(this.getAlumno()) == 1) {
				this.setGrabo(true);
				this.dispose();
			} else
				JOptionPane.showMessageDialog(this, "Error");
		}

		// si la operacion que se desea realizar es dar de alta
		if (this.getOperacion().equals("A")) {

			Alumno a = new Alumno();
			a.setNombre(textFieldNombre.getText().toString());
			a.setApellido(textFieldApellido.getText().toString());

			if (email.isEmpty())

				a.setEmail(null);

			else
				a.setEmail(textFieldEmail.getText().toString());

			if ((new AlumnoDAO()).insertarAlumno(a) == 1) {
				this.setGrabo(true);
				this.dispose();
			} else
				JOptionPane.showMessageDialog(this, "Error");
		}
		
	   }
	}
}
