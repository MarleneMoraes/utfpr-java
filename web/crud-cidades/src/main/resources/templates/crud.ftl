<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>CRUD Cidades</title>
</head>

<body>
    <section class="container-fluid">
        <div class="jumbotron mt-5">
            <h1>GERENCIAMENTO DE CIDADES</h1>
            <p>CRIAR, ALTERAR EXCLUIR E LISTAR CIDADES</p>
        </div>

        <article class="card p-3">
        
        <#if cidadeAtual??>
            <form action="/alterar" method="POST">
            <input type="hidden" name="nomeAtual" value="${(cidadeAtual.nome)!}"/>
            <input type="hidden" name="estadoAtual" value="${(estadoAtual.estado)!}"/>
        <#else>
            <form action="/criar" method="POST">
        </#if>

                <div class="form-group">
                    <label for="nome">Cidade:</label>
                    <input value="${(cidadeAtual.nome)!}" type="text" class="form-control" id="nome" name="nome"
                        placeholder="Informe o nome da cidade">
                </div>
                <div class="form-group">
                    <label for="estado">Estado:</label>
                    <input value="${(cidadeAtual.estado)!}" type="text" class="form-control" id="estado" name="estado"
                        placeholder="Informe o estado da cidade">
                </div>

                <#if cidadeAtual??>
                    <button type="submit" class="btn btn-warning">ALTERAR</button>
                <#else>
                    <button type="submit" class="btn btn-primary">CRIAR</button>
                </#if>
            </form>
        </article>
        <table class="table table-striped table-hover">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Nome</th>
                    <th scope="col">Estado</th>
                    <th scope="col">Ações</th>
                </tr>
            </thead>
            <tbody>
                <#list cities as city>
                    <tr>
                        <td>${city.name}</td>
                        <td>${city.state}</td>
                        <td>
                            <div class="d-flex">
                                <button type="button" class="btn btn-warning">
                                    <a href="/alterar?nome=${cidade.nome}&estado=${cidade.estado}">ALTERAR</a>
                                </button>
                                <button type="button" class="btn btn-danger mx-3">
                                    <a href="/excluir?nome=${cidade.nome}&estado=${cidade.estado}">EXCLUIR</a>
                                </button>
                            </div>
                        </td>
                    </tr>
                </#list>
            </tbody>
        </table>

    </section>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>

</html>