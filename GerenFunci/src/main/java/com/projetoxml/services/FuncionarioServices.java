package com.projetoxml.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoxml.entities.Funcionario;
import com.projetoxml.repository.FuncionarioRepository;

@Service
public class FuncionarioServices {
	private final FuncionarioRepository FuncionarioRepository;
	

	@Autowired
	public FuncionarioServices(FuncionarioRepository FuncionarioRepository) {
		this.FuncionarioRepository = FuncionarioRepository;
	}

	public List<Funcionario> getAllFuncionario() {
		return FuncionarioRepository.findAll();
	}

	public Funcionario getFuncionarioById(Long id) {
		Optional<Funcionario> Funci = FuncionarioRepository.findById(id);
		return Funci.orElse(null);
	}

	public Funcionario saveFuncionario(Funcionario funcionario) {
		return FuncionarioRepository.save(funcionario);
	}

	public Funcionario changeFuncionario(Long id, Funcionario gerFun) {
		Optional<Funcionario> existeFunc = FuncionarioRepository.findById(id);
		if (existeFunc.isPresent()) {
			gerFun.setId(id);
			return FuncionarioRepository.save(gerFun);
		}
		return null;
	}

	public boolean deleteFuncionario(Long id) {
		Optional<Funcionario> existeFunc= FuncionarioRepository.findById(id);
		if (existeFunc.isPresent()) {
			FuncionarioRepository.deleteById(id);
			return true;
		}
		return false;
	}

}