package com.luiz.controle_financeiro.backend.services;

import java.util.List;

import com.luiz.controle_financeiro.backend.models.Transacao;
import com.luiz.controle_financeiro.backend.repositories.TransactionRepository;
import com.luiz.controle_financeiro.backend.repositories.TransactionRepositoryImpl;

public class TransactionService {
    private TransactionRepository repository = new TransactionRepositoryImpl();

    public void addTransacao (Transacao transacao) {
        repository.addTransacao(transacao);
    }

    public void updateTransacao(Transacao transacao) {
        repository.updateTransacao(transacao);
    }

    public void deleteTransacao(int id) {
        repository.deleteTransacao(id);
    }

    public Transacao getTransacaoById(int id) {
        return repository.getTransacaoById(id);
    }

    public List<Transacao> getAllTransacoes() {
        return repository.getALLTransacoes();
    }
}
