import java.util.ArrayList;
import java.util.Scanner;
//usei o ArrayList porque achei mais simples e mais flexível.
//o tamanho e a capacidade do array normal precisa ser definido desde o começo,
//então dificulta quaisquer adaptações que eu queira fazer depois.
public class atv2 {
        public static class AcaoLivro {
            public void realizarAcao() {

            }
        }
        private static final ArrayList<Livro> listaDeLivros = new ArrayList<>();
        private static final ArrayList<Livro> listaDeLivrosEmprestados = new ArrayList<>();

        public static class Livro extends AcaoLivro {
            String titulo;
            String autor;
            int anoPublicacao;

            public Livro(String titulo, String autor, int anoPublicacao) {
                this.titulo = titulo;
                this.autor = autor;
                this.anoPublicacao = anoPublicacao;
            }

            @Override
            public void realizarAcao() {
            }
        }

        public static class LivroFiccao extends AcaoLivro {
            Livro livro;
            String genero;

            public LivroFiccao(String titulo, String autor, int anoPublicacao, String genero) {
                this.livro = new Livro(titulo, autor, anoPublicacao);
                this.genero = genero;
            }

            public void realizarAcao() {
                System.out.println("Livro de ficção:");
                System.out.println("Título: " + livro.titulo);
                System.out.println("Autor: " + livro.autor);
                System.out.println("Gênero: " + genero);
            }
        }

        public static class LivroNaoFiccao extends AcaoLivro {
            Livro livro;
            String assunto;

            public LivroNaoFiccao(String titulo, String autor, int anoPublicacao, String assunto) {
                this.livro = new Livro(titulo, autor, anoPublicacao);
                this.assunto = assunto;
            }

            public void realizarAcao() {
                System.out.println("Livro de não ficção:");
                System.out.println("Título: " + livro.titulo);
                System.out.println("Autor: " + livro.autor);
                System.out.println("Assunto: " + assunto);
            }
        }

        private static void cadastrarLivro() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o título do livro:");
            String titulo = scanner.nextLine();
            System.out.println("Digite o autor do livro:");
            String autor = scanner.nextLine();
            System.out.println("Digite o ano de publicação do livro:");
            int anoPublicacao = scanner.nextInt();
            System.out.println("Escolha o tipo de livro (1. Ficção, 2. Não Ficção):");
            int tipoLivro = scanner.nextInt();
            scanner.nextLine();

            AcaoLivro acaoLivro;

            if (tipoLivro == 1) {
                System.out.println("Digite o gênero do livro de ficção:");
                String genero = scanner.nextLine();
                acaoLivro = new LivroFiccao(titulo, autor, anoPublicacao, genero);
            } else if (tipoLivro == 2) {
                System.out.println("Digite o assunto do livro de não ficção:");
                String assunto = scanner.nextLine();
                acaoLivro = new LivroNaoFiccao(titulo, autor, anoPublicacao, assunto);
            } else {
                System.out.println("Opção inválida.");
                return;
            }

            listaDeLivros.add(new Livro(titulo, autor, anoPublicacao));
            listaDeLivrosEmprestados.add(new Livro(titulo, autor, anoPublicacao));

            System.out.println("Livro cadastrado com sucesso!");

            acaoLivro.realizarAcao();
        }

        private static void realizarEmp() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o título do livro a ser emprestado:");
            String titulo = scanner.nextLine();

            Livro livroEmprestado = null;

            for (Livro livro : listaDeLivros) {
                if (livro.titulo.equals(titulo)) {
                    livroEmprestado = livro;
                    break;
                }
            }
            if (livroEmprestado != null) {
                listaDeLivros.remove(livroEmprestado);
                System.out.println("Livro emprestado com sucesso!");

                AcaoLivro(titulo);
            } else {
                System.out.println("Livro não encontrado.");
            }
        }

        private static void AcaoLivro(String titulo) {
            for (AcaoLivro acaoLivro : listaDeLivrosEmprestados) {
                if (acaoLivro instanceof LivroFiccao && ((LivroFiccao) acaoLivro).livro.titulo.equals(titulo)) {
                    acaoLivro.realizarAcao();
                    break;
                } else if (acaoLivro instanceof LivroNaoFiccao && ((LivroNaoFiccao) acaoLivro).livro.titulo.equals(titulo)) {
                    acaoLivro.realizarAcao();
                    break;
                }
            }
        }

        private static void realizarDev() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o título do livro a ser devolvido:");
            String titulo = scanner.nextLine();

            Livro livroDevolvido = null;

            for (Livro livro : listaDeLivrosEmprestados) {
                if (livro.titulo.equals(titulo)) {
                    livroDevolvido = livro;
                    break;
                }
            }
            if (livroDevolvido != null) {
                listaDeLivros.add(livroDevolvido);
                System.out.println("Livro devolvido com sucesso!");

                AcaoLivro(titulo);
                listaDeLivrosEmprestados.remove(livroDevolvido);
            } else {
                System.out.println("Livro não encontrado.");
            }
        }

        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            int menu = 0;

            while (menu != 4) {
                System.out.println("Bem vindo(a) à Biblioteca!");
                System.out.println("1. Cadastrar livro");
                System.out.println("2. Realizar empréstimo");
                System.out.println("3. Realizar devolução");
                System.out.println("4. Sair");
                menu = scanner.nextInt();
                scanner.nextLine();

                switch (menu) {
                    case 1:
                        cadastrarLivro();
                        break;
                    case 2:
                        realizarEmp();
                        break;
                    case 3:
                        realizarDev();
                        break;
                    case 4:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            }
        }
    }

