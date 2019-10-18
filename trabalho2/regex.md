# REGEX

## Link

Regra geral: `\[.*\]\(.*\)`

### Para bibliografia

Regra para a bibliografia:

Deve gerar um `\cite{}`

### Para link (URL)
Regra para link:`\[\@.*\]\(.*\)`

Deve gerar um `\footnote{}`


## Estilização

### Itálico
Regra: `[^\*]\*[^\*]+\*[^\*]`

Gerar um `\textit{}`

### Negrito
Regra: `\*{2}[^\*]+\*{2}`

Gerar um `\textbf{}`

Obs: é necessário fazer uma regra só para negrito + itálico

## Título
### H1
Regra: `^\#{1} .+$`
