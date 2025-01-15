package com.generation.rh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.rh.model.Funcionario;
import com.generation.rh.repository.FuncionarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@GetMapping
	public ResponseEntity<List<Funcionario>> getAll() {
		return ResponseEntity.ok(funcionarioRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> getByid(@PathVariable Long id) {
		return funcionarioRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	
	}
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Funcionario>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(funcionarioRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Funcionario> post(@Valid @RequestBody Funcionario funcionario) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(funcionarioRepository.save(funcionario));
	}
	
	
	
}
