package aplicacao;

public class Menu {
	
	public static void menu() {
		final String textos[] = { "Listar Pessoas cadastradas", "Buscar uma Pessoa pelo id", "Cadastrar Pessoa",
				"Atualizar Pessoa", "Remover uma Pessoa", "Sair" };

		for (int i = 1; i < textos.length; i++) {
			imprimir(i + " - " + textos[i - 1]);
		}

		imprimir(0 + " - " + textos[textos.length - 1] + "\n");
	}

	public static void imprimir(String texto) {
		System.out.println(texto);
	}

	public static void main(String[] args) {
		menu();
	}
}
