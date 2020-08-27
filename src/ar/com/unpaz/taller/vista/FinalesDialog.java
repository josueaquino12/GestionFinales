package ar.com.unpaz.taller.vista;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;


import ar.com.unpaz.modelo.Finales;
import ar.com.unpaz.modelo.FinalesTableModel;
import ar.com.unpaz.taller.db.AlumnoDAO;
import ar.com.unpaz.taller.db.FinalesDAO;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class FinalesDialog extends JDialog {

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
			FinalesDialog dialog = new FinalesDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FinalesDialog() {
		setModal(true);
		setTitle("Finales");
		setBounds(100, 100, 796, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblFinales = new JLabel("Finales:");
		lblFinales.setBounds(10, 11, 46, 14);
		contentPanel.add(lblFinales);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				modificar(e);
			}
		});
		btnModificar.setIcon(
				new ImageIcon(FinalesDialog.class.getResource("/ar/com/unpaz/taller/vista/images/Modificar.png")));
		btnModificar.setBounds(74, 221, 133, 32);
		contentPanel.add(btnModificar);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregar(arg0);

			}
		});
		btnAgregar.setIcon(
				new ImageIcon(FinalesDialog.class.getResource("/ar/com/unpaz/taller/vista/images/Agregar.png")));
		btnAgregar.setBounds(309, 221, 133, 32);
		contentPanel.add(btnAgregar);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				borrar(arg0);
			}
		});
		btnBorrar.setIcon(
				new ImageIcon(FinalesDialog.class.getResource("/ar/com/unpaz/taller/vista/images/Borrar.png")));
		btnBorrar.setBounds(547, 221, 133, 32);
		contentPanel.add(btnBorrar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 33, 760, 182);
		contentPanel.add(scrollPane);

		table = new JTable();

		table.setModel(new FinalesTableModel(new FinalesDAO().getFinales()));
		scrollPane.setViewportView(table);

//		table.setAutoCreateRowSorter(true);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(null);
		}

		setLocationRelativeTo(null);
	}

	//metodo agregar final (se abre una ventana)
	private void agregar(java.awt.event.ActionEvent evt) {

		AgregaFinalDialog altaFinal = new AgregaFinalDialog();

		altaFinal.setLocationRelativeTo(this);
		altaFinal.setVisible(true);

		if (altaFinal.isGrabo()) {
			FinalesTableModel mtm = null;

			mtm = new FinalesTableModel((new FinalesDAO()).getFinales());

			table.setModel(mtm);
		}

	}
	
	//metodo modificar final (se abre una ventana)
	private void modificar(java.awt.event.ActionEvent evt){

		ModificarFinalDialog modificarFinal;
		
		 int row = table.getSelectedRow();
	        if (row != -1){
	        	
	        	Finales f = new Finales();
	        
	        	f.setId((int) table.getModel().getValueAt(row, 0));
	        	f.setAlumno((int) table.getModel().getValueAt(row, 1));
	        	f.setNombre((String)table.getModel().getValueAt(row, 2).toString());
	        	f.setApellido((String)table.getModel().getValueAt(row, 3).toString());
	        	f.setMateria((int)table.getModel().getValueAt(row, 4));
	        	f.setDescripMateria((String)table.getModel().getValueAt(row, 5));
	        	f.setNota((float) table.getModel().getValueAt(row, 6));
	        	f.setFechafinal((Date)table.getModel().getValueAt(row, 7));
	        	f.setPromedio((java.math.BigDecimal)table.getModel().getValueAt(row, 8));
	        	
	        	
	        	modificarFinal = new ModificarFinalDialog(f);
	        	modificarFinal.setLocationRelativeTo(this);
	    		modificarFinal.setVisible(true);

	    		if (modificarFinal.isGrabo()) {
	    			
	    			FinalesTableModel mtm = null;
	    					mtm = new FinalesTableModel(new FinalesDAO().getFinales());
	    			table.setModel(mtm);
	    			
	    		}
	        	
	        }
	        
	        else {
    			JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
    		}
	}
	
	//metodo borrar final (se abre una ventana)
	private void borrar(java.awt.event.ActionEvent evt) {
	       //selecciona fila                     
	        int row = table.getSelectedRow();
	        if (row != -1){
	        	
	        	Finales f = new Finales();
	        	f.setId((int) table.getModel().getValueAt(row, 0));
	        	f.setAlumno((int) table.getModel().getValueAt(row, 1));
	        	f.setMateria((int) table.getModel().getValueAt(row, 4));
	        	f.setNota((float) table.getModel().getValueAt(row, 6));
	        	f.setFechafinal((Date) table.getModel().getValueAt(row, 7));
	        	
	       
	            int dialogButton = JOptionPane.YES_NO_OPTION;
	            int dialogResult = JOptionPane.showConfirmDialog (null, "¿Confirma la eliminación del final? ","Borrar Final",dialogButton);
	       
	            
	            if(dialogResult == JOptionPane.YES_OPTION){
	                if ((new FinalesDAO()).BorrarFinales(f)==1) {
	                
	                	FinalesTableModel mtm= null;
	           		 
	           		 		mtm = new FinalesTableModel((new FinalesDAO()).getFinales());
	        			
	           		 	
	                	table.setModel(mtm);
	                }
	                	
	            }            
	        }
	        else {
	            JOptionPane.showMessageDialog(null, "Debe seleccionar una Materia");
	            
	            
	            
	            
	        }
		
		
	}
	

}
