package view.cliente;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexao_db.ClienteDAO;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RemoverClientePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField documento;
	@SuppressWarnings("unused")
	private ClientePage TelaAnterior;


	public RemoverClientePage(ClientePage TelaAnterior) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][][][][][][grow][][][][]", "[][][]"));
		
		JLabel lblNewLabel = new JLabel("Remover cliente");
		JLabel lblNewLabel_1 = new JLabel("Documento:");
		documento = new JTextField();
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String documento1 = documento.getText();
				if (documento1.trim().isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Documento obrigatório!");
				} 
				
				if (ClienteDAO.buscarCliente(documento1) == null) {
				    JOptionPane.showMessageDialog(null, "Cliente não encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
				} else {
				int confirmacao = JOptionPane.showConfirmDialog(null, 
			        		"Tem certeza que deseja remover este cliente?", 
			                "Confirmar Remoção", 
			                JOptionPane.YES_NO_OPTION, 
			                JOptionPane.WARNING_MESSAGE);
			        
			    if (confirmacao == JOptionPane.YES_OPTION) {
			            ClienteDAO.deletarCliente(documento1);
			            JOptionPane.showMessageDialog(null, "Cliente removido.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			    } else {
			            JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
			    }
				
				} }
				catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, 
		                "Erro ao remover cliente: " + ex.getMessage(), 
		                "Erro", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (TelaAnterior != null) {
					TelaAnterior.setVisible(true);
				}
				dispose();
			}
		});


		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, "cell 6 1");
		contentPane.add(lblNewLabel_1, "cell 5 3,alignx trailing");
		contentPane.add(documento, "cell 6 3 2 1,growx");
		documento.setColumns(10);
		contentPane.add(btnVoltar, "cell 5 7");
		contentPane.add(btnRemover, "cell 7 7");

	}

}
