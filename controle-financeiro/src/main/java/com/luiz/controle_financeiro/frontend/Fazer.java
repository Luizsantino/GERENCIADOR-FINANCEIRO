package com.luiz.controle_financeiro.frontend;

import com.luiz.controle_financeiro.backend.models.Categoria;
import com.luiz.controle_financeiro.backend.models.Transacao;
import com.luiz.controle_financeiro.backend.repositories.TransactionRepositoryImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Fazer {

    public static void main(String[] Args) {

        Scanner scanner = new Scanner(System.in);
        TransactionRepositoryImpl transactionRepositoryImpl = new TransactionRepositoryImpl();
        
        while (true) {
            System.out.println("Transação segura!:");
            System.out.println("Aperte 1 para adicionar uma transação");
            System.out.println("Aperte 2 para sair");

            int opcao = 0;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consome a nova linha deixada por nextInt()
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine(); // Limpa a entrada inválida
                continue;
            }

            switch(opcao) {
                case 1: 
                    Transacao transacao = new Transacao();

                    System.out.println("Digite a descrição da transação:");
                    String descricao = scanner.nextLine();

                    double valor = 0.0;
                    boolean valorValido = false;
                    while (!valorValido) {
                        System.out.println("Digite o valor da transação:");
                        try {
                            valor = scanner.nextDouble();
                            valorValido = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Por favor, insira um valor numérico.");
                            scanner.nextLine(); // Limpa a entrada inválida
                        }
                    }
                    
                    transacao.setDescricao(descricao);
                    transacao.setValor(valor);
                    transacao.setData(new java.util.Date());

                    // Escolher a categoria
                    Categoria categoria = new Categoria();
                    System.out.println("Escolha a categoria da transação:");
                    System.out.println("1. Alimentação");
                    System.out.println("2. Lazer");
                    System.out.println("3. Transporte");
                    System.out.println("4. Saúde");

                    int categoriaOpcao = 0;
                    try {
                        categoriaOpcao = scanner.nextInt();
                        scanner.nextLine(); // Consome a nova linha deixada por nextInt()
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Por favor, insira um número.");
                        scanner.nextLine(); // Limpa a entrada inválida
                        continue;
                    }

                    // Define a categoria com base na escolha
                    switch (categoriaOpcao) {
                        case 1:
                            categoria.setId(1); // ID para Alimentação
                            break;
                        case 2:
                            categoria.setId(2); // ID para Lazer
                            break;
                        case 3:
                            categoria.setId(3); // ID para Transporte
                            break;
                        case 4:
                            categoria.setId(4); // ID para Saúde
                            break;
                        default:
                            System.out.println("Categoria inválida. Definindo categoria padrão (Alimentação).");
                            categoria.setId(1); // Categoria padrão
                            break;
                    }

                    transacao.setCategoria(categoria);

                    transactionRepositoryImpl.addTransacao(transacao);
                    System.out.println("Transação adicionada com sucesso!");
                    break;

                case 2:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}
