package view.pacote.servico;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import conexao_db.ServicoDAO;
import view.pacote.ServicoPage;
public class RemoverPage extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomeRemover;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoverPage frame = new RemoverPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public RemoverPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel_1 = new JLabel("Remover serviço");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 4;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridx = 4;
		gbc_lblNewLabel.gridy = 3;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		nomeRemover = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.gridx = 5;
		gbc_textField.gridy = 3;
		contentPane.add(nomeRemover, gbc_textField);
		nomeRemover.setColumns(10);
		
		JButton confirmarBtn = new JButton("Confirmar");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 6;
		contentPane.add(confirmarBtn, gbc_btnNewButton);
		
		confirmarBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String nome1 = nomeRemover.getText();
					if (nome1.trim().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Nome obrigatório!");
					} 
					
					if (ServicoDAO.buscarServico(nome1) == null) {
					    JOptionPane.showMessageDialog(null, "Serviço não encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
					} else {
					int confirmacao = JOptionPane.showConfirmDialog(null, 
				        		"Tem certeza que deseja remover este serviço?", 
				                "Confirmar Remoção", 
				                JOptionPane.YES_NO_OPTION, 
				                JOptionPane.WARNING_MESSAGE);
				        
				    if (confirmacao == JOptionPane.YES_OPTION) {
				            ServicoDAO.excluirServiço(nome1);
				            JOptionPane.showMessageDialog(null, "Serviço removido.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				    } else {
				            JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
				    }
					
					} }
					catch (Exception ex) {
			            ex.printStackTrace();
			            JOptionPane.showMessageDialog(null, 
			                "Erro ao remover serviço: " + ex.getMessage(), 
			                "Erro", JOptionPane.ERROR_MESSAGE);
			        }
				
			}
			
		});
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_1.gridx = 5;
		gbc_btnNewButton_1.gridy = 6;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ServicoPage();
				setVisible(false);
			}
			
		});
		
		setVisible(true);
	}
}
