package com.talissonmelo.aplicacao;

import com.talissonmelo.modelo.Cliente;
import com.talissonmelo.modelo.dto.ClienteSalvar;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/clientes")
@RequiredArgsConstructor
public class ClienteControlador {

    private final ClienteServico servico;

    @GetMapping(value = "/status")
    public String status() {
        return "MS_CLIENTE OK!.";
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody ClienteSalvar clienteSalvar){
        Cliente cliente = clienteSalvar.clienteSalvarParaCliente();
        servico.salvar(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().query("cpf={cpf}").buildAndExpand(cliente.getCpf()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity buscarClientePorCpf(@RequestParam("cpf") String cpf) {
        var cliente = servico.getClientePorCfp(cpf);
        if(cliente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }
}
