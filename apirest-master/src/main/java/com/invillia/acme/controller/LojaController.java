package com.invillia.acme.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.acme.classe.Loja;
import com.invillia.acme.repository.LojaRepository;

@RestController
@RequestMapping(value="/loja")
public class LojaController {

	@Autowired
	LojaRepository lojaRepository;

	/* Pegar todas as lojas*/
	@GetMapping("/listaLojas")
	public List<Loja> getAllLojas(){
		return lojaRepository.findAll();
	}
	
	/* Salvar loja */
	@PostMapping("/salvarLoja")
	public Loja criarLoja(@Valid @RequestBody Loja lj) {
		return lojaRepository.save(lj);
	}
	
	/*pegar loja pelo id*/
	@GetMapping("/listarLojaPeloId/{id}")
	public ResponseEntity<Loja> getLojaById(@PathVariable(value="id") Long ljid){
		Loja lj = lojaRepository.findAllById(ljid);
		
		if(lj == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(lj);
	}
	
	
	/* atualizar loja */
	@PutMapping("/atualizarLoja")
	public @Valid ResponseEntity<Loja> atualizaProduto(@RequestBody @Valid Loja loja) {
		if(loja.getId() == null) {
			return ResponseEntity.notFound().build();
		}
		Loja updateLoja =  lojaRepository.save(loja);
		return ResponseEntity.ok().body(updateLoja);
		
	}

}
