package ar.com.unpaz.taller.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.com.unpaz.modelo.Finales;
import ar.com.unpaz.taller.db.FinalesDAO;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModificarFinalDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private Finales nota_final = new Finales();
	JSpinner spinner = new JSpinner();

	public Finales getNota_final() {
		return nota_final;
	}

	public void setNota_final(Finales nota_final) {
		this.nota_final = nota_final;
	}

	public boolean isGrabo() {
		return grabo;
	}

	public void setGrabo(boolean grabo) {
		this.grabo = grabo;
	}

	private boolean grabo = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModificarFinalDialog dialog = new ModificarFinalDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ModificarFinalDialog() {
		
		
		initialize();

	}
	
    public ModificarFinalDialog(Finales finales) {
		
		initialize();
		this.setNota_final(finales);
		this.spinner.setValue((float)finales.getNota());
	
	}

	void initialize() {

		setModal(true);
		setTitle("Modificar Nota");
		setBounds(100, 100, 386, 174);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		SpinnerNumberModel m_numberSpinnerModel;
		Float current = new Float(5.50);
		Float min = new Float(0.00);
		Float max = new Float(10.00);
		Float step = new Float(0.25);
		m_numberSpinnerModel = new SpinnerNumberModel(current, min, max, step);
		spinner = new JSpinner(m_numberSpinnerModel);

		spinner.setBounds(168, 56, 63, 20);
		contentPanel.add(spinner);

		JLabel lblLabeLMod = new JLabel("Modificar:");
		lblLabeLMod.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLabeLMod.setBounds(90, 57, 141, 14);
		contentPanel.add(lblLabeLMod);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Grabar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// ejecuta el metodo para modificar la nota del alumno y la final seleccionada
						modificarNota(e);
					}

				});
				okButton.setIcon(new ImageIcon(
						ModificarFinalDialog.class.getResource("/ar/com/unpaz/taller/vista/images/Guardar.png")));
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setIcon(new ImageIcon(
						ModificarFinalDialog.class.getResource("/ar/com/unpaz/taller/vista/images/Cancelar.png")));
				cancelButton.setActionCommand("Cancel");
			}

			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup().addGap(31)
							.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addGap(53)));
			gl_buttonPane.setVerticalGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup().addGap(5).addGroup(gl_buttonPane
							.createParallelGroup(Alignment.LEADING).addComponent(okButton).addComponent(cancelButton))
							.addContainerGap()));
			buttonPane.setLayout(gl_buttonPane);

		}

	}

	private void modificarNota(ActionEvent e) {
		
		this.getNota_final().setNota((float) spinner.getValue());
		
		if((new FinalesDAO()).ActualizarNotaFinal(this.getNota_final())==1) {
			this.setGrabo(true);
			this.dispose();
		} else
			JOptionPane.showMessageDialog(this, "Error");
		
		
	}

}
