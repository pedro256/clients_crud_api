package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.demo.models.Client;
import com.example.demo.repository.ClientRepository;


@RestController
@RequestMapping("/clientes")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@GetMapping
	public List<Client> listaClientes(){
		return clientRepository.findAll();
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client create(@RequestBody Client cliente) {
		return clientRepository.save(cliente);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> findOne(@PathVariable Long id) {
		return clientRepository.findById(id)
        .map(record -> ResponseEntity.ok().body(record))
        .orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client clienteInsert){
		Optional<Client> clienteNew = clientRepository.findById(id);
		if(clienteNew.isPresent()) {
			clienteNew.get().setNome(clienteInsert.getNome());
			clienteNew.get().setCpf(clienteInsert.getCpf());
			clienteNew.get().setOnline(clienteInsert.isOnline());
			
			clientRepository.save(clienteNew.get());
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	    
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Client> delete(@PathVariable Long id){
		Optional<Client> clientFin = clientRepository.findById(id);
		if(clientFin.isPresent()) {
			clientRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
		
	}
}
