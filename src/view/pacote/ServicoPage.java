package view.pacote;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.pacote.servico.IncluirPacotePage;
import view.pacote.servico.InserirPage;
import view.pacote.servico.ListarPage;
import view.pacote.servico.RemoverPage;
public class ServicoPage extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServicoPage frame = new ServicoPage();
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
	public ServicoPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Serviço");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Inserir serviço");
		mnNewMenu.add(mntmNewMenuItem);
		
		mntmNewMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new InserirPage();
				setVisible(false);
			}
			
		});
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Listar serviço");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ListarPage();
				setVisible(false);
			}
			
		});
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Remover serviço");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RemoverPage();
				setVisible(false);
			}
			
		});
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Incluir serviço à pacote");
		mnNewMenu.add(mntmNewMenuItem_3);
		
		mntmNewMenuItem_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new IncluirPacotePage();
				setVisible(false);
				
			}
			
		});
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton voltarBtn = new JButton("Voltar");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 6;
		contentPane.add(voltarBtn, gbc_btnNewButton);
		
		voltarBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PacotePage();
				setVisible(false);
			}
			
		});
		
		JButton sairBtn = new JButton("Sair");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridx = 5;
		gbc_btnNewButton_1.gridy = 6;
		contentPane.add(sairBtn, gbc_btnNewButton_1);
		
		sairBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
			
		});
		
		setVisible(true);
	}
}
