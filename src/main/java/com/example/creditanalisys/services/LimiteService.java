package com.example.creditanalisys.services;

import com.example.creditanalisys.converter.DozerConverter;
import com.example.creditanalisys.model.dtos.LimiteDTO;
import com.example.creditanalisys.model.entities.LimiteCred;
import com.example.creditanalisys.model.entities.User;
import com.example.creditanalisys.repositories.LimiteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LimiteService {
    private LimiteRepository limiteRepository;
    private UserService userService;

    public LimiteDTO createLimit(LimiteDTO limiteDTO, Long userId) {
        User catchUser = DozerConverter.parseObject(userService.getUserById(userId), User.class);
        if(catchUser == null){
            throw new RuntimeException("Solicitação de crédito não encontrada com o ID: " + userId);

        }
        LimiteCred limiteCred = DozerConverter.parseObject(limiteDTO, LimiteCred.class);
        limiteCred.setUserId(catchUser.getId());
        LimiteCred savedLimit = limiteRepository.save(limiteCred);
        return DozerConverter.parseObject(savedLimit, LimiteDTO.class);
    }

    public LimiteDTO getLimitById(Long id){
        return DozerConverter.parseObject(limiteRepository.findById(id), LimiteDTO.class);
    }

    public ResponseEntity<LimiteDTO> updateLimite(LimiteDTO limiteDTO, Long id){
        LimiteCred limitSearch = DozerConverter.parseObject(getLimitById(id), LimiteCred.class);
        if (limitSearch == null){
            return ResponseEntity.notFound().build();
        } else {
            limitSearch.setValor(limiteDTO.getValor());
            limitSearch.setDataAprovacao(limiteDTO.getDataAprovacao());
            LimiteCred savedLimit = limiteRepository.save(limitSearch);
            return ResponseEntity.ok(DozerConverter.parseObject(savedLimit, LimiteDTO.class));
        }
    }

    public List<LimiteDTO> getAllLimite(){
        List<LimiteCred> limits = limiteRepository.findAll();
        return DozerConverter.parseListObjects(limits, LimiteDTO.class);
    }

    public ResponseEntity<LimiteDTO> deleteLimite(Long id){
        LimiteCred limiteCred = DozerConverter.parseObject(getLimitById(id), LimiteCred.class);
        if (limiteCred != null){
            limiteRepository.delete(limiteCred);
            return ResponseEntity.ok(DozerConverter.parseObject(limiteCred, LimiteDTO.class));
        }
        return ResponseEntity.notFound().build();
    }
}
