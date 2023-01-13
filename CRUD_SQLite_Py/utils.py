import sqlite3


def conectar():
    """Função para conectar ao servidor
    print('Conectando ao servidor...') """

    conn = sqlite3.connect('psqlite3.geek')

    conn.execute("""CREATE TABLE IF NOT EXISTS produtos(
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        nome TEXT NOT NULL,
        preco REAL NOT NULL,
        estoque INTEGER NOT NULL);"""
                 )
    return conn


def desconectar(conn):
    """ Função para desconectar do servidor.
    print('Desconectando do servidor...')"""
    conn.close()


def listar():
    """Função para listar os produtos
    print('Listando produtos...')"""

    conn = conectar()
    cursor = conn.cursor()
    cursor.execute("SELECT * FROM produtos")
    produtos = cursor.fetchall()

    if len(produtos) > 0:
        print('Listando produtos...')
        print('....................')
        for produto in produtos:
            print(f'ID: {produto[0]}')
            print(f'Produto: {produto[1]}')
            print(f'Preço: {produto[2]}')
            print(f'Estoque: {produto[3]}')
            print('....................')
    else:
        print('Não existem produtos cadastrados')
    desconectar(conn)


def inserir():
    """
    Função para inserir um produto
    """
    print('Inserindo produto...')


def atualizar():
    """
    Função para atualizar um produto
    """
    print('Atualizando produto...')


def deletar():
    """
    Função para deletar um produto
    """
    print('Deletando produto...')


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
