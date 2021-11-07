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


## **Resumo**
Aplicação para auxiliar turistas, estrangeiros ou não, no planeamento das suas férias com indicação de rotas e locais turísticos segundo os interesses do utilizador. Além disso, a aplicação irá disponibilizar informações sobre distâncias, preços, indicação etária, tempo médio de visitação e tempo total do trajeto para melhorar, assim, a experiência e o aproveitamento do período de lazer de seus utilizadores.

**Palavras-chave:**  turismo; rotas; caminhos; pontos turísticos; atrações; Lisboa; restauração; hotelaria.

## **Contexto**
Quando estamos de férias o que menos desejamos é preocupações. Atualmente, cada vez mais pessoas têm períodos reduzidos para passeios, por isso querem aproveitar ao máximo o seu momento de lazer. E a experiência turística pode ser por vezes insatisfatória quando não encontramos os locais desejados ou eles estão fechados, perdemos tempo nas rotas ou acabamos por percorrer longas distâncias a pé por não conhecermos a região. Por vezes os locais turísticos têm que se pagar e convêm saber o valor. Outras vezes é preciso saber se a atração é indicada para crianças ou se não é demasiado cara. Também é importante a possibilidade de descobrirmos atrações não tão famosas, mas igualmente gratificantes para que as férias se transformem em experiências únicas (Mendes Filho et al., 2017, Palos-Sanchez et al., 2021, Richards, 2009).

## **Objetivos**
Esta ferramenta visa facilitar a vida de quem quer conhecer uma cidade sem ficar perdido a procurar os pontos turísticos, sem perder tempo entre trajetos ineficientes e agilizar o processo de decisão dos locais de visitação. Permitirá organizar e planear o trajeto e aproveitar melhor o passeio e os pontos turísticos escolhidos. Além disso, a aplicação indicará locais próximos aos pontos de interesse do utilizador que poderá alterar o roteiro em tempo real, incluindo ou excluindo pontos turísticos e, assim, permitindo com que ele conheça e desfrute de novas experiências, tendo em conta os seus gostos e preferências. O utilizador terá informações detalhadas sobre o seu trajeto e sobre as atrações escolhidas, permitindo decisões melhores e mais embasadas.

## **Público-alvo**
Pessoas (turistas), estrangeiras ou não, que desejam conhecer atrações sem ficar perdidas a procurar os locais ou que gostam de se planear.

## **Descrição**
Ao se cadastrar, o utilizador criará um perfil indicando as categorias favoritas das atrações e, com isso, terá indicação de rotas com características semelhantes às previamente selecionadas. Em seguida, será possível escolher qual rota deseja percorrer entre as diversas opções sugeridas. Também será possível utilizar a ferramenta de busca para selecionar as rotas por lugares ou características (filtros ou _tags_). Tais trajetos conterão imagens ilustrativas e informações que poderão ser relevantes, tais como, descrição, tempo médio, distância total, entre outras. O utilizador também poderá selecionar e ver detalhes específicos dos pontos turísticos que compõem a rota. <br/>
A aplicação será desenvolvida inicialmente para a cidade de Lisboa, podendo, caso haja disponibilidade, ser ampliada para outras cidades ou mesmo para Portugal inteira.

## **Pesquisa de mercado**
Nenhuma das aplicações pesquisadas tem a possibilidade de criar rotas com os pontos turísticos escolhidos pelo utilizador, função esta a ideia principal deste projeto.

### **Lisbon City Guides, Offline Maps, Tours and Hotels**
_Prós:_ Entre todas as ‘apps’ que utilizamos esta tem a interface mais fácil de utilizar e mais bem organizada. Quando clicamos num monumento em específico podemos ver imagens, uma breve descrição, outros pontos turísticos perto ou similares. É a única, dentre as analisadas, que permite ao utilizador escolher como ponto de partida a sua localização atual ou o primeiro local da rota.<br/>
_Contras:_ As rotas são criadas aleatoriamente e o utilizador só pode escolher como parâmetro de criação o tempo de duração rota. Quando clicamos num monumento em específico podemos ver outros pontos turísticos próximos e semelhantes, porém não podemos adicionar à rota criada.
#### Links:
**Aplicação:** https://play.google.com/store/apps/details?id=guide.portugal.lisbon.prod <br/>
**Site:** https://www.zonzofox.com/destinations

### **Guia de Lisboa de Civitatis**
_Prós:_ Indicação de um roteiro para visitar Lisboa em 48 horas. Possui bastantes informações sobre as atrações turísticas. Mostra locais onde se pode comprar produtos nacionais como, pastéis, vinhos, queijos, entre outros. Também possui uma aba de atividades.<br/>
_Contras:_ Não permite a criação de um roteiro próprio. Possui poucos pontos turísticos cadastrados por isso tem pouca liberdade de escolha. Sem indicações de atrações semelhantes conforme o perfil do utilizador. Apenas sete das opções que estão na aba de atividades são grátis.
#### Links:
**Aplicação:** https://play.google.com/store/apps/details?id=com.civitatis.lisboa <br/>
**Site:** https://www.disfrutalisboa.com/

### **Lisbon Travel Guide (Ulmon):**
_Prós:_ A primeira vez que utilizamos tem um pequeno tutorial para explicar como a ‘app’ funciona. Tem um vasta escolha de pontos turísticos organizados por recomendações como, lisbon’s best shopping tips, Recommended: “Feiras”, Recommended: Beaches, etc.<br/>
_Contras:_ Pouca informação sobre os monumentos, por exemplo, na página do Mosteiro dos Jerónimos só nos é visível um número de telefone, e-mail e fotos.
#### Links:
**Aplicação:** https://play.google.com/store/apps/details?id=guide.portugal.lisbon.prod <br/>
**Site:** https://www.zonzofox.com/destinations

