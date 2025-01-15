package com.generation.rh.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.rh.model.Funcionario;
import com.generation.rh.repository.FuncionarioRepository;

import jakarta.validation.Valid;

@RestController // Define ao Spring que essa Classe é uma Controller
@RequestMapping("/funcionarios") //Define qual endpoint vai ser tratado por essa Classe
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FuncionarioController {

	@Autowired // O spring dá autonomia para a Interface poder invocar os metodos
	private FuncionarioRepository funcionarioRepository;

	@GetMapping
	public ResponseEntity<List<Funcionario>> getAll() {
		return ResponseEntity.ok(funcionarioRepository.findAll());
	}

	@GetMapping("/{id}") //Mapeia todas as requisições HTTP GET, para o endpoint.
	public ResponseEntity<Funcionario> getByid(@PathVariable Long id) {
		return funcionarioRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta)) // Estamos usando classe optional(map) para tratar um valor que pode estar ausente, afim de evitar erro null(objeto nulo), caso método findById nao seja encontrado o retorno não pode ser nulo, por isso usamos "Optional(Map)" para que informe se possui dados ou nao.
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	
	}
	@GetMapping("/nome/{nome}") //Mapeia as requisições por "nomes" cadastrados
	public ResponseEntity<List<Funcionario>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(funcionarioRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Funcionario> post(@Valid @RequestBody Funcionario funcionario) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(funcionarioRepository.save(funcionario));
	}
	
	@PutMapping
	public ResponseEntity<Funcionario> put(@Valid @RequestBody Funcionario funcionario) {
		return funcionarioRepository.findById(funcionario.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
						.body(funcionarioRepository.save(funcionario)))
						.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);

        if(funcionario.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        funcionarioRepository.deleteById(id);
    }
}
