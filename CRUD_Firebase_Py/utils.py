import pyrebase
import dotenv
import os

dotenv.load_dotenv(dotenv.find_dotenv())


def conectar():

    config = {
        "apikey": "",
        "authDomain": os.getenv('url'),
        "databaseURL": os.getenv('url'),
        "storageBucket": os.getenv('storageBucket')
    }

    conn = pyrebase.initialize_app(config)

    db = conn.database()

    return db


def desconectar():
    print('Desconectando do servidor.')


def listar():
    db = conectar()

    produtos = db.child("produtos").get()

    if produtos.val():
        print('Listando produtos....')
        print('.....................')
        for produto in produtos.each():
            print(f"ID: {produto.key()}")
            print(f"Nome: {produto.val()['nome']}")
            print(f"Preço: {produto.val()['preco']}")
            print(f"Estoque: {produto.val()['estoque']}")
            print('.......................')
    else:
        print('Não existem produtos cadastrados')


def inserir():
    db = conectar()

    if db:
        nome = input('Informe o nome do produto: ')
        preco = float(input('Informe o preço do produto: '))
        estoque = int(input('Informe a quantidade em estoque: '))

        produto = {"nome": nome, "preco": preco, "estoque": estoque}

        res = db.child("produtos").push(produto)

        if 'name' in res:
            print(f'O produto {nome} foi inserido com sucesso.')
        else:
            print(f'Não foi possível inserir o produto {nome}')
    else:
        print(f'Não foi possível inserir o produto {nome}')


def atualizar():
    db = conectar()

    _id = input('Informe o ID do produto: ')

    produto = db.child('produtos').child(_id).get()

    if produto.val():
        nome = input('Informe o nome do produto: ')
        preco = float(input('Informe o preço do produto: '))
        estoque = int(input('Informe a quantidade em estoque: '))

        novo_produto = {"nome": nome, "preco": preco, "estoque": estoque}

        db.child('produto').child(_id).update(novo_produto)

        print(f'O produto {nome} foi atualizado com sucesso.')
    else:
        print(f'Erro ao acessar o banco de dados')


def deletar():

    db = conectar()

    _id = input('Informe o ID do produto: ')

    produto = db.child('produtos').child(_id).get()

    if produto.val():
        db.child('produtos').child(_id).remove()
        print(f'O produto com ID: {_id} foi deletado com sucesso.')
    else:
        print(f'Não existe produto com {_id} informado.')


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