## **Guiões de teste**

➢ **Tela Inicial:** Ao abrir a aplicação, visualizamos um mapa da zona onde o utilizador está (obtido pelos dados de GPS do telemóvel). No topo do ecrã há um ícone de menu (**Menu Principal**) no canto esquerdo.<br/>
➢ **Menu Principal:** Painel deslizante que ocupa parcialmente a lateral esquerda do ecrã. Nele é possível visualizar, de cima para baixo, a foto do utilizador (ou um ícone padrão), os botões de **Meu Perfil**, **Escolher Rotas**, **Criar Ponto** e **Meus Favoritos**.<br/>
➢ **Meu Perfil:** Painel para o utilizador informar e editar informações pessoais tais como foto, nome, e-mail, senha e configurações.<br/>
➢ **Escolher Rotas:** O ecrã exibe uma lista com as rotas mais populares onde será possível fazer _scroll_ vertical para navegar entre elas. No topo, da esquerda para direita, há um ícone de voltar, uma barra de pesquisa e um ícone de filtro. Os itens da lista são compostos por: um pequeno mapa que mostra o trajeto e seus pontos com um ícone de estrela (favorito - ver Caso 1, passo 2.4) e as informações principais da rota (distância, tags, tempo médio e preço).<br/>
➢ **Criar Ponto:** Formulário para o utilizador preencher com os dados do ponto.<br/>
➢ **Meus Favoritos:** Painel com as rotas e pontos favoritos do utilizador.<br/>
➢ **Painel de Rota:** É exibido um ecrã com uma barra no topo formada por um ícone de voltar (retorna para o ecrã anterior), um ícone de casa (retorna para a **Tela Inicial**), o nome e o _ranking_ da rota. Em seguida há o mapa do trajeto com o ícone de estrela (favorito - ver Caso 1, passo 2.4), uma breve descrição, uma lista de pontos turísticos (uma foto, o nome e suas tags) que compõem a rota e um botão de iniciar rota.<br/>
➢ **Painel de Ponto Turístico:** Ecrã com uma barra no topo formada por um ícone de voltar (retorna para o ecrã anterior), um ícone de casa (retorna para a **Tela Inicial**), o nome e o _ranking_ do ponto. De cima para baixo, uma foto do local ocupando toda a extensão horizontal do ecrã com um ícone de estrela (favorito - ver Caso 1, passo 2.4) no canto superior direito, uma breve descrição, uma sequência de fotos exibidas em um _scroll_ horizontal, uma lista de locais próximos e uma sessão de comentários.<br/>


### Caso 1 - Utilização principal:
1. Ao clicar no botão de menu, na **Tela Inicial**, um painel lateral desliza da esquerda para direita cobrindo a metade esquerda do ecrã (**Menu Principal**).
2. Selecionar o botão **Escolher Rotas**.<br/>
	2.1. O botão de voltar retorna à **Tela Inicial**.<br/>
	2.2. Na barra de pesquisa é possível filtrar as rotas exibidas na lista a partir da digitação de um nome.<br/>
	2.3. O botão de filtro abre um novo ecrã onde é possível filtrar a lista de rotas de acordo com outras opções (distância, _tags_, tempo médio, preço, etc.).<br/>
	2.4. Clicar no ícone de estrela alterna entre adicionar às rotas favoritas (ícone fica amarelo) ou excluir das rotas favoritas (ícone fica cinza).<br/>
3. Clicar em um dos itens da lista exibe um **Painel de Rota**.<br/>
	3.1. Clicar em um dos pontos abre um **Painel de Ponto Turístico**.<br/>
	3.2. Clicar no botão iniciar a rota.<br/>
	3.3. Após passar por um ponto de interesse e se afastar alguns metros, aparece uma notificação sugerindo que o utilizador avalie o ponto de interesse numa escala de 1 a 5 e escreva um comentário.

### Caso 2 - Criar Pontos Turísticos:
1. No **Menu Principal**, clicar no botão **Criar Ponto**.
2. Indicar o local da atração escolhendo um ponto no mapa ou utilizando a própria localização.
3. Preencher o formulário.<br/>
	3.1. Escrever o nome da atração.<br/>
	3.2. Selecionar fotos do local.<br/>
	3.3. Escrever uma descrição (opcional).<br/>
	3.4. Escrever ou selecionar tags relacionadas ao local.<br/>
4. Clicar no botão de enviar.

## **Pitch:** 
**Link para o vídeo** - https://youtu.be/DAEeR5r4aHI

## Referências Bibliográficas
Mendes Filho, L. et al. (2017). Aplicativos Móveis e Turismo: Um Estudo Quantitativo Aplicando a Teoria do Comportamento Planejado. _Rosa dos Ventos, 9, 2, 178-194._
https://doi.org/10.18226/21789061.v9i2p179
<br><br/>
Palos-Sanchez, P. et al. (2021). Do tourism applications’ quality and user experience influence its acceptance by tourists?. _Review of Managerial Science 15, 1205–1241._
https://doi.org/10.1007/s11846-020-00396-y
<br><br/>
Richards, G. (2009). Turismo cultural: Padrões e implicações. In de Camargo, P. and da Cruz, G. (eds) _Turismo Cultural: Estratégias, sustentabilidade e tendências. UESC:
Bahia, pp. 25-48._
https://www.researchgate.net/publication/346986866_Turismo_Cultural_Padroes_e_implicacoes
<br><br/>
