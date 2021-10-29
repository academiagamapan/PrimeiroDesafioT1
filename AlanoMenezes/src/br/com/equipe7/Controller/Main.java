package br.com.equipe7.Controller;

import br.com.equipe7.Modelos.Cliente;
import br.com.equipe7.Modelos.Compra;
import br.com.equipe7.Modelos.Produto;
import br.com.equipe7.Utilittarios.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Main {

     static Cliente cliente = new Cliente();
     static TreeSet<Produto> estoque = Utils.getProdutos();
     static ArrayList<Compra> carrinho = new ArrayList<>();

     static Scanner sc = new Scanner(System.in);
     static String esp = "";

    public static void main(String[] args) throws InterruptedException {

        cliente.setCompras(new Compra());
        menuPrincipal();

    }

    private static void menuPrincipal() throws InterruptedException {

        int opcao = 0;
        do {
            System.out.printf("%10s*************************************************************\n",esp);
            System.out.printf("%10s*----------------!! SUPERMERCADO MANGUA�A !!----------------*\n",esp);
            System.out.printf("%10s*************************************************************\n",esp);
            System.out.printf("%10s*%10s1.%5sMostrar Estoque%26s*\n",esp,esp,esp,esp);
            System.out.printf("%10s*%10s2.%5sComprar%34s*\n",esp,esp,esp,esp);
            System.out.printf("%10s*%10s3.%5sCarrinho%33s*\n",esp,esp,esp,esp);
            System.out.printf("%10s*************************************************************\n",esp);
            System.out.printf("%10s Digite uma op��o 1-3, Digite 0 para sair: ",esp);
            opcao = sc.nextInt();

            switch (opcao){
                case 0: //Sair da applica��o
                    Utils.limparTela();
                    System.out.printf("%10s Volte Sempre!!",esp);
                    Thread.sleep(2000);
                    System.exit(0);
                    break;
                case 1: //Mostra o estoque na Tela
                    Utils.limparTela();
                    mostrarEstoque();
                    Utils.limparTela();
                    break;
                case 2: // Inicia o processo de compra
                    Utils.limparTela();
                    comprar();
                    Utils.limparTela();
                    break;
                case 3: //Finaliza o processo de comprar
                    Utils.limparTela();
                    if(cliente.getCompras().isCompraFinalizada() || cliente.getCompras().getProdutos().isEmpty()){
                        System.out.printf("%10sCarrinho vazio!!\n",esp);
                        Thread.sleep(2000);
                        Utils.limparTela();
                        menuPrincipal();
                    }
                    MostrarCarrinho();
                    Utils.limparTela();
                    break;
            }

        }while (opcao != 0);
    }

    private static void mostrarEstoque() {

        int opcao = 0;

        do{
            System.out.printf("%10s************************************************************\n",esp);
            System.out.printf("%10s*-----------------------!! ESTOQUE !!----------------------*\n",esp);
            System.out.printf("%10s************************************************************\n",esp);
            System.out.printf("%15s%5sC�D%5sDescri��o%5sQTD%5sPRE�O\n\n",esp,esp,esp,esp,esp);
            for(Produto p : estoque){
                System.out.printf("%15s%5s%d%8s%"+Utils.tamanhoMaiorString(estoque)+"s%9d%6sR$%.2f\n",esp,esp,
                        p.getCodigoDoProduto(),esp,p.getNomeDoProduto(),p.getQtdEmEstoque(),esp,p.getPrecoDoProduto());
            }
            System.out.printf("%10s************************************************************\n",esp);
            System.out.printf("%10s Digite 0 para voltar ao menu principal: ",esp);
            opcao = sc.nextInt();
        }while(opcao != 0);
    }

    private static void comprar() throws InterruptedException {

        int opcao = 0;
        Compra carrinho;

        //Verifica se a compra está finalizada
        if(!cliente.getCompras().isCompraFinalizada()){
            carrinho = cliente.getCompras();
        }else{
            carrinho = new Compra();
        }

        do{
            System.out.printf("%10s************************************************************\n",esp);
            System.out.printf("%10s*-----------------------!! ESTOQUE !!----------------------*\n",esp);
            System.out.printf("%10s************************************************************\n",esp);
            System.out.printf("%15s%5sC�D%5sDescri��o%5sQTD%5sPRE�O\n\n",esp,esp,esp,esp,esp);
            for(Produto p : estoque){
                System.out.printf("%15s%5s%d%8s%"+Utils.tamanhoMaiorString(estoque)+"s%9d%6sR$%.2f\n",esp,esp,
                        p.getCodigoDoProduto(),esp,p.getNomeDoProduto(),p.getQtdEmEstoque(),esp,p.getPrecoDoProduto());
            }
            System.out.printf("%10s************************************************************\n",esp);
            System.out.printf("%10s Digite o c�digo do produto que deseja comprar ou para voltar ao menu principal: ",esp);
            opcao = sc.nextInt();

            if(opcao != 0){
                Produto x = pesquisaProduto(opcao);
                if(x != null){ //Verifica se o produto existe
                    System.out.printf("%10s Digite a quantidade do produto que deseja comprar: ",esp);
                    int qtd = sc.nextInt();
                    if(qtd > x.getQtdEmEstoque()){ //Verifica se existe quantidade suficiente no estoque
                        System.out.printf("%10s N�o há estoque suficiente deste produto!!\n ",esp);
                        Thread.sleep(2000);
                        Utils.limparTela();
                    }else{
                        Utils.limparTela();
                        if(cliente.getCompras().getProdutos().isEmpty()){ //Se existe produto no carrinho
                            x.setQtdDoPedido(qtd);
                            x.setPre�oTotaldoProduto(x.getPrecoDoProduto()*qtd);
                            carrinho.getProdutos().add(x);

                            //Atualizar estoque
                            estoque.remove(x);
                            x.setQtdEmEstoque(x.getQtdEmEstoque()-qtd);
                            estoque.add(x);

                            cliente.setCompras(carrinho);
                        }else{
                            Produto temp = pesquisaProdutoCarrinho(x, cliente.getCompras());
                            if(temp != null){
                                carrinho.getProdutos().remove(temp);
                                temp.setQtdDoPedido(temp.getQtdDoPedido()+qtd);
                                temp.setPre�oTotaldoProduto(temp.getPrecoDoProduto() * temp.getQtdDoPedido());
                                carrinho.getProdutos().add(temp);

                                //Atualizar estoque
                                estoque.remove(temp);
                                temp.setQtdEmEstoque(temp.getQtdEmEstoque()-qtd);
                                estoque.add(temp);

                                cliente.setCompras(carrinho);
                            }else{
                                x.setQtdDoPedido(qtd);
                                x.setPre�oTotaldoProduto(x.getPrecoDoProduto()*qtd);
                                carrinho.getProdutos().add(x);

                                //Atualizar estoque
                                estoque.remove(x);
                                x.setQtdEmEstoque(x.getQtdEmEstoque()-qtd);
                                estoque.add(x);

                                cliente.setCompras(carrinho);
                            }
                        }
                    }
                }
            }

        }while(opcao != 0);
    }

    private static Produto pesquisaProduto(int opcao) {
        for (Produto p : estoque){
            if(p.getCodigoDoProduto() == opcao){
                return p;
            }
        }
        return null;
    }

    private static Produto pesquisaProdutoCarrinho(Produto x, Compra compras) {
        for(Produto p : compras.getProdutos()){
            if(p.getCodigoDoProduto() == x.getCodigoDoProduto()){
                return p;
            }
        }
        return null;
    }

    private static void MostrarCarrinho() throws InterruptedException {
      int opcao = 0;

       cliente.getCompras().setValorTotalDaCompra(calculaValorTotal());

        do {
            System.out.printf("%10s************************************************************\n", esp);
            System.out.printf("%10s*-----------------------!! CARRINHO !!---------------------*\n", esp);
            System.out.printf("%10s************************************************************\n", esp);
            System.out.printf("%15s%5sC�D%5sDescri��o%5sQTD%5sPRE�O\n\n", esp, esp, esp, esp, esp);
            for (Produto p : cliente.getCompras().getProdutos()) {
                System.out.printf("%15s%5s%d%8s%" + Utils.tamanhoMaiorString(estoque) + "s%9d%6sR$%.2f\n", esp, esp,
                        p.getCodigoDoProduto(), esp, p.getNomeDoProduto(), p.getQtdDoPedido(), esp,
                        p.getPrecoDoProduto()*p.getQtdDoPedido());
            }
            System.out.printf("%10s************************************************************\n", esp);
            System.out.printf("%10sVALOR TOTAL DO PEDIDO: R$%.2f\n", esp, cliente.getCompras().getValorTotalDaCompra());
            System.out.printf("%10s************************************************************\n\n", esp);

            System.out.println("\n\n\n");
            System.out.printf("%10s MEIOS DE PAGAMENTOS\n\n ", esp);
            System.out.printf("%10s 1. � vista (dinheiro ou pix) tem 20%% de desconto\n ", esp);
            System.out.printf("%10s 2. � vista no cr�dito tem 10%% de desconto\n ", esp);
            System.out.printf("%10s 3. Cr�dito a vista com cart�o banco pan tem 15%% de desconto\n ", esp);
            System.out.printf("%10s 4. Parcelado em at� 3x n�o tem desconto\n\n ", esp);

            System.out.printf("%10s Digite o c�digo do meio de pagamento que você deseja utilizar: " +
                    "(Digite 0 para voltar ao menu principal) ", esp);
            opcao = sc.nextInt();
            sc.nextLine();
            System.out.printf("\n");
            if(opcao != 0) {

                Utils.limparTela();
                cadastrarCliente();
                Utils.limparTela();

                switch (opcao) {
                    case 1:
                        mostrarNotaFiscal(20);
                        break;
                    case 2:
                        mostrarNotaFiscal(10);
                        break;
                    case 3:
                        mostrarNotaFiscal(15);
                        break;
                    case 4:
                        mostrarNotaFiscal(0);
                        break;
                }
            }

        }while (opcao != 0);
    }

    private static void cadastrarCliente() {

        System.out.printf("%10sCADASTRO DO CLIENTE\n\n",esp);
        System.out.printf("%10sDigite o nome do cliente: ",esp);
        String nome = sc.nextLine();
        cliente.setNome(nome);
        System.out.printf("\n");
        System.out.printf("%10sDigite o CPF do cliente: ",esp);
        String cpf = sc.nextLine();
        cliente.setCpf(cpf);
    }

    private static void mostrarNotaFiscal(int i) throws InterruptedException {

        double valorPago = 0;
        int opcao = 0;

        if(i == 20){
            System.out.printf("%10sValor a Pagar:  R$%.2f:\n", esp,cliente.getCompras().getValorTotalDaCompra() - getDesconto(i) );
            System.out.printf("%10s Digite o valor que deseja pagar: ",esp);
            valorPago = sc.nextDouble();
        }
        Utils.limparTela();
        do{
        System.out.printf("%10s*************************************************************\n",esp);
        System.out.printf("%10s%8sCNPJ: 00.000.000/000.99 SUPERMERCADO MANGUA�A%8s\n",esp,esp,esp);
        System.out.printf("%10s%7sRua Tapaxanas, 069, Centro, Pindamonhangapio-PL%7s\n",esp,esp,esp);
        System.out.printf("%10s%1sDocumento Auxiliar da Nota Fiscal do Consumidor Eletronica%2s\n",esp,esp,esp);
        System.out.printf("%10s************************************************************\n\n",esp);
        System.out.printf("%10sC�digo%3sDescri��o%10sQTD e UN%5sVl Unit%5sVl Total\n",esp,esp,esp,esp,esp);
        for(Produto p : cliente.getCompras().getProdutos()){
            System.out.printf("%10s%-10d%"+Utils.tamanhoMaiorString(cliente.getCompras().getProdutos())+
                    "s%16d%s%8sR$%.2f%5sR$%.2f\n",esp,p.getCodigoDoProduto(),p.getNomeDoProduto(),p.getQtdDoPedido(),p.getUnidade(),
                    esp,p.getPrecoDoProduto(),esp,p.getPrecoDoProduto()*p.getQtdDoPedido());
        }

        double valorComDesconto = cliente.getCompras().getValorTotalDaCompra() - getDesconto(i);

        System.out.printf("\n\n");
        System.out.printf("%10sQtde total de Itens:%39s%d \n",esp,esp,cliente.getCompras().getProdutos().size());
        System.out.printf("%10sValor Total R$:%40s%-5.2f \n",esp,esp,cliente.getCompras().getValorTotalDaCompra());
        System.out.printf("%10sDesconto R$:%43s%-5.2f \n",esp,esp,getDesconto(i));
        System.out.printf("%10sValor a pagar R$:%38s%-5.2f \n",esp,esp,valorComDesconto);
        System.out.printf("%10sFORMA DE PAGAMENTO%31sVALOR PAGO R$\n",esp,esp);
        switch (i){
            case 20:
                System.out.printf("%10sDinheiro%45s%.2f\n",esp,esp,valorPago);
                break;
            case 10:
                System.out.printf("%10sCart�o%45s%.2f\n",esp,esp,valorComDesconto);
                break;
            case 15:
                System.out.printf("%10sCart�o PAN%45s%.2f\n",esp,esp,valorComDesconto);
                break;
            case 0:
                System.out.printf("%10sCart�o PARC%45s%.2f\n",esp,esp,valorComDesconto);
                break;
        }

        if(i == 20)
            System.out.printf("%10sTroco R$%45s%.2f\n",esp,esp,valorPago-valorComDesconto);

        System.out.printf("%10s************************************************************\n\n",esp);
        System.out.printf("%10s%14sConsulta pela Chave de Acesso em:%14s\n",esp,esp,esp);
        System.out.printf("%10s%11shttp://nfce.fazenda.pl.gov.br/consulta%11s\n",esp,esp,esp);
        System.out.printf("%10s%3s1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111%3s\n\n",esp,esp,esp);
        System.out.printf("%10s************************************************************\n\n",esp);

        System.out.printf("%10sCONSUMIDOR: CPF: %s   - %s\n",esp,cliente.getCpf(),cliente.getNome());

        String dataHj = LocalDate.now().getDayOfMonth() +"/"+LocalDate.now().getMonthValue()+"/"+LocalDate.now().getYear();
        String horaHj = LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute();

        System.out.printf("%10sNFC-e nº 000000001  Série 001       %s      %s\n\n",esp, dataHj,
                horaHj);
        double tributo = getTributo(cliente.getCompras().getValorTotalDaCompra());
        System.out.printf("%10s%3sTributos Totais Incidentes (Lei Federal 12.741/2012) R$%.2f\n\n",esp,esp,tributo);
            System.out.printf("%10s Digite 0 para sair: ",esp);
            opcao = sc.nextInt();
            cliente.getCompras().setCompraFinalizada(true);
            if(opcao == 0){
                Utils.limparTela();
                menuPrincipal();
            }
        }while (opcao != 0);
    }

    private static double getTributo(double valorTotalDaCompra) {
        double valor = valorTotalDaCompra;
        double percentual = 9 / 100.0;

        double valorFinal = (percentual * valor);
        return valorFinal;
    }

    private static double getDesconto(int i) {
        double valor = cliente.getCompras().getValorTotalDaCompra();
        double percentual = i / 100.0;

        double valorFinal = (percentual * valor);
        return valorFinal;
    }

    private static double calculaValorTotal() {
        double valor = 0;
        for(Produto p : cliente.getCompras().getProdutos()){
            valor += p.getPre�oTotaldoProduto();
        }
        return valor;
    }
}



