package view.pacote;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.PacoteViagem;
import conexao_db.PacoteDAO;
import net.miginfocom.swing.MigLayout;
import view.cliente.ClientePage;

public class ListarPacotePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarPacotePage frame = new ListarPacotePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ListarPacotePage() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][grow]", "[][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Lista de pacotes");
		contentPane.add(lblNewLabel, "cell 2 2,alignx left");
		
		JComboBox<String> comboBox = new JComboBox<>();
		contentPane.add(comboBox, "cell 3 2,growx");
		
		List<PacoteViagem> pacotes = PacoteDAO.listarTodos();

		if (pacotes.isEmpty()) {
		    JOptionPane.showMessageDialog(this,
		        "Nenhum pacote cadastrado.",
		        "Aviso",
		        JOptionPane.WARNING_MESSAGE);
		    comboBox.setEnabled(false); //desativa a comboBox caso não haja clientes
		} else {
		    for (PacoteViagem pacote : pacotes) {
		        comboBox.addItem(pacote.getNome());
		    }
		}
		
		JButton voltarBtn = new JButton("Voltar");
		contentPane.add(voltarBtn, "cell 2 5");
		
		voltarBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ClientePage();
				setVisible(false);
			}
			
		});
		
		JButton sairBtn = new JButton("Sair");
		contentPane.add(sairBtn, "cell 3 5");
		
		sairBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		
		setVisible(true);

	}

}
