package br.com.fiap.apostila13.view;

import br.com.fiap.apostila13.dao.ProdutoDao;
import br.com.fiap.apostila13.model.Produto;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ExemploPesquisaPorValor {

    static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        try {
            System.out.println("Digite o valor minimo do produto");
            double valor = leitor.nextDouble();

            ProdutoDao dao = new ProdutoDao();
            List<Produto> lista = dao.buscarPorPrecoMaior(valor);
            System.out.println(lista);
        } catch (InputMismatchException e) {
            System.err.println("Digite um número seu usuario...");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
