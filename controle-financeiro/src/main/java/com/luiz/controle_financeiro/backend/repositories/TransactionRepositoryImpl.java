package com.luiz.controle_financeiro.backend.repositories;
import com.luiz.controle_financeiro.backend.DatabaseConfig;
import com.luiz.controle_financeiro.backend.models.Categoria;
import com.luiz.controle_financeiro.backend.models.Transacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepositoryImpl implements TransactionRepository {

	public void addTransacao(Transacao transacao) {

		String sql = "INSERT INTO transacoes (descricao, valor, data, categoria_id) VALUES (?, ?, ?, ?)";
		try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, transacao.getDescricao());
			stmt.setDouble(2, transacao.getValor());
			stmt.setDate(3, new java.sql.Date(transacao.getData().getTime()));
			stmt.setInt(4, transacao.getCategoria().getId());
			stmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	@Override
	public void updateTransacao(Transacao transacao) {

		String sql = "UPDATE transacoes SET descricao = ?, valor = ?, data = ?, categoria_id = ? WHERE id = ?";
		try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, transacao.getDescricao());
			stmt.setDouble(2, transacao.getValor());
			stmt.setDate(3, new java.sql.Date(transacao.getData().getTime()));
			stmt.setInt(4, transacao.getCategoria().getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public void deleteTransacao(int id) {

		String sql = "DELETE FROM transacoes WHERE id = ?";
		try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Transacao getTransacaoById(int id) {
	    String sql = "SELECT t.*, c.nome AS categoria_nome FROM transacoes t JOIN categorias c ON t.categoria_id = c.id WHERE t.id = ?";
	    try (Connection conn = DatabaseConfig.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            Transacao transacao = new Transacao();
	            transacao.setId(rs.getInt("id"));
	            transacao.setDescricao(rs.getString("descricao"));
	            transacao.setValor(rs.getDouble("valor"));
	            transacao.setData(rs.getDate("data"));
	            Categoria categoria = new Categoria();
	            categoria.setId(rs.getInt("categoria_id"));
	            categoria.setNome(rs.getString("categoria_nome"));
	            transacao.setCategoria(categoria);
	            return transacao;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	public List<Transacao> getALLTransacoes() {
	    String sql = "SELECT t.*, c.nome AS categoria_nome FROM transacoes t JOIN categorias c ON t.categoria_id = c.id";
	    List<Transacao> transacoes = new ArrayList<>();
	    try (Connection conn = DatabaseConfig.getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            Transacao transacao = new Transacao();
	            transacao.setId(rs.getInt("id"));
	            transacao.setDescricao(rs.getString("descricao"));
	            transacao.setValor(rs.getDouble("valor"));
	            transacao.setData(rs.getDate("data"));
	            Categoria categoria = new Categoria();
	            categoria.setId(rs.getInt("categoria_id"));
	            categoria.setNome(rs.getString("categoria_nome"));
	            transacao.setCategoria(categoria);
	            transacoes.add(transacao);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return transacoes;
	}

	}
