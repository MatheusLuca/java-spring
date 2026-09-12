package br.com.fiap.apostila13.view;

import br.com.fiap.apostila13.dao.CategoriaDao;
import br.com.fiap.apostila13.model.Categoria;

import java.util.Scanner;

public class ExemploCategoria {

    static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        try {
            System.out.println("Digite o nome da categoria");
            String nome = leitor.next() + leitor.nextLine();

            CategoriaDao dao = new CategoriaDao();
            Categoria categoria = new Categoria(nome);
            dao.cadastrar(categoria);
            System.out.println("Categoria cadastrada! " + categoria);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }//main
}//class
