package view.pacote;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import classes.PacoteAventura;
import classes.PacoteCultural;
import classes.PacoteLuxuoso;
import classes.PacoteViagem;
import conexao_db.PacoteDAO;

public class AdicionarPacotePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nome;
	private JTextField destino;
	private JTextField duracao;
	private JTextField preco;

	private boolean validarCampos(String nome, String destino, String duracao, String preco) {
	    if (nome.isEmpty() || destino.isEmpty() || duracao.isEmpty() || preco.isEmpty()) {
	        JOptionPane.showMessageDialog(this, 
	            "Todos os campos (Nome, E-Destino, Duração, Preço) são obrigatórios.", 
	            "Campos obrigatórios", 
	            JOptionPane.WARNING_MESSAGE);
	        return false;
	    }
	    return true;
	}
	
	public AdicionarPacotePage(PacotePage TelaAnterior) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][grow][][][]", "[][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Adicionar pacote");
		contentPane.add(lblNewLabel, "cell 4 0 3 1");
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		contentPane.add(lblNewLabel_1, "cell 3 1,alignx trailing");
		
		nome = new JTextField();
		contentPane.add(nome, "cell 4 1,growx");
		nome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Destino");
		contentPane.add(lblNewLabel_2, "cell 3 2,alignx trailing");
		
		destino = new JTextField();
		destino.setColumns(10);
		contentPane.add(destino, "cell 4 2,growx");
		
		JLabel lblNewLabel_3 = new JLabel("Duração(dias):");
		contentPane.add(lblNewLabel_3, "cell 3 3,alignx trailing");
		
		duracao = new JTextField();
		duracao.setColumns(10);
		contentPane.add(duracao, "cell 4 3,growx");
		
		JLabel lblNewLabel_4 = new JLabel("Preço:");
		contentPane.add(lblNewLabel_4, "cell 3 4,alignx trailing");
		
		preco = new JTextField();
		preco.setColumns(10);
		contentPane.add(preco, "cell 4 4,growx");
		
		JLabel lblNewLabel_5 = new JLabel("Tipo:");
		contentPane.add(lblNewLabel_5, "cell 3 5");
		
		JRadioButton rdbtnLuxo = new JRadioButton("Luxo");
		JRadioButton rdbtnCultural = new JRadioButton("Cultural");
		JRadioButton rdbtnAventura = new JRadioButton("Aventura");

		rdbtnLuxo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnLuxo.isSelected()) {
					rdbtnCultural.setSelected(false);
					rdbtnAventura.setSelected(false);
				}
			}
		});
		rdbtnCultural.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCultural.isSelected()) {
					rdbtnLuxo.setSelected(false);
					rdbtnAventura.setSelected(false);
				}
			}
		});
		rdbtnAventura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnAventura.isSelected()) {
					rdbtnLuxo.setSelected(false);
					rdbtnCultural.setSelected(false);
				}
			}
		});


		contentPane.add(rdbtnLuxo, "flowx,cell 4 6");
		contentPane.add(rdbtnCultural, "cell 4 6");
		contentPane.add(rdbtnAventura, "cell 4 6");
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaAnterior.setVisible(true);
			}
		});
		contentPane.add(btnNewButton, "flowx,cell 4 8");
		
		JButton btnNewButton_1 = new JButton("Adicionar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String nome1 = nome.getText();
				String destino1 = destino.getText();
				String duracao1 = duracao.getText();
				String preco1 = preco.getText();
				 if (!validarCampos(nome1, destino1, duracao1, preco1)) {
		                return;
		            }
				 PacoteViagem pacote = null;
				 if (rdbtnLuxo.isSelected()) {
					 pacote = new PacoteLuxuoso();
		                ((PacoteViagem) pacote).setTipo("luxo");
				 } else if (rdbtnCultural.isSelected()) {
					 pacote = new PacoteCultural();
		                ((PacoteViagem) pacote).setTipo("cultural");
				 } else if (rdbtnAventura.isSelected()) {
					 pacote = new PacoteAventura();
		                ((PacoteViagem) pacote).setTipo("aventura");
				 } else {
					   JOptionPane.showMessageDialog(null, "Selecione o tipo de pacote!");
		                return;
				 }
				 pacote.setNome(nome1);
				 pacote.setDestino(destino1);
				 pacote.setDuracao(Integer.parseInt(duracao1));
				 pacote.setPreco(Double.parseDouble(preco1));
				 
				 PacoteDAO.inserirPacote(pacote);
				 
				 JOptionPane.showMessageDialog(null, "Pacote adicionado com sucesso!");
		            dispose();
		            if (TelaAnterior != null) {
		                TelaAnterior.setVisible(true);
		            }
				 
				} catch(Exception ex) {
					ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, 
		                "Erro ao adicionar o pacote: " + ex.getMessage(), 
		                "Erro", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		contentPane.add(btnNewButton_1, "cell 4 8");

	}

}
