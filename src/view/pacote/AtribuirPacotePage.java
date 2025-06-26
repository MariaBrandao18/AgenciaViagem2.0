package view.pacote;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexao_db.ClienteDAO;
import net.miginfocom.swing.MigLayout;
import view.cliente.ClientePage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AtribuirPacotePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomePacote;
	private JTextField documento;

	private boolean validarCampos(String cliente, String pacote) {
		if(cliente.isEmpty() || pacote.isEmpty()) {
			JOptionPane.showMessageDialog(this, 
		            "Todos os campos (Documento, Nome do pacote) são obrigatórios.", 
		            "Campos obrigatórios", 
		            JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	public AtribuirPacotePage(PacotePage TelaAnterior) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][][grow][][]", "[][][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Atribuir pacote à um cliente");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, "cell 3 0 2 1");
		
		JLabel lblNewLabel_1 = new JLabel("Documento do cliente: ");
		contentPane.add(lblNewLabel_1, "cell 2 3,alignx trailing");
		
		documento = new JTextField();
		contentPane.add(documento, "cell 4 3 2 1,growx");
		documento.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nome do pacote: ");
		contentPane.add(lblNewLabel_1_1, "cell 2 4,alignx trailing");
		
		nomePacote = new JTextField();
		contentPane.add(nomePacote, "cell 4 4 2 1,growx");
		nomePacote.setColumns(10);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaAnterior.setVisible(true);
			}
		});
		contentPane.add(btnVoltar, "cell 4 9");
		
		JButton btnAtribuir = new JButton("Atribuir");
		btnAtribuir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String cliente = documento.getText();
					String pacote = nomePacote.getText();
					if (!validarCampos(cliente, pacote)) {
		                return;
		            }
				
					ClienteDAO.incluirPacote(cliente, pacote);
					JOptionPane.showMessageDialog(null, "Pacote atribuido com sucesso!");
			        dispose();
			        if (TelaAnterior != null) {
			                TelaAnterior.setVisible(true);
			        }
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, 
			                "Erro ao atribuir pacote: " + e1.getMessage(), 
			                "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		contentPane.add(btnAtribuir, "cell 5 9");

	}

}
