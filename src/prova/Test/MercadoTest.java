package prova.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import prova.Compra;
import prova.Mercado;
import prova.Produto;


public class MercadoTest {
	Produto p;
	Compra c;
	
    @Test
    public void LerProdutotest(){
        Mercado mercado = new Mercado();
        mercado.lerProduto();
        assertEquals(50, mercado.produto.size()); 
    }

    @Test
    public void LerCompratest(){
        Mercado mercado = new Mercado();
        mercado.lerCompra();
        assertEquals(10, mercado.compra.size()); 
    }
    @Test
    public void testExecutarCompra()  {
        Mercado mercado = new Mercado();

        Produto produto = new Produto("Maçã", 20, 2.50);

        Compra compra = new Compra(produto.getNome(), 10); 

        mercado.compra.add(compra);

    }
    @Test
    public void testCalcularTotalCompra() {
        Produto produto = new Produto("Maçã", 2, 2.50); 

        Compra compra = new Compra(produto.getNome(), 5); 

        Mercado mercado = new Mercado();

        mercado.compra.add(compra);
       // double totalCompra = mercado.calcularTotalCompra();

       // assertEquals(5 * produto.getValor(), totalCompra); 
    }

}