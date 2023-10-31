package com.projetoxml.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoxml.entities.Funcionario;
import com.projetoxml.services.FuncionarioServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Pedido", description = "API REST DE GERENCIAMENTO DO Gerenciamento")
@RestController
@RequestMapping("/Funcionario")
public class FuncionarioController {

	private final FuncionarioServices FuncionarioServices;

	@Autowired
	public FuncionarioController(FuncionarioServices FuncionarioServices) {
		this.FuncionarioServices = FuncionarioServices;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza Gerenciamento por ID")
	public ResponseEntity<Funcionario> buscaFuncionarioControlId(@PathVariable Long id) {
		Funcionario Funcionario = FuncionarioServices.getFuncionarioById(id);
		if (Funcionario != null) {
			return ResponseEntity.ok(Funcionario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "apresenta todos os Gerenciamentos")
	public ResponseEntity<List<Funcionario>> buscaTodosFuncControl() {
		List<Funcionario> Funcionario = FuncionarioServices.getAllFuncionario();
		return ResponseEntity.ok(Funcionario);
	}

	@PostMapping("/")
	@Operation(summary = "cadastra os Gerenciamentos")
	public ResponseEntity<Funcionario> saveFuncionarioControl(@RequestBody @Valid Funcionario Funcionario) {
		Funcionario saveFunc = FuncionarioServices.saveFuncionario(Funcionario);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveFunc);
	}

	@PutMapping("/{id}")
	@Operation(summary = "altera os Gerenciamentos")
	public ResponseEntity<Funcionario> alteraFuncionarioControl(@PathVariable Long id, @RequestBody @Valid Funcionario Funcionario) {
		Funcionario alteraFunc = FuncionarioServices.changeFuncionario(id, Funcionario);

		if (alteraFunc != null) {
			return ResponseEntity.ok(Funcionario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "deleta os Gerenciamentos")
	public ResponseEntity<String> deleteFuncionarioControl(@PathVariable Long id) {
		boolean delete = FuncionarioServices.deleteFuncionario(id);
		if (delete) {
			return ResponseEntity.ok().body("O produto foi excluido com o sucesso");
		} else {
			return ResponseEntity.notFound().build();
		}

	}

}