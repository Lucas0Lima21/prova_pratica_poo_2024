package prova;

public class Compra {
	
	private Produto produto;
	private String nome;
	private int quantCompradaa;
	

	public Compra(String nome, int quantCompradaa) {
	    this.nome = nome;
	    this.quantCompradaa = quantCompradaa;
	}

	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantCompradaa() {
		return quantCompradaa;
	}
	public void setQuantCompradaa(int quantCompradaa) {
		this.quantCompradaa = quantCompradaa;
	}

}
