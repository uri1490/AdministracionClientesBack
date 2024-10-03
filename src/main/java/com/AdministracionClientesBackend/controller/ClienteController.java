package com.AdministracionClientesBackend.controller;
import ch.qos.logback.core.net.server.Client;
import com.AdministracionClientesBackend.exception.ResourceNotFoundException;
import com.AdministracionClientesBackend.model.Cliente;
import com.AdministracionClientesBackend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;
    @GetMapping("/clientes")
    public List<Cliente>listarCliente(){
        return clienteRepository.findAll();
    }
    @PostMapping("/saveClientes")
    public Cliente guardarCliente(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }
    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> listarClientePorId(@PathVariable Long id){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("no existe:"+id));
    return ResponseEntity.ok(cliente);
    }
    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteRequest){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("no existe:"+id));
        cliente.setNombre(clienteRequest.getNombre());
        cliente.setApellido(clienteRequest.getApellido());
        cliente.setEmail(clienteRequest.getEmail());
        Cliente clienteActializado=clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteActializado);
    }
    @DeleteMapping("/deleteClientes/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarCliente(@PathVariable Long id){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("no existe:"+id));
        clienteRepository.delete(cliente);
        Map<String,Boolean> response=new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}