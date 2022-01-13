# Data-Analyss

## Aplicação

A aplicação faz uma analise dos arquivos com extensão `.dat` que estiverem no repositório de entrada e passa para o repositório de saída um arquivo, referente a cada um, sintetizando as informações de cada dado.

## Antes de Usar

1. Vá para seu `%HOMEPATH%` crie um repositório `data`, com um repositório `in` e um repositório `out` dentro da pasta.
2. Vá na aplicação `src > main > java > com > carlosvanoni > challange > dao > DataDAO` e logo no início, troque a palavra *%HOMEPATH%* para o path do seu HOME, onde está o repositório `/data`. 

## Formato dos dados que são lidos
1. *Saleman* = `001çCPFçNameçSalary`
2. *Customer* = `002çCNPJçNameçBusiness Area`
3. *Sale* = `003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name`


## Retorna 
1. Quantidade de clientes.
2. Quantidade de vendedores.
3. O ID da venda mais cara e o valor que ela foi.
4. Quem foi o pior vendedor (ou vendedores, caso mais de um tenha vendido o mesmo valor) e o valor total que conseguiram vender.


## A Aplicação suporta:
1. Ler arquivos de até `30kb` (Esse valor pode ser ajustado na classe DataDAO).
2. Ler arquivos em que os nomes dos clientes ou vendedores tenha `ç`.
3. ler arquivos com a formatação de espaço quebrado, sendo assim é possível ler mesmo que tenha dados na mesma linha, ou que tenha muitas linhas e espaço separando.
4. Caso um vendedor faça uma venda e não esteja registrado, a apicação avisa que esse não consta nos registros, e o valor dá venda não será atribuido a nenhum vendedor.
5. Caso, dentro dos detalhamentos dos produtos vendidos, falte algum dos critérios a aplicação dá um aviso que faltou um detalhe, mas continua a calcular o valor da venda ignorando este único produto.
6. Caso o ID de uma venda já tenha sido feita na leitura desse mesmo dado, a aplicação avisa no console, ignora essa venda e passa para o próximo dado.
7. Caso a formataçõ do arquivo a ser lido não esteja de acordo com nada dos padrões a aplicação gera o arquivo no diretório de saída, mas dentro vai ter um aviso reportando que não foi possível fazer a leitura
8. Caso um vendedor faça multiplas vendas, soma para o resultado final da análise.
6. A Leitura de arquivos é feita em tempo real, então os arquivos que forem colocados no diretório de `\data\in` enquanto a aplicação estiver rodadno vão ser lidos instantaneamente

## Exemplo de Dados:

### Dado de Entrada: 
001ç1234567891234çDiegoç50000

001ç3245678865434çRenatoç40000.99

002ç2345675434544345çJose da silvaçRural

002ç2345675433444345çEduardoPereiraçRural

003ç12ç [1-34-10,2-33-1.50,3-6-3] çDiego

003ç13ç [1-45-13,2-73-4.50,3-7-3] çDiego

003ç14ç [1-10-10,2-45-5,3-6-6] çRenato


### Relatório de Saída:
Amount customers: 2

Amount saleman: 2

Most expansive sale(s):

ID: 13 - Price: 934.5

Worse saleman(s):

Saleman: Renato - price of sale: 361.0
