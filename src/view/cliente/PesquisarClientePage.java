package view.cliente;


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

import classes.Cliente;
import conexao_db.ClienteDAO;
import net.miginfocom.swing.MigLayout;

public class PesquisarClientePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField documento;
	@SuppressWarnings("unused")
	private ClientePage TelaAnterior;


	public PesquisarClientePage(ClientePage TelaAnterior) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][][][][][][grow][][][][]", "[][][]"));
		
		JLabel lblNewLabel = new JLabel("Pesquisar cliente");
		JLabel lblNewLabel_1 = new JLabel("Documento:");
		documento = new JTextField();
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String documento1 = documento.getText();
					Cliente cliente = ClienteDAO.buscarCliente(documento1);
					if (cliente == null) {
					    JOptionPane.showMessageDialog(null, 
					        "Cliente não encontrado.", 
					        "Resultado", 
					        JOptionPane.INFORMATION_MESSAGE);
					} else {
					    StringBuilder info = new StringBuilder();
					    info.append("Nome: ").append(cliente.getNome()).append("\n");
					    info.append("Telefone: ").append(cliente.getTelefone()).append("\n");
					    info.append("Email: ").append(cliente.getEmail()).append("\n");
					    info.append("Tipo: ").append(cliente.getTipo_cliente()).append("\n");

					    if (cliente.getTipo_cliente().equalsIgnoreCase("nacional")) {
					        info.append("CPF: ").append(cliente.getCpf()).append("\n");
					    } else if (cliente.getTipo_cliente().equalsIgnoreCase("estrangeiro")) {
					        info.append("Passaporte: ").append(cliente.getPassaporte()).append("\n");
					    }

					    JOptionPane.showMessageDialog(null, 
					        info.toString(), 
					        "Dados do Cliente", 
					        JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, 
		                "Erro ao pesquisar cliente: " + ex.getMessage(), 
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
		contentPane.add(btnPesquisar, "cell 7 7");
		
		setVisible(true);
	}

}
