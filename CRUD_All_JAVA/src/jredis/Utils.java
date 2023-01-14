package jredis;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class Utils {

	static Scanner teclado = new Scanner(System.in);

	public static String gera_id() {
		Jedis conn = conectar();

		String chave = conn.get("chave");

		if (chave != null) {
			chave = conn.incr("chave").toString();

			desconectar(conn);
			return chave;
		} else {
			conn.set("chave", "1");
			desconectar(conn);
			return "1";
		}
	}

	public static Jedis conectar() {
		Jedis conn = new Jedis("localhost");
		return conn;
	}

	public static void desconectar(Jedis conn) {
		conn.disconnect();
	}

	public static void listar() {
		Jedis conn = conectar();

		try {
			Set<String> res = conn.keys("produtos:*");

			if (res.size() > 0) {
				System.out.println("Listando produtos.....");
				System.out.println("......................");
				for (String chave : res) {
					Map<String, String> produto = conn.hgetAll(chave);
					System.out.println("ID: " + chave);
					System.out.println("nome: " + produto.get("nome"));
					System.out.println("preço: " + produto.get("preco"));
					System.out.println("Estoque: " + produto.get("estoque"));
				}
			} else {
				System.out.println("Não existem produtos cadastrados.");
			}

		} catch (JedisConnectionException e) {
			System.out.println("verifique se o servidor Redis está ativo. " + e);
		}
		desconectar(conn);
	}

	public static void inserir() {
		Jedis conn = conectar();

		System.out.println("Informe o nome do produto...");
		String nome = teclado.nextLine();

		System.out.println("Informe o preço do produto");
		String preco = teclado.nextLine();

		System.out.println("Informe a quantidade em estoque do produto");
		String estoque = teclado.nextLine();

		Map<String, String> produto = new HashMap<String, String>();
		produto.put("nome", nome);
		produto.put("preco", preco);
		produto.put("estoque", estoque);

		String chave = "produtos:" + Utils.gera_id();

		try {
			String res = conn.hmset(chave, produto);
			if (res != null) {
				System.out.println("o produto " + nome + " foi inserido com sucesso.");
			} else {
				System.out.println("o produto " + nome + " não foi inserido com exito.");
			}
		} catch (JedisConnectionException e) {
			System.out.println("Verifique se o redis está ativo " + e);
		}
		desconectar(conn);

	}

	public static void atualizar() {
		Jedis conn = conectar();

		System.out.println("Informe a chave do produto:");
		String chave = teclado.nextLine();

		System.out.println("Informe o nome do produto...");
		String nome = teclado.nextLine();

		System.out.println("Informe o preço do produto");
		String preco = teclado.nextLine();

		System.out.println("Informe a quantidade em estoque do produto");
		String estoque = teclado.nextLine();

		Map<String, String> produto = new HashMap<String, String>();
		produto.put("nome", nome);
		produto.put("preco", preco);
		produto.put("estoque", estoque);

		try {
			String res = conn.hmset(chave, produto);
			if (res != null) {
				System.out.println("o produto " + nome + " foi atualizado com sucesso.");
			} else {
				System.out.println("o produto " + nome + " não foi atualizado com exito.");
			}
		} catch (JedisConnectionException e) {
			System.out.println("Verifique se o redis está ativo " + e);
		}
		desconectar(conn);

	}

	public static void deletar() {
		Jedis conn = conectar();

		System.out.println("Informe a chave do produto:");
		String chave = teclado.nextLine();

		try {
			Long ret = conn.del(chave);

			if (ret > 0) {
				System.out.println("O produto chave: " + chave + " foi deletado com sucesso.");
			} else {
				System.out.println("Erro O produto chave: " + chave + " não foi deletado deletado.");
			}

		} catch (Exception e) {
			System.out.println("verifique se o redis está ativo: Erro " + e);
		}
		desconectar(conn);
	}

	public static void menu() {
		System.out.println("==================Gerenciamento de Produtos===============");
		System.out.println("Selecione uma opção: ");
		System.out.println("1 - Listar produtos.");
		System.out.println("2 - Inserir produtos.");
		System.out.println("3 - Atualizar produtos.");
		System.out.println("4 - Deletar produtos.");

		int opcao = Integer.parseInt(teclado.nextLine());
		if (opcao == 1) {
			listar();
		} else if (opcao == 2) {
			inserir();
		} else if (opcao == 3) {
			atualizar();
		} else if (opcao == 4) {
			deletar();
		} else {
			System.out.println("Opção inválida.");
		}
	}
}
