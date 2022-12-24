
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
* Column -> Os componentes dentro do Column é organizado de forma vertical
* Box -> Os componentes dentro do Box ficam um sobre o outro.