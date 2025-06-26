package view.pacote;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import conexao_db.PacoteDAO;
import net.miginfocom.swing.MigLayout;

public class RemoverPacotePage extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomePacote;
	@SuppressWarnings("unused")
	private PacotePage TelaAnterior;


	public RemoverPacotePage(PacotePage TelaAnterior) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][][][][][][grow][][][][]", "[][][]"));
		
		JLabel lblNewLabel = new JLabel("Remover pacote");
		JLabel lblNewLabel_1 = new JLabel("Nome do pacote:");
		nomePacote = new JTextField();
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String pacote1 = nomePacote.getText();
				if (pacote1.trim().isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Nome do pacote obrigatório!");
				} 
				
				if (PacoteDAO.buscarPacote(pacote1) == null) {
				    JOptionPane.showMessageDialog(null, "Pacote não encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
				
				if (PacoteDAO.pacotePossuiCliente(pacote1)) {
				    JOptionPane.showMessageDialog(null, 
				        "Este pacote está associado a um cliente e não pode ser removido.", 
				        "Remoção bloqueada", 
				        JOptionPane.ERROR_MESSAGE);
				    return;
				} else {
					int confirmacao = JOptionPane.showConfirmDialog(null, 
			        		"Tem certeza que deseja remover este pacote?", 
			                "Confirmar Remoção", 
			                JOptionPane.YES_NO_OPTION, 
			                JOptionPane.WARNING_MESSAGE);
			        
			        if (confirmacao == JOptionPane.YES_OPTION) {
			            PacoteDAO.deletarPacote(pacote1);
			            JOptionPane.showMessageDialog(null, "pacote removido.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			        } else {
			            JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
			        }
				
					}
				} catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, 
		                "Erro ao remover pacote: " + ex.getMessage(), 
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
		contentPane.add(nomePacote, "cell 6 3 2 1,growx");
		nomePacote.setColumns(10);
		contentPane.add(btnVoltar, "cell 5 7");
		contentPane.add(btnRemover, "cell 7 7");

	}

}