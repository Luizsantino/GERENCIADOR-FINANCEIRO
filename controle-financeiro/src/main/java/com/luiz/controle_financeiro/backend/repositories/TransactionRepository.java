package com.luiz.controle_financeiro.backend.repositories;
import com.luiz.controle_financeiro.backend.models.Transacao;
import java.util.List;



public interface TransactionRepository {
	
	
	void addTransacao (Transacao transacao);
	void updateTransacao(Transacao transacao);
	void deleteTransacao(int id);
	Transacao getTransacaoById(int id);
	List<Transacao> getALLTransacoes();
	
	

}
