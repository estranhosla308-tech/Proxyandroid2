# Proxy Android (v2)

Projeto Android nativo em Kotlin, com interface de painel e integração inicial de autorização/estado do Shizuku.

## Gerar APK pelo GitHub
1. Envie o conteúdo desta pasta para um repositório GitHub.
2. Abra a aba **Actions**.
3. Execute **Build APK** (ou aguarde o build automático após enviar para `main`/`master`).
4. Abra a execução concluída e baixe o artefato **ProxyAndroid-debug**.

## Novidades da v2
- **Conexão com o Shizuku corrigida**: o `ShizukuProvider` agora está declarado no `AndroidManifest.xml` (sem ele o app nunca recebia a conexão e ficava sempre Offline). O status também atualiza sozinho quando o Shizuku liga/desliga.
- **Tela cheia**: o app agora desenha por baixo da barra de status/navegação e do notch. Para esconder também as barras (modo imersivo), troque `telaCheiaImersiva` para `true` no `MainActivity.kt`.
- **Novo visual** (tema roxo neon, cartão de conexão, animações).
- **Menu dos 3 traços**: painel lateral só com as funções principais e a conexão.

## Shizuku
O app verifica se o Binder do Shizuku está ativo e solicita a autorização da API. Em Android 11 ou superior, o Shizuku pode ser iniciado usando Depuração sem fio, pelo próprio app Shizuku.

## Importante sobre as funções
Os três interruptores são seleção visual neste primeiro esqueleto. Os módulos reais de sensibilidade, desempenho e 120 FPS não estão implementados. Shizuku não garante acesso a configurações protegidas nem permite forçar FPS que o aparelho/jogo não suportem. O botão valida a autorização e pode abrir o pacote oficial do Free Fire normal (`com.dts.freefireth`); ele não injeta código, altera memória do jogo ou contorna anti-cheat.

## Assets
Coloque seus arquivos em `app/src/main/assets/`. A pasta está pronta para receber os arquivos do projeto.
