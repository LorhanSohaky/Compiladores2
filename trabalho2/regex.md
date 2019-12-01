# REGEX

## Link

Regra geral: `\[.*\]\(.*\)`

### Para bibliografia

Regra para a bibliografia: `\[\@(.*)\]`

Deve gerar um `\cite{}`

### Para link (URL)
Regra para link:`[^!]\[(.*)\]\((.*)\)`

Deve gerar um `\footnote{}`

### Para imagem

Regra para a imagem: `\!\[(.*)\]\((.*)\)`

Deve gerar um `
 \begin{figure*}
\centering
\caption{}
\includegraphics[width=\textwidth]{}
\end{figure*} 
`




## Estilização

### Itálico
Regra: `(\\*)(\\s*\\b)([^\\*]*)(\\b\\s*)(\\*)`

Gerar um `\textit{}`

### Negrito
Regra: `\*{2}[^\*]+\*{2}`

Gerar um `\textbf{}`

Obs: é necessário fazer uma regra só para negrito + itálico

## Título
### H1
Regra: `^#{1}\\s(.*)`
Gerar um `\section{}`

### H2
Regra: `^#{2}\\s(.*)`
Gerar um `\subsection{}`

### H3
Regra: `^#{3}\\s(.*)`
Gerar um `\subsubsection{}`
