package br.com.fiap.apostila13.view;

import br.com.fiap.apostila13.dao.ProdutoDao;
import br.com.fiap.apostila13.model.Produto;

import java.util.Scanner;

public class ExemploCadastro {
    //cadastrar um produto
    static void main(String[] args) {
        //Ler os dados do produto
        Scanner leitor = new Scanner(System.in);
        try {
            System.out.println("Digite o nome");
            String nome = leitor.next() + leitor.nextLine();

            System.out.println("Digite a descrição");
            String descricao = leitor.next() + leitor.nextLine();

            System.out.println("Digite o valor");
            double valor = leitor.nextDouble();

            System.out.println("Digite se tem estoque (true/false)");
            boolean estoque = leitor.nextBoolean();

            //Instanciar um Produto com os dados
            Produto produto = new Produto(nome, descricao, valor, estoque);

            //Instanciar um ProdutoDao
            ProdutoDao dao = new ProdutoDao();

            //Chamar o método de cadastro
            dao.cadastrar(produto);
            System.out.println("Produto cadastrado!");
            System.out.println(produto);

        } catch(Exception e){
            System.err.println("Erro: " + e.getMessage());
        }
    }//main
}//class
