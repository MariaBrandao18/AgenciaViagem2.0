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
import classes.ServicoAdicional;
import view.pacote.PacotePage;
public class InserirPage extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nome;
	private JTextField preco;
	private JTextField descricao;
	private PacotePage TelaAnterior;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InserirPage frame = new InserirPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private boolean validarCampos(String nome, double preco, String descricao) {
	    if (nome.isEmpty() || preco <= 0.0 || descricao.isEmpty()) {
	        JOptionPane.showMessageDialog(this,
	            "Todos os campos (Nome, Preço, Descrição) são obrigatórios.",
	            "Campos obrigatórios",
	            JOptionPane.WARNING_MESSAGE);
	        return false;
	    }
	    return true;
	}
	/**
	 * Create the frame.
	 */
	public InserirPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridx = 5;
		gbc_lblNewLabel.gridy = 2;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		nome = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.gridx = 6;
		gbc_textField.gridy = 2;
		contentPane.add(nome, gbc_textField);
		nome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Preco:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 5;
		gbc_lblNewLabel_1.gridy = 3;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		preco = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.gridx = 6;
		gbc_textField_1.gridy = 3;
		contentPane.add(preco, gbc_textField_1);
		preco.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Descrição:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 5;
		gbc_lblNewLabel_2.gridy = 4;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		descricao = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.anchor = GridBagConstraints.WEST;
		gbc_textField_2.gridx = 6;
		gbc_textField_2.gridy = 4;
		contentPane.add(descricao, gbc_textField_2);
		descricao.setColumns(10);
		
		JButton btnNewButton = new JButton("Confirmar");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 5;
		gbc_btnNewButton.gridy = 5;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
		            String nome1 = nome.getText().trim();
		            double preco1 = Double.parseDouble(preco.getText().trim());
		            String descricao1 = descricao.getText().trim();
		            if (!validarCampos(nome1, preco1, descricao1)) {
		                return;
		            }
		            ServicoAdicional servico = new ServicoAdicional(nome1, preco1, descricao1);
		            ServicoDAO.inserirServico(servico);
		            JOptionPane.showMessageDialog(null, "Serviço adicionado com sucesso!");
		            dispose();
		            if (TelaAnterior != null) {
		                TelaAnterior.setVisible(true);
		                setVisible(false);
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null,
		                "Erro ao inserir novo servico: " + ex.getMessage(),
		                "Erro", JOptionPane.ERROR_MESSAGE);
		        }
				
			}
			
		});
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_1.gridx = 6;
		gbc_btnNewButton_1.gridy = 5;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PacotePage();
				setVisible(false);
			}
			
		});
		
		setVisible(true);
	}}
