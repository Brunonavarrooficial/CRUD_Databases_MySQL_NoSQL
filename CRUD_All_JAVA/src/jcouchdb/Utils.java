package jcouchdb;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Utils {

	static Scanner teclado = new Scanner(System.in);

	public static HttpClient conectar() {
		HttpClient conn = HttpClient.newBuilder().build();
		return conn;
	}

	public static void desconectar() {
		System.out.println("desconectando...");
	}

	public static void listar() {
		HttpClient conn = conectar();

		String link = "http://localhost:5984/jcouch/_all_docs?include_docs=true";

		HttpRequest requisicao = HttpRequest.newBuilder().uri(URI.create(link)).build();

		try {
			HttpResponse<String> resposta = conn.send(requisicao, BodyHandlers.ofString());

			JSONObject obj = new JSONObject(resposta.body());

			if ((int) obj.get("total_rows") > 0) {
				JSONArray produtos = (JSONArray) obj.get("rows");

				System.out.println("Listando produtos.....");
				System.out.println("......................");
				for (Object produto : produtos) {
					JSONObject doc = (JSONObject) produto;
					JSONObject prod = (JSONObject) doc.get("doc");

					System.out.println("ID: " + prod.get("_id"));
					System.out.println("Rev: " + prod.get("_rev"));
					System.out.println("Nome: " + prod.get("nome"));
					System.out.println("preço: " + prod.get("preco"));
					System.out.println("Estoque: " + prod.get("estoque"));
					System.out.println("......................");
				}
			} else {
				System.out.println("Não existem produtos cadastrados.");
			}

		} catch (IOException e) {
			System.out.println("verifique se o servidor CouchDB está ativo. " + e);
		} catch (InterruptedException e) {
			System.out.println("verifique se o servidor CouchDB está ativo. " + e);
		}
	}

	public static void inserir() {
		HttpClient conn = conectar();

		String link = "http://localhost:5984/jcouch";

		System.out.println("Informe o nome do produto...");
		String nome = teclado.nextLine();

		System.out.println("Informe o preço do produto");
		float preco = teclado.nextFloat();

		System.out.println("Informe a quantidade em estoque do produto");
		int estoque = teclado.nextInt();

		JSONObject nproduto = new JSONObject();
		nproduto.put("nome", nome);
		nproduto.put("preco", preco);
		nproduto.put("estoque", estoque);

		HttpRequest requisicao = HttpRequest.newBuilder()
				.uri(URI.create(link))
				.POST(BodyPublishers.ofString(nproduto.toString()))
				.header("Content-Type", "application/json")
				.build();

		try {
			HttpResponse<String> resposta = conn.send(requisicao, BodyHandlers.ofString());
			JSONObject obj = new JSONObject(resposta.body());

			if (resposta.statusCode() == 201) {
				System.out.println("o produto " + nome + " foi inserido com sucesso.");
			} else {
				System.out.println("o produto " + nome + " não foi inserido com exito.");
				System.out.println("Objeto: " + obj);
				System.out.println("Status da Requisição: " + resposta.statusCode());
			}
		} catch (IOException e) {
			System.out.println("Verifique se o CouthDB está ativo " + e);
		} catch (InterruptedException e) {
			System.out.println("Verifique se o CouthDB está ativo " + e);
		}

	}

	public static void atualizar() {
		HttpClient conn = conectar();

		System.out.println("Informe o ID do produto:");
		String id = teclado.nextLine();

		System.out.println("Informe a Rev do produto:");
		String rev = teclado.nextLine();

		System.out.println("Informe o nome do produto...");
		String nome = teclado.nextLine();

		System.out.println("Informe o preço do produto");
		float preco = teclado.nextFloat();

		System.out.println("Informe a quantidade em estoque do produto");
		int estoque = teclado.nextInt();

		String link = "http://localhost:5984/jcouch/" + id + "/" + "?rev=" + rev;

		JSONObject nproduto = new JSONObject();
		nproduto.put("nome", nome);
		nproduto.put("preco", preco);
		nproduto.put("estoque", estoque);

		HttpRequest requisicao = HttpRequest.newBuilder()
				.uri(URI.create(link))
				.PUT(BodyPublishers.ofString(nproduto.toString()))
				.header("Content-Type", "application/json")
				.build();

		try {
			HttpResponse<String> resposta = conn.send(requisicao, BodyHandlers.ofString());
			JSONObject obj = new JSONObject(resposta.body());
			if (resposta.statusCode() == 201) {
				System.out.println("o produto " + nome + " foi atualizado com sucesso.");
			} else {
				System.out.println("o produto " + nome + " não foi atualizado com exito.");
				System.out.println("Objeto: " + obj);
				System.out.println("Status da Requisição: " + resposta.statusCode());
			}
		} catch (IOException e) {
			System.out.println("Verifique se o CouchDB está ativo " + e);
		} catch (InterruptedException e) {
			System.out.println("Verifique se o CouchDB está ativo " + e);
		}

	}

	public static void deletar() {

		HttpClient conn = conectar();

		System.out.println("Informe o ID do produto:");
		String id = teclado.nextLine();

		System.out.println("Informe a Rev do produto:");
		String rev = teclado.nextLine();

		String link = "http://localhost:5984/jcouch/" + id + "/" + "?rev=" + rev;

		HttpRequest requisicao = HttpRequest.newBuilder()
				.uri(URI.create(link))
				.DELETE()
				.build();

		try {
			HttpResponse<String> resposta = conn.send(requisicao, BodyHandlers.ofString());

			if (resposta.statusCode() == 200) {
				System.out.println("o produto id: " + id + " foi deletado com sucesso.");
			} else {
				System.out.println("o produto id: " + id + " não foi deletado com exito.");
				System.out.println("Objeto: " + resposta.body());
				System.out.println("Status da Requisição: " + resposta.statusCode());
			}

		} catch (IOException e) {
			System.out.println("Verifique se o CouchDB está ativo " + e);
		} catch (InterruptedException e) {
			System.out.println("Verifique se o CouchDB está ativo " + e);
		}

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
