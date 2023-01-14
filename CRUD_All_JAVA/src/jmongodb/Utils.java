package jmongodb;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.JSONObject;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public class Utils {

	static Scanner teclado = new Scanner(System.in);

	public static MongoCollection<Document> conectar() {
		try {
			com.mongodb.client.MongoClient conn = MongoClients.create(
					MongoClientSettings.builder()
							.applyToClusterSettings(
									builder -> builder.hosts(Arrays.asList(new ServerAddress("localhost", 27017))))
							.build());

			MongoDatabase database = conn.getDatabase("jmongo");
			MongoCollection<Document> collection = database.getCollection("produtos");
			return collection;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void desconectar(MongoCursor<Document> cursor) {
		cursor.close();
	}

	public static void listar() {
		MongoCollection<Document> collection = conectar();

		if (collection.countDocuments() > 0) {
			MongoCursor<Document> cursor = collection.find().iterator();

			try {
				System.out.println("Listando produtos..");
				System.out.println("...................");
				while (cursor.hasNext()) {
					String json = cursor.next().toJson();

					JSONObject obj = new JSONObject(json);
					JSONObject id = obj.getJSONObject("_id");

					System.out.println("ID: " + id.get("$oid"));
					System.out.println("Produto: " + obj.get("nome"));
					System.out.println("Preço: " + obj.get("preço"));
					System.out.println("Estoque: " + obj.get("estoque"));
					System.out.println(".......................");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			desconectar(cursor);

		} else {
			System.out.println("Não existem produtos cadastrados. ");
		}
	}

	public static void inserir() {
		MongoCollection<Document> collection = conectar();

		System.out.println("Informe o nome do produto...");
		String nome = teclado.nextLine();

		System.out.println("Informe o preço do produto");
		teclado.useLocale(Locale.ENGLISH);
		float preco = teclado.nextFloat();

		System.out.println("Informe a quantidade em estoque do produto");
		int estoque = teclado.nextInt();

		JSONObject nproduto = new JSONObject();
		nproduto.put("nome", nome);
		nproduto.put("preco", preco);
		nproduto.put("estoque", estoque);

		collection.insertOne(Document.parse(nproduto.toString()));
		System.out.println("o produto" + nome + "foi inserido com sucesso.");

	}

	public static void atualizar() {
		MongoCollection<Document> collection = conectar();

		System.out.println("Informe o ID do produto:");
		String _id = teclado.nextLine();

		System.out.println("Informe o nome do produto");
		String nome = teclado.nextLine();

		System.out.println("Informe o preco do produto");
		teclado.useLocale(Locale.ENGLISH);
		float preco = teclado.nextFloat();

		System.out.println("Informe a quantidade em estoque");
		int estoque = teclado.nextInt();

		Bson query = combine(set("nome", nome), set("preco", preco), set("estoque", estoque));

		UpdateResult res = collection.updateOne(new Document("_id", new ObjectId(_id)), query);

		if (res.getModifiedCount() == 1) {
			System.out.println("O produto " + nome + " foi atualizado com sucesso.");
		} else {
			System.out.println("O produto " + nome + " não pode ser atualizado.");
		}

	}

	public static void deletar() {
		MongoCollection<Document> collection = conectar();

		System.out.println("Informe o ID do produto:");
		String _id = teclado.nextLine();

		DeleteResult res = collection.deleteOne(new Document("_id", new ObjectId(_id)));

		if (res.getDeletedCount() == 1) {
			System.out.println("O produto id: " + _id + " foi deletado com sucesso.");
		} else {
			System.out.println("Erro O produto id: " + _id + " não foi deletado deletado.");
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
