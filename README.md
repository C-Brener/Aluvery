
# Anotações sobre compose

Essas anotações tem como objetivo principal servir de lembrete e consuta de como funciona coisas basicas no compose.

# Um pouco sobre compose

O compose trabalha de forma declarativa, ou seja, não temos tão presente os getters e setters,
contudo, para a mudança de comportamento das telas usamos a recomposição que redesenhará a tela
no momento em que o estado relacionado àquele componente tenha alguma alteração.

# Atalhos do Android Studio para compose
Digitando "comp" no android studio é um atalho que cria um composable para nós.

Digitando "prev" no android studio criamos um composable com preview.

# Preview
* Separamos o Preview de cada composables para questões organizacionais
* É possível fazer mais de um preview em um unico composable, quando utilizamos a anotação “preview” estamos criando um preview exclusivo, ou seja, se usamos mais de uma anotação, criamos mais de um preview.
* Quando se trata de preview temos algumas notações que nos ajudam em questão de visualização como:
  * showSystemUI que implementa uma tela similar a do emulador nos fazendo ter uma visão ampla de como o composable se comportaria em tela.
```Kotlin
@Preview(showSystemUi = true, name = "TextPreview")
@Composable
fun MyFirstComposablePreview() {
    AluveryTheme {
        Surface() {
            MyFirstComposable()

        }
    }
}
```

# Composables Básicos de Layout

* Row -> Os componentes dentro do Row é organizado de forma horizontal
    ```kotlin
     Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 16.dp)
                    .background(Color.Green)
                    .fillMaxHeight(0.1f)
            ) {
                Text(text = "Do Próprio")
                Text(text = " Jetpack compose")
            }
    ```
* Column -> Os componentes dentro do Column é organizado de forma vertical
    ```kotlin
            Column(
            Modifier
                .padding(all = 8.dp)
                .fillMaxWidth()
                .background(color = Color.Red)
        ) {
            Text(text = "Testando diversos layouts")
            Text(text = "Com composables padrões")
        }
    ```
* Box -> Os componentes dentro do Box ficam um sobre o outro.7
    ```kotlin
     Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .background(Color.Cyan)
            .fillMaxSize()

    ) {
        Text(text = "Testando diversos layouts")
        Text(text = "Com composables padrões")
    }
    ```

# Modifiers

