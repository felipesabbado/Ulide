# Ultimate Lisbon Guide

**IADE - Faculdade de Design, Tecnologia e Comunicação**

Engenharia Informática — 3.º semestre

Felipe Campelo Sabbado (20191012); Leonardo Lage (20200859)

Projeto Multidisciplinar:
- Projeto de Desenvolvimento Mobile (Pedro Rosa);
- Programação Mobile (João Dias);
- Programação Orientada por Objetos (Miguel Bugalho);
- Bases de Dados (Miguel Boavida);
- Competências Comunicacionais (Alexandra Santos,);
- Matemática Discreta (Rodolfo Bendoyro)

1 de outubro de 2021

## **Resumo**
Aplicação para auxiliar turistas, estrangeiros ou não, no planeamento das suas férias com indicação de rotas e locais turísticos segundo os interesses do utilizador. Além disso, a aplicação irá disponibilizar informações sobre distâncias, preços, indicação etária, tempo médio de visitação e tempo total do trajeto para melhorar, assim, a experiência e o aproveitamento do período de lazer.

**Palavras-chave:**  turismo; rotas; caminhos; pontos turísticos; atrações; Lisboa; restauração; hotelaria.

## **Contexto**
Quando estamos de férias o que menos desejamos é preocupações. Atualmente, cada vez mais pessoas têm períodos reduzidos para passeios, por isso querem aproveitar ao máximo o seu momento de lazer. E a experiência turística pode ser por vezes insatisfatória quando não encontramos os locais desejados ou eles estão fechados, perdemos tempo nas rotas ou acabamos por percorrer longas distâncias a pé por não conhecermos a região. Por vezes os locais turísticos têm que se pagar e convêm saber o valor. Outras vezes é preciso saber se a atração é indicada para crianças ou se não é demasiado cara. Também é importante a possibilidade de descobrirmos atrações não tão famosas, mas igualmente gratificantes para que as férias se transformem em experiências únicas.

## **Objetivos**
Esta ferramenta visa facilitar a vida de quem quer conhecer uma cidade sem ficar perdido a procurar os pontos turísticos, sem perder tempo entre trajetos ineficientes e agilizar o processo de decisão dos locais de visitação. Permitirá organizar e planear o trajeto e aproveitar melhor o passeio e os pontos turísticos escolhidos. Além disso, a aplicação indicará locais próximos aos pontos de interesse do utilizador que poderá alterar o roteiro em tempo real, incluindo ou excluindo pontos turísticos e, assim, permitindo com que ele conheça e desfrute de novas experiências, tendo em conta os seus gostos e preferências. O utilizador terá informações detalhadas sobre o seu trajeto e sobre as atrações escolhidas, permitindo decisões melhores e mais embasadas.

## **Público-alvo**
Pessoas (turistas), estrangeiras ou não, que desejam conhecer atrações sem ficar perdidas a procurar os locais ou que gostam de se planear.

## **Descrição**
Ao se cadastrar, o utilizador criará um perfil indicando as categorias favoritas das atrações e, com isso, terá indicação de rotas com características semelhantes às previamente selecionadas. Em seguida, será possível escolher qual rota deseja percorrer entre as diversas opções sugeridas. Também será possível utilizar a ferramenta de busca para selecionar as rotas por lugares ou características (filtros ou tags). Tais trajetos conterão imagens ilustrativas e informações que poderão ser relevantes, tais como, descrição, tempo médio, distância total, entre outras. O utilizador também poderá selecionar e ver detalhes específicos dos pontos turísticos que compõem a rota.


## **Pesquisa de mercado**
Nenhuma das aplicações pesquisadas tem a possibilidade de criar rotas com os pontos turísticos escolhidos pelo utilizador, função esta a ideia principal deste projeto.

### **Lisbon City Guides, Offline Maps, Tours and Hotels**
_Prós:_ Entre todas as ‘apps’ que utilizamos esta tem a interface mais fácil de utilizar e mais bem organizada. Quando clicamos num monumento em específico podemos ver imagens, uma breve descrição, outros pontos turísticos perto ou similares. É a única, dentre as analisadas, que permite ao utilizador escolher como ponto de partida a sua localização atual ou o primeiro local da rota.<br/>
_Contras:_ As rotas são criadas aleatoriamente e o utilizador só pode escolher como parâmetro de criação o tempo de duração rota. Quando clicamos num monumento em específico podemos ver outros pontos turísticos próximos e semelhantes, porém não podemos adicionar à rota criada.

