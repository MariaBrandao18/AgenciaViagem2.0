package view.cliente;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Cliente;
import conexao_db.ClienteDAO;
import net.miginfocom.swing.MigLayout;
import view.pacote.ServicoPage;

public class ListarClientePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarClientePage frame = new ListarClientePage();
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
	public ListarClientePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][grow]", "[][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Lista de clientes");
		contentPane.add(lblNewLabel, "cell 2 2,alignx left");
		
		JComboBox<String> comboBox = new JComboBox<>();
		contentPane.add(comboBox, "cell 3 2,growx");
		
		List<Cliente> clientes = ClienteDAO.listarClientes();

		if (clientes.isEmpty()) {
		    JOptionPane.showMessageDialog(this,
		        "Nenhum cliente cadastrado.",
		        "Aviso",
		        JOptionPane.WARNING_MESSAGE);
		    comboBox.setEnabled(false); //desativa a comboBox caso não haja clientes
		} else {
		    for (Cliente cliente : clientes) {
		        comboBox.addItem(cliente.getNome());
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
