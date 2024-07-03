<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CRUD Cidades</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<body>
    <nav class="navbar navbar-expand-smbg-dark d-flex justify-content-end">
        <span class="navbar-brand text-white">${Session.currentUser}</span>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a 
                    class="nav-link btn btn-danger p-2" 
                    href="/logout">Sair da aplicação</a>
            </li>
        </ul>
    </nav>
    <section class="container-fluid">
        <div class="container-fluid">
            <div class="jumbotron mt-5">
                <h1>GERENCIAMENTO DE CIDADES</h1>
                <p>CRIAR, ALTERAR EXCLUIR E LISTAR CIDADES</p>
            </div>
            
            <article class="card p-3">
                <#if currentCity??>
                    <form action="/update" method="POST" class="needs-validation" novalidate>
                    <input type="hidden" name="currentName" value="${(currentCity.name)!}"/>
                    <input type="hidden" name="currentState" value="${(currentCity.state)!}"/>
                <#else>
                    <form action="/create" method="POST" class="needs-validation" novalidate>
                </#if>
            
                <div class="form-group">
                    <label for="name">Cidade:</label>
                    <input 
                        value="${(currentCity.name)!}${nameProvided!}" 
                        name="name" 
                        type="text" 
                        class="form-control ${(name??)?then('is-invalid', '')}" 
                        placeholder="Informe o nome da cidade" 
                        id="name">
                    
                    <div class="invalid-feedback">
                        ${name!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="state">Estado:</label>
                    <input 
                        value="${(currentCity.state)!}${stateProvided!}" 
                        name="state" 
                        type="text" 
                        class="form-control ${(state??)?then('is-invalid', '')}" 
                        placeholder="Informe o estado ao qual a cidade pertence"
                        id="state">

                    <div class="invalid-feedback">
                        ${state!}
                    </div>
                </div>

                <#if currentCity??>
                    <button type="submit" class="btn btn-warning">CONCLUIR ALTERAÇÃO</button>
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
                    <#list listCities as city >
                        <tr>
                            <td>${city.name}</td>
                            <td>${city.state}</td>
                            <td>
                                <div class="d-flex">
                                <a href="/prepareUpdate?name=${city.name}&state=${city.state}" class="btn btn-warning mr-3">ALTERAR</a>
                                <a href="/delete?name=${city.name}&state=${city.state}" class="btn btn-danger">EXCLUIR</a>
                                </div>
                            </td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </section>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>

</html>