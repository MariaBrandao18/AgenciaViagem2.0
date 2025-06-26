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

import classes.PacoteViagem;
import conexao_db.PacoteDAO;
import net.miginfocom.swing.MigLayout;

public class PesquisarPacotePage extends JFrame {

		private static final long serialVersionUID = 1L;
		private JPanel contentPane;
		private JTextField nomePacote;
		@SuppressWarnings("unused")
		private PacotePage TelaAnterior;


		public PesquisarPacotePage(PacotePage TelaAnterior) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(new MigLayout("", "[][][][][][][][][][][grow][][][][]", "[][][]"));
			
			JLabel lblNewLabel = new JLabel("Pesquisar pacote");
			JLabel lblNewLabel_1 = new JLabel("Nome:");
			nomePacote = new JTextField();
			JButton btnPesquisar = new JButton("Pesquisar");
			btnPesquisar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
					String pacote1 = nomePacote.getText();
					if (pacote1.trim().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Documento obrigatório!");
					}
					
					PacoteViagem pacote = PacoteDAO.buscarPacote(pacote1);

		            if (pacote == null) {
		                JOptionPane.showMessageDialog(null,
		                        "Pacote não encontrado.",
		                        "Resultado",
		                        JOptionPane.INFORMATION_MESSAGE);
		                return;
		            }

		            StringBuilder info = new StringBuilder();
		            info.append("Nome: ").append(pacote.getNome()).append("\n");
		            info.append("Destino: ").append(pacote.getDestino()).append("\n");
		            info.append("Duração: ").append(pacote.getDuracao()).append(" dias\n");
		            info.append("Preço: R$ ").append(String.format("%.2f", pacote.getPreco())).append("\n");
		            info.append("Tipo: ").append(pacote.getTipo() != null ? pacote.getTipo() : "Não informado");

		            JOptionPane.showMessageDialog(null,
		                    info.toString(),
		                    "Dados do Pacote",
		                    JOptionPane.INFORMATION_MESSAGE);
		            
					} catch (Exception ex) {
			            ex.printStackTrace();
			            JOptionPane.showMessageDialog(null, 
			                "Erro ao pesquisar pacote: " + ex.getMessage(), 
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
			contentPane.add(btnPesquisar, "cell 7 7");
			
			setVisible(true);
		}

}