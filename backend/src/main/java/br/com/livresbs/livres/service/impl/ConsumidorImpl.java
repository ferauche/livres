package br.com.livresbs.livres.service.impl;

import br.com.livresbs.livres.dto.ConsumidorDTO;
import br.com.livresbs.livres.model.Consumidor;
import br.com.livresbs.livres.model.PreComunidade;
import br.com.livresbs.livres.repository.ConsumidorRepository;
import br.com.livresbs.livres.repository.PreComunidadeRepository;
import br.com.livresbs.livres.service.ConsumidorService;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsumidorImpl implements ConsumidorService {
    @Autowired
    ConsumidorRepository cons;

    @Autowired
    PreComunidadeRepository pre;


    public List<ConsumidorDTO> listarConsumidor() {
        List<ConsumidorDTO> listConsdto = new ArrayList<>();
        cons.findAll().forEach(consumidor -> {

            ConsumidorDTO builderDto = ConsumidorDTO.builder()
                    .nome(consumidor.getNome())
                    .cpf(consumidor.getCpf())
                    .senha(consumidor.getSenha())
                    .sobrenome(consumidor.getSobrenome())
                    .precomunidade(consumidor.getPrecomunidade().getId())
                    .build();

            listConsdto.add(builderDto);
        });
        return listConsdto;
    }

    public Consumidor listaConsumidorUnico(@PathVariable(value = "id") String id) {
        return cons.findById(id).get();
    }

    public ResponseEntity cadastraConsumidor(@RequestBody ConsumidorDTO con)  {
        if(!cons.existsById(con.getCpf())) {
            Optional<PreComunidade> oppre = pre.findById(con.getPrecomunidade());
            if(!oppre.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body("Pre Comunidade Não Encontrada!");
            }

            String senha = con.getSenha();

            String sha256hex = Hashing.sha256().hashString(senha, StandardCharsets.UTF_8).toString();

            Consumidor consumidor = Consumidor.builder()
                    .cpf(con.getCpf())
                    .nome(con.getNome())
                    .sobrenome(con.getSobrenome())
                    .senha(sha256hex)
                    .precomunidade(oppre.get())
                    .build();

            cons.save(consumidor);

            return ResponseEntity.status(HttpStatus.OK).body("Cadastrado com Sucesso!");
        }
        else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF já Cadastrado!");
        }
    }


    @Override
    public ResponseEntity<String> editaConsumidor(ConsumidorDTO consumidor) {
        if(cons.existsById(consumidor.getCpf())) {
            Optional<PreComunidade> oppre = pre.findById(consumidor.getPrecomunidade());
            if (!oppre.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body("Pre Comunidade Não Encontrada!");
            }

            String senha = consumidor.getSenha();
            if(senha == "" || senha == null) {

                Consumidor con = Consumidor.builder()
                        .cpf(consumidor.getCpf())
                        .nome(consumidor.getNome())
                        .senha(cons.findById(consumidor.getCpf()).get().getSenha())
                        .sobrenome(consumidor.getSobrenome())
                        .precomunidade(oppre.get())
                        .build();

                cons.save(con);
            }
            else{

                String sha256hex = Hashing.sha256().hashString(senha, StandardCharsets.UTF_8).toString();
                Consumidor con = Consumidor.builder()
                        .cpf(consumidor.getCpf())
                        .nome(consumidor.getNome())
                        .sobrenome(consumidor.getSobrenome())
                        .senha(sha256hex)
                        .precomunidade(oppre.get())
                        .build();

                cons.save(con);
            }

            return ResponseEntity.status(HttpStatus.OK).body("Editado com Sucesso!");
        }
        else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF não Encontrado!");
        }
    }

    @Override
	public /*ResponseEntity*/ void deletarConsumidor(String id) {
		//if(cons.existsById(id)) {
			//Consumidor con = cons.findById(id).get();
			cons.deleteById(id);
			//return ResponseEntity.ok().body("Consumidor: " + con.getNome() + " deletado com sucesso");
		//}
		
			//return ResponseEntity.badRequest().body("Deu ruim");
		
	}



}