### **Guia de Lisboa de Civitatis**
_Prós:_ Indicação de um roteiro para visitar Lisboa em 48 horas. Possui bastantes informações sobre as atrações turísticas. Mostra locais onde se pode comprar produtos nacionais como, pastéis, vinhos, queijos, entre outros. Também possui uma aba de atividades.<br/>
_Contras:_ Não permite a criação de um roteiro próprio. Possui poucos pontos turísticos cadastrados por isso tem pouca liberdade de escolha. Sem indicações de atrações semelhantes conforme o perfil do utilizador. Apenas sete das opções que estão na aba de atividades são grátis

### **Lisbon Travel Guide (Ulmon):**
_Prós:_ A primeira vez que utilizamos tem um pequeno tutorial para explicar como a ‘app’ funciona. Tem um vasta escolha de pontos turísticos organizados por recomendações como, lisbon’s best shopping tips, Recommended: “Feiras”, Recommended: Beaches, etc.<br/>
_Contras:_ Pouca informação sobre os monumentos, por exemplo, na página do Mosteiro dos Jerónimos só nos é visível um número de telefone, e-mail e fotos.

## **Guiões de teste**

Ao abrir o aplicativo poderemos visualizar uma barra de pesquisa no topo do ecrã, por baixo desta barra estará um mapa da zona onde a pessoa está e também haverá um ícone no canto superior esquerdo. Ao clicar neste ícone vamos poder selecionar: ver o meu perfil; criar um ponto, escolher rotas; as minhas rotas.

**Caso 1 -  Escolher a rota:**
- Passo 1: escolher a aba de rotas.
- No ecrã irá aparecer as rotas mais populares e uma barra de scroll down permitirá ver mais rotas. 
- Passo 2: caso queira aplicar filtros à rota para restringir os resultados.
- Passo 3: clicar numa rota.
	- Ao clicar numa rota podemos:
	- Passo 3.1: clicar no botão verde escrito iniciar.
	- Passo 3.2: iniciar o passeio.
- Após passar por um ponto de interesse e se afastar alguns metros, irá aparecer uma notificação sugerindo ao utilizador que entre na aplicação e avalie o ponto de interesse.
	- Passo 3.3: avaliar o ponto de interesse numa escala de 0 a 5.
	- Passo 3.4: escrever um comentário

	**OU**

	- Passo 3.1: clicar numa estrela no canto superior direito para guardar nos favoritos.
	- Passo 3.2: ver se gosta das características (distância, tempo, ...) da rota.
	- Passo 3.3: clicar num ponto turístico em específico para visualizar as suas características (descrição, fotos, horários, ...).
	- Passo 3.4: voltar para o menu da rota clicando na seta no canto superior esquerdo.
		- Passo 3.4.1: clicar no botão verde escrito iniciar.
		- Passo 3.4.2: iniciar o passeio.
		
		**OU**
		
		- Passo 3.4.1: voltar para o menu onde estão todas as rotas e escolher outra.
- Ao final do percurso uma notificação aparecerá ao utilizador sugerindo que ele vá para a aplicação e avalie a rota.
- Passo 4: avaliar o ponto de interesse numa escala de 0 a 5.
- Passo 5: escrever um comentário sobre a rota.


**Caso 2 - Indicar e criar pontos turísticos:** 
- Passo 1: escolher a aba de criar ponto.
- Aparecerá uma espécie de formulário para o utilizador preencher com os dados do ponto.
- Passo 2: Indicar o local da atração
- Passo 2.1: escolher no mapa

**OU**

- Passo 2.2: utilizar a própria localização  
- Passo 3: escrever o nome da atração.
- Passo 4: selecionar fotos do local.
- Passo 5: escrever uma breve descrição (será opcional) 
- Passo 6: escrever algumas tags que se adequem ao local
- Passo 7: clicar no botão de enviar 

## **Pitch:** 
**Link para o vídeo** - https://youtu.be/DAEeR5r4aHI
