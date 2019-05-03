package aplicacao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {

		PessoaCRUD pessoaCRUD = new PessoaCRUD();
		pessoaCRUD.inicializarConexao();

		Scanner entrada = new Scanner(System.in);

		for (int i = 0; i < 50; i++) {
			imprimir("");
		}

		String opcao;

		while (true) {
			imprimir("\nEscolha uma das opcoes abaixo\n");
			Menu.menu();

			opcao = entrada.nextLine();

			switch (opcao) {

			case "1":
				List<Pessoa> pessoas = pessoaCRUD.listarPessoas();
				if (pessoas.isEmpty()) {
					imprimir("Não há pessoas cadastradas!");
				} else {
					pessoas.forEach(System.out::println);
				}
				break;
			case "2":
				do {
					obterPessoaId(pessoaCRUD, entrada);
				} while (repetirAcao(entrada));
				break;

			case "3":

				List<Pessoa> pessoas1;

				String nome;
				String email;

				boolean existe = false;

				do {
					do {
						if (existe) {
							imprimir("Esta pessoa ja existe! Preencha os dados novamente!");
						}

						nome = pedirString(entrada, "nome");
						email = pedirString(entrada, "email");

						existe = false;
						pessoas1 = pessoaCRUD.listarPessoas();

						if (!pessoas1.isEmpty()) {
							for (int i = 0; i < pessoas1.size(); i++) {
								if (pessoas1.get(i).getEmail().equals(email)
										&& pessoas1.get(i).getNome().equals(nome)) {
									existe = true;
									break;
								}
							}
						}
					} while (existe == true);

					Pessoa pessoa = new Pessoa(null, nome, email);

					pessoaCRUD.cadastrarPessoa(pessoa);
				} while (repetirAcao(entrada));

				break;
			case "4":

				do {
					Pessoa pessoa = obterPessoaId(pessoaCRUD, entrada);

					if (pessoa != null) {
						imprimir("Deseja mudar o nome? S/N");
						if (entrada.nextLine().toLowerCase().contains("s")) {
							pessoa.setNome(pedirString(entrada, "nome"));
						}
						imprimir("Deseja mudar o email? S/N");
						if (entrada.nextLine().toLowerCase().contains("s")) {
							pessoa.setNome(pedirString(entrada, "email"));
						}

						pessoaCRUD.cadastrarPessoa(pessoa);
					}

				} while (repetirAcao(entrada));

				break;
			case "5":

				do {
					Pessoa pessoa = obterPessoaId(pessoaCRUD, entrada);

					if (pessoa != null) {
						pessoaCRUD.deletarPessoa(pessoa);
					}

				} while (repetirAcao(entrada));

				break;
			case "0":
				imprimir("Finalizando programa!");
				pessoaCRUD.fecharConecoesEntity();
				System.exit(0);
			default:
				System.out.println("Número inválido!");
			}

		}
	}

	public static void imprimir(String texto) {
		System.out.println("\n" + texto + "\n");
	}

	public static boolean repetirAcao(Scanner entrada) {
		System.out.println("Deseja repetir a ação? S/N");
		return entrada.nextLine().toLowerCase().contains("s");
	}

	public static String pedirString(Scanner entrada, String texto) {
		imprimir("\nDigite o " + texto + ":");
		String textoEntrada;

		do {
			textoEntrada = entrada.nextLine();
		} while (textoEntrada.equals(""));

		return textoEntrada;
	}

	public static Pessoa obterPessoaId(PessoaCRUD pessoaCRUD, Scanner entrada) {
		Integer id;

		while (true) {
			try {
				imprimir("Digite um número para buscar por id: ");
				id = Integer.parseInt(entrada.nextLine());
				break;
			} catch (NumberFormatException e) {
				imprimir("Número inválido!");
			}
		}

		Pessoa pessoa = pessoaCRUD.buscarPessoaPorId(id);
		if (pessoa == null) {
			imprimir("Pessoa invalida");
		} else {
			imprimir(pessoa.toString());
		}

		return pessoa;
	}
}
