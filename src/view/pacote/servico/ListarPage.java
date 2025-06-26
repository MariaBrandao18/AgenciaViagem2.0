package view.pacote.servico;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

import conexao_db.ServicoDAO;
import classes.ServicoAdicional;
import view.pacote.ServicoPage;

public class ListarPage extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarPage frame = new ListarPage();
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
	public ListarPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Lista de serviços:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 4;
		gbc_lblNewLabel.gridy = 3;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JComboBox<String> comboBox = new JComboBox<>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 5;
		gbc_comboBox.gridy = 3;
		contentPane.add(comboBox, gbc_comboBox);
		
		List<ServicoAdicional> servicos = ServicoDAO.listarServicos();
		
		if (servicos.isEmpty()) {
		    JOptionPane.showMessageDialog(this,
		        "Nenhum serviço cadastrado.",
		        "Aviso",
		        JOptionPane.WARNING_MESSAGE);
		    comboBox.setEnabled(false); //desativa a comboBox caso não haja clientes
		} else {
		    for (ServicoAdicional servico : servicos) {
		        comboBox.addItem(servico.getNome());
		    }
		}
		
		JButton voltarBtn = new JButton("Voltar");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 5;
		gbc_btnNewButton.gridy = 7;
		contentPane.add(voltarBtn, gbc_btnNewButton);
		
		voltarBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ServicoPage();
				setVisible(false);
			}
			
		});
		
		setVisible(true);
	}
}