* É uma interface que é implementada nos composes que nos dar o poder de personalizar nossos composables.
* A ordem da chamada do modifier de estilização importa, isso porquê a depender da chamada o comportamento pode ser diferente.
* Existem algumas extensões de modifiers que só podem ser usadas em contextos específicos, por exemplo, Modifier.align é um modifier que é utilizado em contextos de row ou column, isso porquê, esses modifiers precisam de uma orientação para funcionar o seu alinhamento.
* Quando trabalhamos com componentes externos, é sempre viável fazer com que o nosso composable seja alterado na hora de sua chamada por quem estar o chamando, isso porque ele acaba mantendo o comportamento original e sendo alterado pelo pai, exemplo, não devemos definir paddings em nossos composables

  ## Alguns Modifiers comuns:
  * [clip](https://foso.github.io/Jetpack-Compose-Playground/cookbook/how_to_create_custom_shape/)->
    Serve para fazer cortes na forma que desejammos, no caso do exemplo acima desejavamos um
    circulo.
  * [offset](https://developer.android.com/jetpack/compose/modifiers#offset) -> É um modifier de
    deslocamento, serve para deslocar um componente baseando-se no plano cartesiano. Quando o offset
    altera a posição de um item, o mesmo continua ocupando o espaçamento da posição inicial, ou
    seja, é sempre bom utilizar o offset em locais que não acabe ferindo o layout.
  ## Row Modifiers:
  * Quando trabalhamos com row ou com o Columns estamos denro do escopo desse componente em questão,
    por conta desse escopo temos acesso alguns tipos de propriedades, dentre essas propriedades
    podemos citar a arragenment, que é uma espécie de organização dos componentes que estão dentro
    do row, o arragenment tem alguns comportamentos de organização.
  * Dentre os modifiers do Row temos o horizontalScrollable que nos dá a capacidade de ter um
    scrollamento no componente de row, podemos utilizar no parametro de state:
    * ScrollState -> Definimos o ponto inicial do scroll em pixels, geralmente definido como 0
    * rememberScrollState -> Como o jetpack compose trabalha muito com estados temos o
      rememberScroll que define o estado de scrollamento e também persiste a posição em que paramos
      do scroll mesmo que haja recomposição de tela.

# Estados

* Quando trabalhamos com o jetpack compose um dos conceitos mais importantes dessa ferramenta é a
  utilização de estado.

* Os estados estão intimamente ligados com o remeber que é uma função do compose que tem o intuito
  de "lembrar" do que foi passado para ela, ou seja, quando acontece a recmposição de tela o
  remember consegue manter o valor anterior a recomposição.

* Como estamos trabalhando com paradigma declarativo as chances de ocorrer um loopping de
  recomposição é grande, isso por quê ao mexer em um item que esta ligado a algum componente ele faz
  com que esse componente redesenhe para representar o novo valor e caso isso não seja feito da
  maneira correta acaba entrando em looping.

# Slot API

* Tecnica de layout utilizada
  pelo [material](https://m3.material.io/develop/android/jetpack-compose) que tem o intuito de
  deixar "caixas" dentro de um componente para a personalização com outros composables.

OutlinedTextField:

```kotlin
 OutlinedTextField(value = searchText,
  onValueChange = { value ->
    searchTextChanged(value)
  },
  label = { Text(text = "Produto") },
  maxLines = 1,
  modifier = Modifier
    .fillMaxWidth()
    .padding(16.dp),
  shape = RoundedCornerShape(percent = 100),
  leadingIcon = {
    Icon(imageVector = Icons.Default.Search, contentDescription = null)
  },
  placeholder = {
    Text(text = "O que você procura?")
  })

```

No exemplo acima são slots api a propriedade de leadingIcon, placeholder e label.

* O Slot Api é uma extensão e não uma restrição, ou seja, você pode ou não utiliza-lo.

# Anotações sobre compose

Essas anotações tem como objetivo principal servir de lembrete e consuta de como funciona coisas
basicas no compose.

# Um pouco sobre compose

O compose trabalha de forma declarativa, ou seja, não temos tão presente os getters e setters,
contudo, para a mudança de comportamento das telas usamos a recomposição que redesenhará a tela no
momento em que o estado relacionado àquele componente tenha alguma alteração.

# Atalhos do Android Studio para compose

Digitando "comp" no android studio é um atalho que cria um composable para nós.

Digitando "prev" no android studio criamos um composable com preview.

# Preview

* Separamos o Preview de cada composables para questões organizacionais
* É possível fazer mais de um preview em um unico composable, quando utilizamos a anotação “preview”
  estamos criando um preview exclusivo, ou seja, se usamos mais de uma anotação, criamos mais de um
  preview.
* Quando se trata de preview temos algumas notações que nos ajudam em questão de visualização como:
  * showSystemUI que implementa uma tela similar a do emulador nos fazendo ter uma visão ampla de
    como o composable se comportaria em tela.

```Kotlin
@Preview(showSystemUi = true, name = "TextPreview")
@Composable
fun MyFirstComposablePreview() {
  AluveryTheme {
    Surface() {
      MyFirstComposable()

    }
  }
}
```

# Composables Básicos de Layout

* Row -> Os componentes dentro do Row é organizado de forma horizontal
    ```kotlin
     Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 16.dp)
                    .background(Color.Green)
                    .fillMaxHeight(0.1f)
            ) {
                Text(text = "Do Próprio")
                Text(text = " Jetpack compose")
            }
    ```
* Column -> Os componentes dentro do Column é organizado de forma vertical
    ```kotlin
            Column(
            Modifier
                .padding(all = 8.dp)
                .fillMaxWidth()
                .background(color = Color.Red)
        ) {
            Text(text = "Testando diversos layouts")
            Text(text = "Com composables padrões")
        }
    ```
* Box -> Os componentes dentro do Box ficam um sobre o outro.7
    ```kotlin
     Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .background(Color.Cyan)
            .fillMaxSize()

    ) {
        Text(text = "Testando diversos layouts")
        Text(text = "Com composables padrões")
    }
    ```

# Modifiers

* É uma interface que é implementada nos composes que nos dar o poder de personalizar nossos
  composables.
* A ordem da chamada do modifier de estilização importa, isso porquê a depender da chamada o
  comportamento pode ser diferente.
* Existem algumas extensões de modifiers que só podem ser usadas em contextos específicos, por
  exemplo, Modifier.align é um modifier que é utilizado em contextos de row ou column, isso porquê,
  esses modifiers precisam de uma orientação para funcionar o seu alinhamento.
* Quando trabalhamos com componentes externos, é sempre viável fazer com que o nosso composable seja
  alterado na hora de sua chamada por quem estar o chamando, isso porque ele acaba mantendo o
  comportamento original e sendo alterado pelo pai, exemplo, não devemos definir paddings em nossos
  composables

  ## Alguns Modifiers comuns:
  * [clip](https://foso.github.io/Jetpack-Compose-Playground/cookbook/how_to_create_custom_shape/)->
    Serve para fazer cortes na forma que desejammos, no caso do exemplo acima desejavamos um
    circulo.
  * [offset](https://developer.android.com/jetpack/compose/modifiers#offset) -> É um modifier de
    deslocamento, serve para deslocar um componente baseando-se no plano cartesiano. Quando o offset
    altera a posição de um item, o mesmo continua ocupando o espaçamento da posição inicial, ou
    seja, é sempre bom utilizar o offset em locais que não acabe ferindo o layout.
  ## Row Modifiers:
  * Quando trabalhamos com row ou com o Columns estamos denro do escopo desse componente em questão,
    por conta desse escopo temos acesso alguns tipos de propriedades, dentre essas propriedades
    podemos citar a arragenment, que é uma espécie de organização dos componentes que estão dentro
    do row, o arragenment tem alguns comportamentos de organização.
  * Dentre os modifiers do Row temos o horizontalScrollable que nos dá a capacidade de ter um
    scrollamento no componente de row, podemos utilizar no parametro de state:
    * ScrollState -> Definimos o ponto inicial do scroll em pixels, geralmente definido como 0
    * rememberScrollState -> Como o jetpack compose trabalha muito com estados temos o
      rememberScroll que define o estado de scrollamento e também persiste a posição em que paramos
      do scroll mesmo que haja recomposição de tela.

# Estados

* Quando trabalhamos com o jetpack compose um dos conceitos mais importantes dessa ferramenta é a
  utilização de estado.

* Os estados estão intimamente ligados com o remeber que é uma função do compose que tem o intuito
  de "lembrar" do que foi passado para ela, ou seja, quando acontece a recmposição de tela o
  remember consegue manter o valor anterior a recomposição.

* Como estamos trabalhando com paradigma declarativo as chances de ocorrer um loopping de
  recomposição é grande, isso por quê ao mexer em um item que esta ligado a algum componente ele faz
  com que esse componente redesenhe para representar o novo valor e caso isso não seja feito da
  maneira correta acaba entrando em looping.

# Slot API

* Tecnica de layout utilizada
  pelo [material](https://m3.material.io/develop/android/jetpack-compose) que tem o intuito de
  deixar "caixas" dentro de um componente para a personalização com outros composables.

OutlinedTextField:

```kotlin
 OutlinedTextField(value = searchText,
  onValueChange = { value ->
    searchTextChanged(value)
  },
  label = { Text(text = "Produto") },
  maxLines = 1,
  modifier = Modifier
    .fillMaxWidth()
    .padding(16.dp),
  shape = RoundedCornerShape(percent = 100),
  leadingIcon = {
    Icon(imageVector = Icons.Default.Search, contentDescription = null)
  },
  placeholder = {
    Text(text = "O que você procura?")
  })

```

No exemplo acima são slots api a propriedade de leadingIcon, placeholder e label.

* O Slot Api é uma extensão e não uma restrição, ou seja, você pode ou não utiliza-lo.