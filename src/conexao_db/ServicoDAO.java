package conexao_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import classes.ServicoAdicional;

public class ServicoDAO {
	 public static void inserirServico(ServicoAdicional servico) {
	        String sql = "INSERT INTO servicos (nome_servico, descricao, preco) VALUES (?, ?, ?)";
	        try (Connection conn = Conexao.conectar();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, servico.getNome());
	            stmt.setString(2, servico.getDescricao());
	            stmt.setDouble(3, servico.getPreco());

	            stmt.executeUpdate();
	            System.out.println("Servico cadastrado com sucesso!");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public static void excluirServiço(String nome) {
	        String sql = "DELETE FROM servicos WHERE nome_servico = ?";
	        try (Connection conn = Conexao.conectar();
	             PreparedStatement stmt = conn.prepareStatement(sql)) 
	        {
	            stmt.setString(1, nome.trim());
	            stmt.executeUpdate();
	            System.out.println("Servico removido com sucesso!");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	 public static List<ServicoAdicional> listarServicos() {
		 List<ServicoAdicional> servicos = new ArrayList<>();
		 String sql = "SELECT * FROM servicos";
		 Connection conn = null;
    
    try {
    	conn = Conexao.conectar();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String nome = rs.getString("nome_servico");
            String descricao = rs.getString("descricao");
            Double preco = rs.getDouble("preco");
            
            ServicoAdicional s;
                s = new ServicoAdicional(nome, preco, descricao);
                s.setServicoId(rs.getLong("servico_id"));
                servicos.add(s);

        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return servicos;
}
	 
	 public static void incluirServico(String pacote, String servico) throws SQLException {
		    String sqlSelectServico = "SELECT servico_id, preco FROM servicos WHERE nome_servico = ?";
		    String sqlSelectPacote = "SELECT pacote_id, preco FROM pacotes WHERE nome = ?";
		    String sqlSelectCliente = "SELECT cliente_id FROM clientes_pacotes WHERE pacote_id = ?";
		    String sqlInsertPedido = "INSERT INTO pedidos_servicos (servico_id, pacote_id, cliente_id, data_pedido) VALUES (?, ?, ?, CURDATE())";
		    String sqlUpdatePrecoPacote = "UPDATE pacotes SET preco = ? WHERE pacote_id = ?";

		    Connection conn = null;

		    try {
		        conn = Conexao.conectar();
		        conn.setAutoCommit(false);

		        int servicoId = -1;
		        int pacoteId = -1;
		        Integer clienteId = null;
		        double precoServico = 0.0;
		        double precoAtualPacote = 0.0;

		        try (PreparedStatement stmtServico = conn.prepareStatement(sqlSelectServico)) {
		            stmtServico.setString(1, servico);
		            try (ResultSet rs = stmtServico.executeQuery()) {
		                if (rs.next()) {
		                    servicoId = rs.getInt("servico_id");
		                    precoServico = rs.getDouble("preco");
		                } else {
		                    throw new SQLException("Serviço não encontrado com o nome informado.");
		                }
		            }
		        }

		        try (PreparedStatement stmtPacote = conn.prepareStatement(sqlSelectPacote)) {
		            stmtPacote.setString(1, pacote);
		            try (ResultSet rs = stmtPacote.executeQuery()) {
		                if (rs.next()) {
		                    pacoteId = rs.getInt("pacote_id");
		                    precoAtualPacote = rs.getDouble("preco");

		                } else {
		                    throw new SQLException("Pacote não encontrado com o nome informado.");
		                }
		            }
		        }

		        try (PreparedStatement stmtCliente = conn.prepareStatement(sqlSelectCliente)) {
		            stmtCliente.setInt(1, pacoteId);
		            try (ResultSet rs = stmtCliente.executeQuery()) {
		                if (rs.next()) {
		                    clienteId = rs.getInt("cliente_id");
		                } else {
		                    clienteId = null;
		                }
		            }
		        }

		        try (PreparedStatement stmtInsert = conn.prepareStatement(sqlInsertPedido)) {
		            stmtInsert.setInt(1, servicoId);
		            stmtInsert.setInt(2, pacoteId);
		            if (clienteId != null) {
		                stmtInsert.setInt(3, clienteId);
		            } else {
		                stmtInsert.setNull(3, java.sql.Types.INTEGER);
		            }
		            stmtInsert.executeUpdate();
		        }

		        conn.commit();
		        System.out.println("Serviço associado com sucesso ao pacote" + (clienteId != null ? " e ao cliente." : "."));
		        
		        double novoPreco = precoAtualPacote + precoServico;
		        try (PreparedStatement stmt = conn.prepareStatement(sqlUpdatePrecoPacote)) {
		            stmt.setDouble(1, novoPreco);
		            stmt.setInt(2, pacoteId);
		            stmt.executeUpdate();
		        }

		        conn.commit();
		        System.out.println("Serviço atribuído e valor do pacote atualizado para: R$ " + novoPreco);

		    } catch (SQLException e) {
		        if (conn != null) {
		            try {
		                conn.rollback();
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
		        }
		        e.printStackTrace();
		        throw e;
		    } finally {
		        if (conn != null) {
		            Conexao.desconectar(conn);
		        }
		    }
		}
	  
	  public static ServicoAdicional buscarServico(String nomeServico) {
		    String sql = "SELECT * FROM servicos WHERE nome_servico = ?";
		    Connection conn = null;

		    try {
		        conn = Conexao.conectar();
		        PreparedStatement stmt = conn.prepareStatement(sql);
		        stmt.setString(1, nomeServico.trim());

		        ResultSet rs = stmt.executeQuery();

		        if (rs.next()) {
		            String nome = rs.getString("nome_servico");
		            String descricao = rs.getString("descricao");
		            double preco = rs.getDouble("preco");

		            ServicoAdicional servico = new ServicoAdicional(nome, preco, descricao);
		            servico.setServicoId(rs.getLong("servico_id"));
		            return servico;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        if (conn != null) {
		            Conexao.desconectar(conn);
		        }
		    }

		    return null;
		}

}