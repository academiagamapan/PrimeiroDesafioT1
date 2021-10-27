# Super Pan Store 
## Desafio 1 da Pan Academy 🚀
- Um curso para formar desenvolverdores Java beck-end e AWS

## Objetivo
 - Criar uma loja com estoque em que o usuário seleciona produtos do estoque e adiciona-os ao seu 
carrinho de compras. Ao final, são mostardas as opções de pagamento e é emitida uma nota fiscal. 
Toda interação é feita através do terminal.

## Descrição dos códigos-fonte. ...SuperPanStore/src
### Produto
- Criação de uma classe Produto que contém código, nome, preço e unidades disoníveis como atributos;
- Os atributos devem ser passados na construção do objeto;
- Os atributos podem ser alterados pelos respectivos métodos getters() e setters();

### LojaSuperPan
- Criação da classe LojaSuperPan que possui como atributos o carrinho e o estoque;
- Os atributos são do tipo ArrayList<Produto>;
- O cadastramento de Produto ao estoque ou ao carrinho é feita através do método add() dentro dos respectivos getters() and setters();
- Esta classe possui métodos para mostrar os produtos no carrinho, no estoque, fazer compras, dar boas vindas ao usuário e agradecer pela visita.

### NotaFiscal
- Criação da classe NotaFiscal com os atributos produtos selecionados, valor total, desconto, juros e forma de pagamento;
- Atributos são definidos pelos métodos setters(). Cada um com sua peculiaridade. Por exemplo, o método setFomaPagamento() faz
com que haja uma interação entre máquina-usuário em que o usuário é requisitado a escolher uma das formas de pagemento disponíveis;
- A depender da forma de pagamento selecionada, ou os juros ou o desconto é calculado através de setJuros() e setDesconto(), respectivamente;
- Posteriormente o valor total também é calculado via setTotalGeral();
- A classe NotaFiscal também possui um método para imprimir a nota fiscal, mostrando no termial o que foi comprado, o valor total e o desconto (se aplicável),
o valor referente à impostos imbutidos (39% do valor total) e a data e hora do pagamento.

### TesteSuperPanStore
- Arquivo de testes para avaliar o funcionamento das demais classes. É nele que os objetos são instanciados e os métodos são utilizados.
 
