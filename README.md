
### Como rodar a aplicação

O aplicativo utiliza o docker

Para rodar use o comando

```
docker compose up
```

INFORMAÇÕES DOS ALUNOS PARTICIPANTES estão no AUTHORS.md

```
GET /sobre
```

O sistema possui três grandes rotas

``
/remedios /pacientes /receitas
``

Para cadastrar uma receita você precisa de pelo menos um paciente e um remédio cadastrados.

Cadastre o remédio usando a rota POST

```
POST /remedios
{
	"dosagem": "1cp 8h",
	"descricao": "Tomar um comprimido a cada oito horas",
	"nome": "Ibuprofeno"
}
```

Cadastre o paciente usando a rota POST

```
POST /pacientes
{
	"idade": 24,
	"cpf": "inserir-cpf-valido", //aqui precisa ser um CPF válido
	"nome": "Artur Salvador Tiscoski"
}
```

Você pode verificar os pacientes e remédios cadastrados na rota GET

```
GET /remedios/{id}
GET /pacientes/{id}
```

É possivel atualizar também usando a rota `PATCH`

e deletar usando a rota `DELETE`

Para cadastrar uma receita é usado esse padrão

```
POST /receitas
{
	"posologia": "Tomar 1 comprimido a cada 8 horas",
	"pacienteUUID": "eb7451cd-2731-4ed5-814f-ab79381874cc", //onde esse é um UUID valido de algum paciente
	"remediosUUID": [
		"83294149-bc1d-4b01-926a-901e6fe0bec0", //Aqui são UUIDs de remédios já cadastrados antes
		"125bf3a6-0473-48aa-8e47-23122abe7b18"
	]
}
```

O sistema é tratado para verificar algumas exceptions