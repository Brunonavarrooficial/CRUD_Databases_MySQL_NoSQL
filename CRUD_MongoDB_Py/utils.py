from pymongo import MongoClient, errors
from bson.objectid import ObjectId
import dotenv
import os

dotenv.load_dotenv(dotenv.find_dotenv())


def conectar():
    """Função para conectar ao servidor
    print('Conectando ao servidor...') """

    conn = MongoClient(os.getenv('url'))

    return conn


def desconectar(conn):
    """ Função para desconectar do servidor.
    print('Desconectando do servidor...')"""
    if conn:
        conn.close()


def listar():
    """Função para listar os produtos
    print('Listando produtos...')"""

    conn = conectar()
    db = conn.pmongo

    try:
        if db.produtos.count_documents({}) > 0:
            produtos = db.produtos.find()
            print('Listando produtos....')
            print('.....................')
            for produto in produtos:
                print(f"ID: {produto['_id']}")
                print(f"Produto: {produto['nome']}")
                print(f"Preço: {produto['preco']}")
                print(f"Estoque: {produto['estoque']}")
                print('....................')
        else:
            print('Não existem produtos cadastrados')
    except errors.PyMongoError as e:
        print(f'Erro ao acessar o banco de dados: {e}')
    desconectar(conn)


def inserir():
    """Função para inserir um produto
    print('Inserindo produto...')"""

    conn = conectar()
    db = conn.pmongo

    nome = input('Informe o nome do produto:')
    preco = float(input('Informe o preço do produto: '))
    estoque = int(input('Informe a quantidade em estoque: '))

    try:
        db.produtos.insert_one(
            {
                "nome": nome,
                "preco": preco,
                "estoque": estoque
            }
        )
        print(f'O produto {nome} foi inserido com sucesso.')
    except errors.PyMongoError as e:
        print(f'Não foi possível inserir o produto erro: {e}')
    desconectar(conn)


def atualizar():
    """Função para atualizar um produto
    print('Atualizando produto...')"""

    conn = conectar()
    db = conn.pmongo

    _id = input('Informe o ID do produto: ')
    nome = input('Informe o nome do produto: ')
    preco = float(input('Informe o preço do produto: '))
    estoque = int(input('Informe a quantidade em estoque: '))

    try:
        if db.produtos.count_documents({}) > 0:
            res = db.produtos.update_one(
                {"_id": ObjectId(_id)},
                {
                    "$set": {
                        "nome": nome,
                        "preco": preco,
                        "estoque": estoque
                    }
                }
            )
            if res.modified_count == 1:
                print(f'O produto {nome} foi atualizado com sucesso.')
            else:
                print(f'Não foi possível atualizar o produto {nome}')
        else:
            print('Não existem documentos para serem atualizados.')
    except errors.PyMongoError as e:
        print(f'Erro ao acessar o banco de dados: {e}')
    desconectar(conn)


def deletar():
    """Função para deletar um produto
    print('Deletando produto...')"""

    conn = conectar()
    db = conn.pmongo

    _id = input('Informe o código do produto: ')

    try:
        if db.produtos.count_documents({}) > 0:
            res = db.produtos.delete_one(
                {
                    "_id": ObjectId(_id)
                }
            )
            if res.deleted_count > 0:
                print(f'O produto com id: {_id} foi deletado com sucesso.')
            else:
                print(f'Não foi possível inserir o produto id: {_id}')
        else:
            print('Não existem produtos para serem deletados.')
    except errors.PyMongoError as e:
        print(f'Erro ao acessar o banco de dados: {e}')
    desconectar(conn)


def menu():
    """
    Função para gerar o menu inicial
    """
    print('=========Gerenciamento de Produtos==============')
    print('Selecione uma opção: ')
    print('1 - Listar produtos.')
    print('2 - Inserir produtos.')
    print('3 - Atualizar produto.')
    print('4 - Deletar produto.')
    opcao = int(input())
    if opcao in [1, 2, 3, 4]:
        if opcao == 1:
            listar()
        elif opcao == 2:
            inserir()
        elif opcao == 3:
            atualizar()
        elif opcao == 4:
            deletar()
        else:
            print('Opção inválida')
    else:
        print('Opção inválida')
