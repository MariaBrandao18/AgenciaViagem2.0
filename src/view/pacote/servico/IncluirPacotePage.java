package view.pacote.servico;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import conexao_db.ServicoDAO;
import net.miginfocom.swing.MigLayout;
import view.pacote.ServicoPage;

public class IncluirPacotePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField servico;
	private JTextField pacote;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IncluirPacotePage frame = new IncluirPacotePage();
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
	public IncluirPacotePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][grow]", "[][][][][]"));
		
		JLabel label = new JLabel("Serviço:");
		contentPane.add(label, "cell 3 2,alignx left");
		
		servico = new JTextField();
		contentPane.add(servico, "cell 4 2,alignx left");
		servico.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Pacote: ");
		contentPane.add(lblNewLabel, "cell 3 3,alignx left");
		
		pacote = new JTextField();
		contentPane.add(pacote, "cell 4 3,alignx left");
		pacote.setColumns(10);
		
		JButton btnNewButton = new JButton("Confirmar");
		contentPane.add(btnNewButton, "cell 3 4");
		
		btnNewButton.addActionListener(e -> {
		    String nomeServico = servico.getText().trim();
		    String nomePacote = pacote.getText().trim();

		    if (nomeServico.isEmpty() || nomePacote.isEmpty()) {
		        JOptionPane.showMessageDialog(this,
		            "Os campos 'Serviço' e 'Pacote' devem ser preenchidos.",
		            "Campos obrigatórios",
		            JOptionPane.WARNING_MESSAGE);
		        return;
		    }

		    try {
		        ServicoDAO.incluirServico(nomePacote, nomeServico);
		        JOptionPane.showMessageDialog(this,
		            "Serviço vinculado ao pacote com sucesso!",
		            "Sucesso",
		            JOptionPane.INFORMATION_MESSAGE);
		    } catch (SQLException ex) {
		        JOptionPane.showMessageDialog(this,
		            "Erro ao vincular serviço ao pacote: " + ex.getMessage(),
		            "Erro",
		            JOptionPane.ERROR_MESSAGE);
		    }
		});
		
		JButton cancelarBtn = new JButton("Cancelar");
		contentPane.add(cancelarBtn, "cell 4 4");
		
		cancelarBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ServicoPage();
				setVisible(false);
			}
			
		});
		
		setVisible(true);
	}

}
