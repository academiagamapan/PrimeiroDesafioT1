# Desafio 1
<img alt="logo banco pan e gama academy" src="https://github.com/joaomhernandes/PAN-Academy/blob/main/Assets/gama-pan-academy-logo.svg" style="width: 300%, height: auto, margin-left: auto, margin-left: auto" />


# Turma 1 - Grupo 6 👩‍💻👨‍💻🚀

# Integrantes

Participante | Linkedin | Github
:--------- | :------ | :-------
Bruno Claudino | <a href="https://www.linkedin.com/in/brunoclaudino/" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>  | <a href="https://github.com/brunoclaudino" target="_blank"><img src="https://img.shields.io/github/followers/brunoclaudino?style=social" target="_blank"></a>
Charllyson Souza | <a href="https://www.linkedin.com/in/charllyson-souza-248576108/" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a> | <a href="https://github.com/charllysonsouza" target="_blank"><img src="https://img.shields.io/github/followers/charllysonsouza?style=social" target="_blank"></a> 
João M H Carrenho | <a href="https://www.linkedin.com/in/joão-maurício-hernandes-carrenho/" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a> | <a href="https://github.com/joaomhernandes" target="_blank"><img src="https://img.shields.io/github/followers/joaomhernandes?style=social" target="_blank"></a> 
Mateus Almeida | <a href="https://www.linkedin.com/in/mateus-almeida-312a27129/" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a> | <a href="https://github.com/mateusMBA" target="_blank"><img src="https://img.shields.io/github/followers/mateusMBA?style=social" target="_blank"></a> 
Paulo Queiroz | <a href="https://www.linkedin.com/in/paulo-queiroz-7048b1a0" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a> | <a href="https://github.com/hawkkawa" target="_blank"><img src="https://img.shields.io/github/followers/hawkkawa?style=social" target="_blank"></a> 

## Apresentação do desafio

 Será criado uma Loja virtual com Carrinho de Compras!

 Em resumo será possível buscar produtos por código, adicionar quantidades dos itens selecionados, somar os preços de diversos itens a serem comprados, cálculo das taxas e adicão de desconto dependendo da forma do pagamento.

# Requisitos:

A nossa loja deve conter estoque;
Caso produto esteja indisponível deve retornar a mensagem para o cliente “Produto Indisponível”;
Deverá ter 3 formas de pagamento: 
À vista (dinheiro ou pix) tem 20% de desconto;
À vista no crédito tem 10% de desconto;
Parcelado em até 3x não tem desconto;
Deve retornar a nota fiscal com o valor pago de tributos de 9% sobre a compra(Esse valor não deve ser somado ao total do carrinho, somente ser exibido na nota fiscal);
Caprichem no README lá vocês podem contar como fizeram e como foi o trabalho em equipe.
O Projeto pode ser feito somente em uma classe ou da forma que acharem melhor;
O PROGRAMA TEM QUE RODAR NO CONSOLE.


# Desenvolvimento

## 1º encontro em 20/10/2021

Inicialmente foi discutido e modelado o problema pelo grupo, chegando ao modelo abaixo:
<img title="Modelo para o desafio 1" src="https://github.com/joaomhernandes/Desafio1/blob/João_Maurício/assets/modelagem.svg" alt="Modelagem do projeto" style="width: 244px, height: auto, margin-left: auto, margin-left: auto"/>

Em seguida foram desenvolvidas as classes Produtos e Loja. Na classe produto, além de seus atributos foram impementados o Constructor, os Getters e Setters, e Sobrescrita o metodo toString, a fim de formatar a saída do método.
Ja na classe Loja foram implementados os métodos div(), verMenuPrincipal(), verProdutos(), verCarrinho().
 - div() - Cria uma divisória, com ou sem titulo, para ajudar na formatação dos menus e formulários.
 - verMenuPrincipal() - Imprime a tela de menu, e utiliza a classe Scanner para ler a opção desejada. A estrutura de decisão utilizada foi uma estrutura de decisão condicional encadeada utilizando-se if else, e ao invés de se utilizar uma estrutura de repetição, optou-se por realizar um metodo recursivo, onde o método chama a si mesmo para efetuar uma repetição. 
 - verProdutos() - (To do) Levará para a lista de produtos disponíveis.
 - verCarrinho() - (To do) Levará para o carrinho de compras.

Nos próximos encontros serão tratados os (To do) e a implementação dos métodos pagamento(), notaFiscal() e cancelarProduto(). 

## 2º encontro em 21/10/2021

Foram implementados os métodos verProdutos() e verCarrinho(), que haviam ficado como (To do) no último encontro. Também foram implementadas na classe Loja os métodos:

- removerProduto() - Remove produtos do carrinho de compras na quantidade desejada. Este método está substituindo o cancelarProduto().  
- pagamento() - Leva para a tela de pagamento e, após escolha da forma de pagamente pelo usuário, faz o cálculo do valor a ser pago e valor de parcelas, caso se aplique. Em seguida chama o método notaFiscal() passando esses valores.
- criarEstoque() - Popula o estoque da loja com valores pré-definidos.
- notaFiscal() - (To do) Levará para tela de emissão de nota fiscal e gerará o formulário da nota fiscal da compra.

Foi também ajustados os atributos da classe Produto, bem como seu contructor e alguns de seus getters e setters e criado o método decrementarQuantidade().
Nos próximos encontros será tratado o (To do) do método notaFiscal().

## 3º encontro em 22/10/2021

Conclusão do Desafio 1, implementamos a classe notaFiscal() que havia ficado como (To do), também foi implementado o método:

- getDateTime() - Retorna a data e horario do sistema no formato "dd/MM/yyyy HH:mm:ss" na forma de string.

Foi realizada a seguinte mudança em relação à modelagem inicial: Será possivel acessar o carrinho de comprar a partir da liste de produtos disponiveis e vice versa.

Por último foram formatados os formulários da aplicação utilizando-se o comando System.out.format().E discutido a forma que será apresentado o código para a turma.

## 4º encontro em 23/10/2021

Foram sanados alguns bugs e apresentado o trabalho desenvolvido para a turma.

Segue uma apresentação dos trabalhos desenvolvidos. Devido a problemas técnicos o audio da apresentação foi prejudicado.

[![](https://github.com/joaomhernandes/Desafio1/blob/João_Maurício/assets/Apresentação.png)](https://youtu.be/gWFGv9Xr2sE)








