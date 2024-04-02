package prova;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Mercado {

    public ArrayList<Produto> produto;
    public ArrayList<Compra> compra;

    
    public Mercado() {
        produto = new ArrayList<>();
        compra = new ArrayList<>();

    }

    public void lerProduto() {
        try {
            FileReader fr = new FileReader("lista_produtos.txt");
            BufferedReader br = new BufferedReader(fr);
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                String nome = dados[0];
                int quantidade = Integer.parseInt(dados[1]);
                double valor = parseValor(dados[2]);
                Produto novoProduto = new Produto(nome, quantidade, valor);
                produto.add(novoProduto);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private double parseValor(String valorString) {
        try {
            valorString = valorString.replace("R$", "").trim();
            valorString = valorString.replace(",", ".");
            return Double.parseDouble(valorString);
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter valor: " + e.getMessage());
            return 0.0;
        }
    }

    public String lerCompra() {
        StringBuilder ret = new StringBuilder();
        try {
            FileReader fr = new FileReader("lista_compras.txt");
            BufferedReader br = new BufferedReader(fr);
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                String nome = dados[0];
                int quantidade = Integer.parseInt(dados[1]);
                Compra novaCompra = new Compra(nome, quantidade);
                compra.add(novaCompra); // Add the new purchase to the list of purchases
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret.toString();
    }
    public void executarCompra() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("compra.txt"));
            writer.write("Nome do Produto,Quantidade Comprada,Valor Unitário,Subtotal\n");

            for (Compra compra : compra) {
                Produto produto = compra.getProduto();
                 int quantidade = compra.getQuantCompradaa();
                if (produto.getQuantidade() >= quantidade) {
                    double valorUnitario = produto.getValor();
                    double subtotal = quantidade * valorUnitario;
                    
                    produto.setQuantidade(produto.getQuantidade() - quantidade);

                    writer.write(produto.getNome() + "," + quantidade + "," + valorUnitario + "," + subtotal + "\n");
                } else {
                    System.out.println("Produto '" + produto.getNome() + "' com estoque insuficiente. Compra não realizada.");
                }
            }

            System.out.println("Compra realizada com sucesso! Arquivo 'compra.txt' gerado.");
        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo 'compra.txt': " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println("Erro ao fechar o BufferedWriter: " + e.getMessage());
                }
            }
        }
    }
    public double calcularTotalCompra() {
        double total = 0.0;
        for (Compra compra : compra) {
            Produto produto = compra.getProduto();
            int quantidade = compra.getQuantCompradaa();
            double valorUnitario = produto.getValor();
            total += quantidade * valorUnitario;
        }
        return total;
    }

    public void exibirTotalCompra() {
        double totalCompra = calcularTotalCompra();
        System.out.println("Total da compra: R$" + totalCompra);
    }

}

